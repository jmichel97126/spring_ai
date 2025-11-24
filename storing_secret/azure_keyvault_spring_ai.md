# Configuration Azure Key Vault avec Spring Boot + Spring AI

Ce guide explique comment stocker votre clé **OpenAI** dans **Azure Key Vault** et la consommer automatiquement dans une application **Spring Boot** utilisant **Spring AI**.

---

## Prérequis
- Compte Azure actif.
- Azure CLI installé et authentifié (`az login`).
- Java 21+ recommandé.
- Identité managée ou Service Principal avec rôle `Key Vault Secrets User`.

---

## 1) Création du secret via l'interface Azure Portal

1. Connectez-vous à [Azure Portal](https://portal.azure.com/).
2. Allez dans **Key Vaults** et créez un coffre si nécessaire.
3. Ouvrez votre Key Vault.
4. Cliquez sur **Secrets** > **Generate/Import**.
5. Donnez un nom (ex: `openai-api-key`).
6. Collez votre clé OpenAI dans le champ **Value**.
7. Cliquez sur **Create**.

### Ajouter des permissions
- Allez dans **Access policies**.
- Ajoutez une politique pour votre identité (Managed Identity ou Service Principal) avec la permission **Get** sur les secrets.

---

## 2) Création du secret via CLI

```bash
VAULT_NAME=my-keyvault
SECRET_NAME=openai-api-key
OPENAI_KEY=sk-xxx_votre_cle_openai

az keyvault secret set --vault-name $VAULT_NAME --name $SECRET_NAME --value $OPENAI_KEY
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
    <groupId>com.azure.spring</groupId>
    <artifactId>spring-cloud-azure-starter-keyvault-secrets</artifactId>
    <version>5.7.0</version>
  </dependency>
</dependencies>
```

---

## 4) Configuration Spring Boot (`application.yml`)

```yaml
spring:
  cloud:
    azure:
      keyvault:
        secret:
          enabled: true
          property-source-enabled: true
          vault-name: ${AZURE_KEYVAULT_NAME}

  ai:
    openai:
      api-key: ${openai-api-key}
      chat:
        model: gpt-4o-mini
```

> ✅ Spring Cloud Azure injecte automatiquement les secrets comme propriétés.

---

## ✅ Configuration simplifiée avec `application.properties`

Pour les démos ou projets rapides, vous pouvez utiliser une configuration minimale :

```properties
spring.config.import=azure-keyvault:/
spring.application.name=spring-azure-keyvault-openai-demo
spring.ai.openai.api-key=${openai-api-key}
```

### Pourquoi cette approche ?
- **Rapide à mettre en place** : une seule ligne pour activer Key Vault.
- **Moins de complexité** : pas besoin de YAML détaillé.
- **Compatible avec Spring Boot 3.x et Spring Cloud Azure**.

> Assurez-vous que :
> - Le secret `openai-api-key` existe dans Azure Key Vault.
> - L'identité (Managed Identity ou Service Principal) a la permission **Get** sur les secrets.
> - Les credentials Azure sont configurés (Managed Identity ou `AZURE_CLIENT_ID`, `AZURE_TENANT_ID`, `AZURE_CLIENT_SECRET`).

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

## 6) Déploiement (ex. Azure App Service, AKS)
- Assurez-vous que l'identité managée ou le Service Principal est configuré.
- Aucune variable d'environnement nécessaire pour la clé OpenAI.

---

## Bonnes pratiques
- Rotation des secrets via Azure Key Vault.
- IAM minimal.
- Ne jamais exposer la clé en clair.
