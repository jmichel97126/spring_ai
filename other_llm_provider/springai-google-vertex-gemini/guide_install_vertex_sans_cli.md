# Utiliser Google Vertex AI via l'interface Web (Navigateur uniquement)

Ce guide explique comment configurer et utiliser **Google Vertex AI** sans CLI ni SDK, uniquement depuis l'interface web, y compris la génération d'une clé API pour un usage local.

---

## ✅ Prérequis
- Un compte **Google Cloud** actif ([Inscription](https://cloud.google.com))
- Accès à la [Console Google Cloud](https://console.cloud.google.com)

---

## 🛠 Étape 1 : Créer un projet Google Cloud
1. Connectez-vous à la [Console Google Cloud](https://console.cloud.google.com).
2. Cliquez sur **Sélectionner un projet** > **Nouveau projet**.
3. Donnez un nom (ex. `vertex-demo`) et validez.

---

## ⚙️ Étape 2 : Activer l'API Vertex AI
1. Dans la barre de recherche, tapez **Vertex AI**.
2. Cliquez sur **Activer l'API Vertex AI**.

---

## 👥 Étape 3 : Configurer les permissions IAM
1. Allez dans **IAM & Admin** > **IAM**.
2. Ajoutez un utilisateur ou un compte de service.
3. Assignez le rôle **Vertex AI User**.

---

## 🔑 Étape 4 : Générer une clé API pour usage local
1. Allez dans **IAM & Admin** > **Comptes de service**.
2. Créez un compte de service (ex. `vertex-local-access`).
3. Cliquez sur **Ajouter une clé** > **Créer une clé**.
4. Choisissez **JSON** et téléchargez le fichier.

> ⚠️ Conservez ce fichier en lieu sûr. Il sera nécessaire pour toute intégration locale.

### ✅ Exemple de configuration locale
Ajoutez la variable d'environnement :
```bash
export GOOGLE_APPLICATION_CREDENTIALS="/chemin/vers/votre-cle.json"
```

---

## 🧪 Étape 5 : Tester via le Playground
1. Dans la console Vertex AI, ouvrez **Playground**.
2. Sélectionnez un modèle (ex. `text-bison`, `chat-bison`).
3. Entrez votre prompt et cliquez sur **Run**.
4. Ajustez les paramètres si nécessaire.

---

## ✅ Bonnes pratiques
- Ne partagez jamais votre clé JSON publiquement.
- Utilisez des variables d'environnement pour la stocker.
- Révoquez les clés inutilisées depuis la console.

---

## 📚 Ressources utiles
- [Documentation Vertex AI](https://cloud.google.com/vertex-ai/docs)
- [Playground Vertex AI](https://console.cloud.google.com/vertex-ai)
