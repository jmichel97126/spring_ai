# Configuration AWS Secrets Manager avec Spring Boot + Spring AI

Ce guide explique comment stocker votre clé **OpenAI** dans **AWS Secrets Manager** et la consommer automatiquement dans une application **Spring Boot** utilisant **Spring AI**.

---

## Prérequis
- Compte AWS actif.
- AWS CLI installé et configuré (`aws configure`).
- Java 21+ recommandé.
- Rôle IAM avec permission `secretsmanager:GetSecretValue`.

---

## 1) Création du secret via l'interface AWS Management Console

1. Connectez-vous à [AWS Console](https://console.aws.amazon.com/).
2. Allez dans **Secrets Manager**.
3. Cliquez sur **Store a new secret**.
4. Choisissez **Other type of secret**.
5. Ajoutez une clé/valeur :
   - Key : `openai-api-key`
   - Value : `sk-xxx_votre_cle_openai`
6. Cliquez sur **Next**.
7. Donnez un nom au secret (ex: `openai-api-key`).
8. Cliquez sur **Store**.

### Ajouter des permissions IAM
- Allez dans **IAM > Roles**.
- Ajoutez la politique `SecretsManagerReadWrite` ou une politique personnalisée avec `secretsmanager:GetSecretValue`.

---

## 2) Création du secret via CLI

```bash
SECRET_NAME=openai-api-key
OPENAI_KEY=sk-xxx_votre_cle_openai
REGION=eu-west-1

aws secretsmanager create-secret     --name "$SECRET_NAME"     --secret-string "{"openai-api-key":"$OPENAI_KEY"}"     --region "$REGION"
```

---

## 3) Dépendances Maven

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-openai-spring-boot-starter</artifactId>
    <version>1.0.0</version>
  </dependency>
  <dependency>
    <groupId>io.awspring.cloud</groupId>
    <artifactId>spring-cloud-starter-aws-secrets-manager-config</artifactId>
    <version>3.0.0</version>
  </dependency>
</dependencies>
```

---

## 4) Configuration Spring Boot (`application.yml`)

```yaml
spring:
  config:
    import: aws-secretsmanager:/openai-api-key

  ai:
    openai:
      api-key: ${openai-api-key}
      chat:
        model: gpt-4o-mini
```

> ✅ `aws-secretsmanager:/openai-api-key` indique à Spring Cloud AWS de charger le secret nommé `openai-api-key`.

---

## ✅ Configuration simplifiée avec `application.properties`

Pour les démos ou projets rapides, vous pouvez utiliser une configuration minimale :

```properties
spring.config.import=aws-secretsmanager:/openai-api-key
spring.application.name=spring-aws-secret-openai-key-demo
spring.ai.openai.api-key=${openai-api-key}
```

### Pourquoi cette approche ?
- **Rapide à mettre en place** : une seule ligne pour activer Secrets Manager.
- **Moins de complexité** : pas besoin de YAML détaillé.
- **Compatible avec Spring Boot 3.x et Spring Cloud AWS**.

> Assurez-vous que :
> - Le secret `openai-api-key` existe dans AWS Secrets Manager.
> - Le rôle IAM a la permission `secretsmanager:GetSecretValue`.
> - Les credentials AWS sont configurés (via instance profile, rôle IAM ou `AWS_ACCESS_KEY_ID` / `AWS_SECRET_ACCESS_KEY`).

---

## 5) Exemple d'utilisation

```java
@Service
public class AiService {
    private final OpenAiChatClient chatClient;
    public AiService(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }
    public String ask(String question) {
        return chatClient.call(new Prompt(question)).getResult().getOutput().getContent();
    }
}
```

---

## 6) Déploiement (ex. ECS, EC2, Lambda)
- Assurez-vous que le rôle IAM associé au service a la permission `secretsmanager:GetSecretValue`.
- Aucune variable d'environnement nécessaire pour la clé OpenAI.

---

## Bonnes pratiques
- Rotation des secrets via AWS Secrets Manager.
- IAM minimal.
- Ne jamais exposer la clé en clair.
