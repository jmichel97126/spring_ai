# Utiliser Google Vertex AI via la CLI (gcloud)

Ce guide explique comment configurer et utiliser **Google Vertex AI** via la ligne de commande (CLI) avec `gcloud`, y compris la génération d'une clé JSON pour un usage local.

---

## ✅ Prérequis
- Un compte **Google Cloud** actif ([Inscription](https://cloud.google.com))
- **Google Cloud SDK** installé ([Installer gcloud](https://cloud.google.com/sdk/docs/install))
- Authentification configurée :
```bash
gcloud auth login
```

---

## 6e0 Étape 1 : Créer un projet Google Cloud
```bash
gcloud projects create vertex-demo --name="vertex-demo"
```
Définir le projet par défaut :
```bash
gcloud config set project vertex-demo
```

---

## ⚙️ Étape 2 : Activer l'API Vertex AI
```bash
gcloud services enable aiplatform.googleapis.com
```

---

## 465 Étape 3 : Configurer les permissions IAM
Ajouter un rôle à un utilisateur ou compte de service :
```bash
gcloud projects add-iam-policy-binding vertex-demo   --member="user:email@example.com"   --role="roles/aiplatform.user"
```

---

## 511 Étape 4 : Générer une clé JSON pour usage local
Créer un compte de service :
```bash
gcloud iam service-accounts create vertex-local-access --display-name="Vertex Local Access"
```
Générer une clé :
```bash
gcloud iam service-accounts keys create ~/vertex-key.json   --iam-account=vertex-local-access@vertex-demo.iam.gserviceaccount.com
```
Configurer la variable d'environnement :
```bash
export GOOGLE_APPLICATION_CREDENTIALS="~/vertex-key.json"
```

---

## 9ea Étape 5 : Tester Vertex AI via CLI
Lister les modèles disponibles :
```bash
gcloud ai models list --region=us-central1
```
Exécuter une prédiction (exemple avec text-bison) :
```bash
gcloud ai endpoints predict ENDPOINT_ID   --region=us-central1   --json-request='{"instances": [{"content": "Bonjour Vertex AI"}]}'
```

---

## ✅ Bonnes pratiques
- Ne partagez jamais votre clé JSON publiquement.
- Utilisez des variables d'environnement pour la stocker.
- Révoquez les clés inutilisées avec :
```bash
gcloud iam service-accounts keys delete KEY_ID --iam-account=vertex-local-access@vertex-demo.iam.gserviceaccount.com
```

---

## 4da Ressources utiles
- [Documentation Vertex AI](https://cloud.google.com/vertex-ai/docs)
- [Google Cloud SDK](https://cloud.google.com/sdk/docs)
