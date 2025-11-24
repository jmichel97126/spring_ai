# Guide : Création d’un compte MongoDB et configuration d’une base vectorielle via MongoDB Atlas

## 1. **Créer un compte MongoDB**
1. Rendez-vous sur https://www.mongodb.com/atlas/database.
2. Cliquez sur **Sign Up**.
3. Choisissez une méthode d’inscription :
   - **Email + Mot de passe**
   - **Google** ou **GitHub**
4. Validez votre email si nécessaire.

---

## 2. **Créer un projet et un cluster**
1. Une fois connecté, cliquez sur **New Project**.
2. Donnez un nom à votre projet (ex. : `VectorDBProject`).
3. Cliquez sur **Create Project**.
4. Dans le projet, cliquez sur **Build a Database**.
5. Choisissez un fournisseur (AWS, Azure, GCP) et une région.
6. Sélectionnez le plan (Free Tier ou payant).
7. Cliquez sur **Create Cluster**.

---

## 3. **Configurer l’accès et la sécurité**
1. **Créer un utilisateur de base de données** :
   - Allez dans **Database Access**.
   - Cliquez sur **Add New Database User**.
   - Définissez un nom d’utilisateur et un mot de passe.
   - Attribuez le rôle `Atlas Admin` ou `Read/Write`.
2. **Autoriser votre IP** :
   - Allez dans **Network Access**.
   - Cliquez sur **Add IP Address**.
   - Ajoutez votre IP ou `0.0.0.0/0` (pour accès global).
3. **Récupérer l’URI** :
    - Une fois le cluster prêt, cliquez sur **Connect** → **Connect your application** → copiez l’URI.

Exemple d’URI :
```
mongodb+srv://<username>:<password>@<cluster-url>/test
```
---

## 4. **Créer la base vectorielle `vector_store`**
1. Allez dans **Clusters** → **Browse Collections**.
2. Cliquez sur **Create Database**.
3. Donnez le nom `vector_store` à la base.
4. Créez une collection nommée `embeddings`.

---

## 5. **Activer la recherche vectorielle (Atlas Search)**
1. Allez dans **Atlas Search** → **Create Index**.
2. Sélectionnez la collection `embeddings`.
3. Configurez un index nommé vector_index avec un champ `embedding` de type `knnVector`.
4. Exemple de configuration JSON :
   ```json
   {
     "mappings": {
       "dynamic": false,
       "fields": {
         "embedding": {
           "type": "knnVector",
           "dimensions": 768,
           "similarity": "cosine"
         }
       }
     }
   }
   ```

