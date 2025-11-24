# Créer une instance Azure OpenAI via le Portail Azure

Ce guide explique comment créer une instance **Azure OpenAI** dans un groupe de ressources nommé `rg_azureopenia` en utilisant uniquement l'interface web du portail Azure.

---

## ✅ Prérequis
- Un compte **Azure** actif
- Accès au [Portail Azure](https://portal.azure.com)

---

## 🛠 Étape 1 : Accéder au service Azure OpenAI
1. Connectez-vous au [Portail Azure](https://portal.azure.com).
2. Dans la barre de recherche en haut, tapez **Azure OpenAI**.
3. Cliquez sur **Azure OpenAI** dans les résultats.

---

## 📂 Étape 2 : Créer le groupe de ressources
1. Si vous n’avez pas encore de groupe, cliquez sur **Créer un groupe de ressources**.
2. Renseignez :
   - **Nom** : `rg_azureopenia`
   - **Région** : *East US* (ou une région supportée par Azure OpenAI)
3. Cliquez sur **Vérifier + créer**, puis **Créer**.

---

## ⚙️ Étape 3 : Créer l’instance Azure OpenAI
1. Cliquez sur **Créer une ressource** (ou **Créer** depuis la page Azure OpenAI).
2. Paramétrez :
   - **Abonnement** : votre abonnement Azure
   - **Groupe de ressources** : `rg_azureopenia`
   - **Nom** : par exemple `azure-openai-instance`
   - **Région** : *East US*
   - **Tarification** : S0
3. Cliquez sur **Vérifier + créer**, puis **Créer**.

---

## 🔑 Étape 4 : Récupérer les clés et l’endpoint
1. Une fois la ressource déployée, ouvrez-la.
2. Allez dans **Clés et points de terminaison**.
3. Copiez :
   - **Clé API**
   - **Endpoint**

---

## ✅ Étape 5 : Déployer un modèle
1. Dans la ressource, allez dans **Déploiements**.
2. Cliquez sur **Créer un déploiement**.
3. Choisissez un modèle (ex. `gpt-4`, `gpt-35-turbo`).
4. Donnez un nom au déploiement (ex. `gpt4-deployment`).
5. Cliquez sur **Créer**.

---

## 🧪 Étape 6 : Tester depuis le portail
- Utilisez **Playground** pour tester vos prompts directement dans le navigateur.

---

## 📚 Ressources utiles
- [Documentation Azure OpenAI](https://learn.microsoft.com/fr-fr/azure/ai-services/openai/)
- [Régions supportées](https://learn.microsoft.com/fr-fr/azure/ai-services/openai/concepts/models)
