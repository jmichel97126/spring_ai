# Configuration Google Secret Manager avec Spring Boot + Spring AI

Ce guide explique comment stocker votre clé **OpenAI** dans **Google Secret Manager (GSM)** et la consommer automatiquement dans une application **Spring Boot** utilisant **Spring AI**.

---

## Prérequis
- Projet Google Cloud actif.
- `gcloud` installé et authentifié.
- Java 21+ recommandé.
- Service Account avec rôle `Secret Manager Secret Accessor`.

---

## 1) Création du secret via l'interface Google Cloud

1. Connectez-vous à [Google Cloud Console](https://console.cloud.google.com/).
2. Allez dans **Security > Secret Manager**.
3. Cliquez sur **Create Secret**.
4. Donnez un nom (ex: `openai-api-secret-key`).
5. Collez votre clé OpenAI dans le champ **Secret value**.
6. Cliquez sur **Create Secret**.

### Ajouter des permissions
- Allez dans l'onglet **Permissions** du secret.
- Ajoutez le compte de service avec le rôle `Secret Manager Secret Accessor`.

---

## 2) Création du secret via CLI

```bash
PROJECT_ID=YOUR_GCP_PROJECT_ID
SECRET_ID=openai-api-secret-key
OPENAI_KEY=sk-xxx_votre_cle_openai

gcloud config set project "$PROJECT_ID"
gcloud secrets create "$SECRET_ID" --replication-policy="automatic"
printf "%s" "$OPENAI_KEY" | gcloud secrets versions add "$SECRET_ID" --data-file=-
SERVICE_ACCOUNT_EMAIL=your-service-account@${PROJECT_ID}.iam.gserviceaccount.com
gcloud secrets add-iam-policy-binding "$SECRET_ID"   --member="serviceAccount:${SERVICE_ACCOUNT_EMAIL}"   --role="roles/secretmanager.secretAccessor"
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
    <groupId>com.google.cloud</groupId>
    <artifactId>spring-cloud-gcp-starter-secretmanager</artifactId>
    <version>3.7.0</version>
  </dependency>
</dependencies>
```

---

## 4) Configuration Spring Boot (`application.yml`)

```yaml
spring:
  cloud:
    gcp:
      project-id: ${GCP_PROJECT_ID}
      secretmanager:
        enabled: true
  ai:
    openai:
      api-key: sm://openai-api-secret-key
      chat:
        model: gpt-4o-mini
```

---

## ✅ Configuration simplifiée avec `application.properties`

Pour les démos ou projets rapides, vous pouvez utiliser une configuration minimale :

```properties
spring.config.import=sm://
spring.application.name=spring-gcp-secret-openai-key-demo
spring.ai.openai.api-key=${sm://openai-api-secret-key}
```

### Pourquoi cette approche ?
- **Rapide à mettre en place** : une seule ligne pour activer Secret Manager.
- **Moins de complexité** : pas besoin de YAML détaillé.
- **Compatible avec Spring Boot 3.x et Spring Cloud GCP**.

> Assurez-vous que :
> - Le secret `openai-api-secret-key` existe dans Google Secret Manager.
> - Le compte de service a le rôle `roles/secretmanager.secretAccessor`.
> - `spring.cloud.gcp.project-id` est défini ou `GOOGLE_APPLICATION_CREDENTIALS` est configuré.

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

## 6) Déploiement

```bash
gcloud run deploy my-spring-ai-app   --image=gcr.io/$PROJECT_ID/my-spring-ai-app:latest   --service-account=$SERVICE_ACCOUNT_EMAIL   --region=europe-west1   --set-env-vars=GCP_PROJECT_ID=$PROJECT_ID
```

---

## Bonnes pratiques
- Rotation des secrets.
- IAM minimal.
- Ne jamais exposer la clé en clair.
