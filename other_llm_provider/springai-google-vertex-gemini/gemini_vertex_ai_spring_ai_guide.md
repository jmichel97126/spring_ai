# Guide : Utiliser Gemini Chat (Vertex AI) depuis un projet Spring Boot avec Spring AI

## 🎯 Objectif
Ce guide explique comment intégrer **Gemini Chat (Vertex AI)** dans une application **Spring Boot** via **Spring AI**, avec configuration, dépendances, exemples de code et tests.Référence officielle Spring AI (Vertex AI Gemini) et Google Cloud sont citées au fil du document. citeturn16search16turn16search25turn16search18

---

## ✅ Pré-requis
- Compte **Google Cloud** et **facturation** activée. Activer l’API **Vertex AI**. citeturn16search18
- Installer le **gcloud CLI**, se **connecter en ADC** (Application Default Credentials) :
  ```bash
  gcloud config set project <PROJECT_ID>
  gcloud auth application-default login
  ```
  (Nécessaire pour l’auth Vertex AI côté Spring AI). citeturn16search16
- Java 17+ / Spring Boot 3.x / Maven ou Gradle.

> ⚠️ Variante API clé (Gemini Developer API) pour prototypage rapide : voir l’impl de **Google GenAI** de Spring AI, qui supporte soit API Key soit Vertex AI. citeturn16search27

---

## ⚙️ Dépendances Spring AI (Vertex AI Gemini)
Ajoutez la **BOM Spring AI** et le starter **Vertex AI Gemini** (artifact pouvant varier selon version ; voir doc Spring AI). citeturn16search16turn16search25

**Maven (exemple)**
```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.springframework.ai</groupId>
      <artifactId>spring-ai-bom</artifactId>
      <version>1.0.0</version> <!-- remplacez par la version actuelle -->
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-model-vertex-ai-gemini</artifactId>
  </dependency>
</dependencies>
```
> Les noms d’artifacts ont évolué récemment ; vérifiez la doc Spring AI pour les versions/starter à jour. citeturn16search16turn16search25

**Gradle (Kotlin DSL)**
```kotlin
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.ai:spring-ai-starter-model-vertex-ai-gemini")
}
```

---

## 🔧 Configuration `application.yml`
Déclarez votre **Project ID** et la **région** Vertex AI, et activez le transport (gRPC par défaut) : citeturn16search16
```yaml
spring:
  ai:
    model:
      chat: vertexai   # active l’auto-config pour Vertex AI Gemini
    vertex:
      ai:
        gemini:
          project-id: ${GCP_PROJECT_ID}
          location: us-east4
          transport: GRPC # ou REST
          # credentials-uri: file:/path/to/adc.json  # optionnel si vous ne voulez pas ADC via gcloud
          chat:
            options:
              model: gemini-2.0-flash   # choisissez le modèle (ex: 2.0-flash, 2.0-pro si dispo)
              temperature: 0.7
```
*Propriétés supportées et préfixes :* `spring.ai.model.chat`, `spring.ai.vertex.ai.gemini.*`. citeturn16search16

---

## 🧱 Beans et service (ChatClient)
Le **ChatClient** de Spring AI fournit une API fluide pour interroger Gemini. citeturn16search16

```java
// src/main/java/com/example/ai/LLMConfig.java
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {
  @Bean
  ChatClient chatClient(ChatClient.Builder builder) {
    return builder.build();
  }
}
```

```java
// src/main/java/com/example/ai/AiService.java
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {
  private final ChatClient ai;
  public AiService(ChatClient ai) { this.ai = ai; }

  public String askGemini(String userMessage) {
    return ai.prompt()             // construit une requête
            .user(userMessage)     // message utilisateur
            .call()                // exécute
            .content();            // récupère le texte
  }
}
```

```java
// src/main/java/com/example/ai/AiController.java
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class AiController {
  private final AiService ai;

  @PostMapping
  public String chat(@RequestBody String message) {
    return ai.askGemini(message);
  }
}
```

---

## 🧪 Test rapide
1. Authentifiez-vous via **gcloud** (ADC). citeturn16search18
2. Démarrez l’app : `mvn spring-boot:run`
3. Testez :
   ```bash
   curl -X POST http://localhost:8080/api/chat      -H "Content-Type: text/plain"      -d "Donne-moi 3 cas d’usage de Gemini pour une app Spring"
   ```

---

## 🧩 Prompts utiles
- *Système* (via options avancées) : définir un rôle/consignes pour l’assistant. citeturn16search17
- *Multimodal* : certains modèles Gemini acceptent texte+images/vidéo. (supporté côté Vertex AI Gemini). citeturn16search16

---

## 🔐 Sécurité & quotas
- Utilisez **ADC** ou des identifiants de service avec rôles *Vertex AI User* (`roles/aiplatform.user`). citeturn16search18
- Gérez le **coût** (quota/billing) côté GCP ; sélectionnez les modèles (Flash vs Pro) selon latence/coût. citeturn16search18

---

## 🛠️ Dépannage
- **401/permission** : vérifier `gcloud auth application-default login` et le `project-id`. citeturn16search18
- **Transport** : basculer `transport: REST` si gRPC pose problème dans votre infra. citeturn16search16
- **Artifacts** : les noms de starters ont évolué ; croisez avec la doc Spring AI actuelle. citeturn16search25

---

## 📚 Liens
- *Spring AI – Vertex AI Gemini Chat* : [docs.spring.io](citeturn16search16)
- *Spring AI – source doc (Antora)* : [GitHub (adoc)](citeturn16search25)
- *Google – Vertex AI Gemini Quickstart* : [docs.cloud.google.com](citeturn16search18)
- *Alternative API Key (Google GenAI)* : [docs.spring.io – Google GenAI Chat](citeturn16search27)

