# Générer une clé API Mistral AI

Ce guide explique comment obtenir une clé API pour utiliser **Mistral AI** dans vos projets.

## ✅ Étape 1 : Créer un compte Mistral AI
1. Rendez-vous sur [https://console.mistral.ai](https://console.mistral.ai).
2. Cliquez sur **Sign Up** et créez un compte (ou connectez-vous si vous en avez déjà un).

## ✅ Étape 2 : Accéder au Dashboard
1. Une fois connecté, allez dans la section **API Keys** du tableau de bord.
2. Cliquez sur **Create API Key**.

## ✅ Étape 3 : Générer la clé API
- Donnez un nom à votre clé (ex. `spring-boot-app`).
- Cliquez sur **Generate**.
- Copiez la clé générée et conservez-la en lieu sûr.

⚠️ **Important** : Ne partagez jamais votre clé API publiquement.

## ✅ Étape 4 : Configurer la clé dans Spring Boot
Ajoutez la clé dans votre fichier `application.properties` :

```properties
spring.ai.mistralai.api-key=${MISTRALAI_API_KEY}
```

Ou définissez-la comme variable d'environnement :

```bash
export MISTRALAI_API_KEY=your_api_key
```

## ✅ Étape 5 : Vérifier l'intégration
Utilisez le starter **Spring AI Mistral** dans votre projet pour interagir avec le modèle.

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-model-mistral-ai</artifactId>
</dependency>
```

Ensuite, vous pouvez appeler l'API via le `ChatClient` ou `EmbeddingClient`.

---
**Documentation officielle** : [https://docs.spring.io/spring-ai/reference/mistral.html](https://docs.spring.io/spring-ai/reference/mistral.html)
