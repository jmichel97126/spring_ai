# Utiliser Amazon Bedrock via la Console AWS (Navigateur uniquement)

Ce guide explique comment configurer et utiliser **Amazon Bedrock** sans CLI ni SDK, uniquement depuis l'interface web AWS, y compris la création d'un groupe d'utilisateurs IAM et la configuration d'une stratégie d'accès.

---

## ✅ Prérequis
- Un compte **AWS** actif
- Accès à la [Console AWS](https://aws.amazon.com/console/)
- Région supportant Amazon Bedrock (ex. `us-east-1` ou `us-west-2`)

---

## 🛠 Étape 1 : Accéder à Amazon Bedrock
1. Connectez-vous à la [Console AWS](https://aws.amazon.com/console/).
2. Dans la barre de recherche en haut, tapez **Bedrock**.
3. Cliquez sur **Amazon Bedrock** dans les résultats.

---

## ⚙️ Étape 2 : Activer Amazon Bedrock
1. Si c'est la première fois, cliquez sur **Get started**.
2. Vérifiez que vous êtes dans une région supportée.
3. Activez les permissions nécessaires pour votre compte.

---

## 👥 Étape 3 : Créer un groupe d'utilisateurs IAM
1. Dans la console AWS, recherchez **IAM** dans la barre de recherche.
2. Cliquez sur **Groupes d'utilisateurs** puis **Créer un groupe**.
3. Donnez un nom au groupe (ex. `BedrockUsers`).
4. Cliquez sur **Suivant** pour passer à l'étape des permissions.

---

## 🔐 Étape 4 : Créer et attacher une stratégie d'accès
1. Dans la section **Permissions**, cliquez sur **Créer une stratégie**.
2. Choisissez **JSON** et collez la stratégie suivante :
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "bedrock:InvokeModel",
        "bedrock:ListModels"
      ],
      "Resource": "*"
    }
  ]
}
```
3. Cliquez sur **Suivant**, donnez un nom à la stratégie (ex. `BedrockAccessPolicy`), puis **Créer la stratégie**.
4. Attachez cette stratégie au groupe `BedrockUsers`.

---

## 👤 Étape 5 : Ajouter des utilisateurs au groupe
1. Dans IAM, allez dans **Utilisateurs**.
2. Créez un utilisateur ou sélectionnez-en un existant.
3. Ajoutez-le au groupe `BedrockUsers`.

---

## 🧪 Étape 6 : Tester via le Playground
1. Retournez dans la console Bedrock.
2. Ouvrez **Playground**.
3. Sélectionnez un modèle (ex. Claude, Titan, Llama).
4. Entrez votre prompt et cliquez sur **Run**.

> Vous pouvez ajuster les paramètres (température, longueur de réponse) directement dans le Playground.

---

## 📚 Ressources utiles
- [Documentation Amazon Bedrock](https://docs.aws.amazon.com/bedrock/latest/userguide/what-is-bedrock.html)
- [Guide IAM](https://docs.aws.amazon.com/IAM/latest/UserGuide/introduction.html)


---

## 🔑 Étape 7 : Créer une clé d'accès IAM
1. Dans la console AWS, allez dans **IAM** > **Utilisateurs**.
2. Sélectionnez l'utilisateur que vous avez ajouté au groupe `BedrockUsers`.
3. Cliquez sur **Créer une clé d'accès**.
4. Choisissez **Type d'accès : Programmatique**.
5. Cliquez sur **Créer une clé**.
6. Copiez et enregistrez :
   - **Access Key ID**
   - **Secret Access Key**

> ⚠️ Conservez ces informations en lieu sûr. Elles seront nécessaires pour toute intégration future (SDK, API).


---

## 🔑 Étape 8 : Créer une clé d'accès pour un accès local
Cette clé permettra à l'utilisateur d'accéder à Amazon Bedrock depuis un environnement local (ex. SDK, outils en ligne de commande).

1. Dans la console AWS, allez dans **IAM** > **Utilisateurs**.
2. Sélectionnez l'utilisateur que vous avez ajouté au groupe `BedrockUsers`.
3. Cliquez sur **Onglet : Informations d'identification de sécurité**.
4. Cliquez sur **Créer une clé d'accès**.
5. Choisissez **Type d'accès : Programmatique**.
6. Cliquez sur **Créer une clé**.
7. Copiez et enregistrez :
   - **Access Key ID**
   - **Secret Access Key**

> ⚠️ Conservez ces informations en lieu sûr. Elles seront nécessaires pour configurer votre environnement local (ex. fichier `~/.aws/credentials`).

### ✅ Exemple de configuration locale
Ajoutez ces informations dans le fichier `~/.aws/credentials` :
```ini
[default]
aws_access_key_id = VOTRE_ACCESS_KEY_ID
aws_secret_access_key = VOTRE_SECRET_ACCESS_KEY
region = us-east-1
```
