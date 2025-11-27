# Guide d'installation d'un projet Hilla en local


## ℹ️ Qu'est-ce que Hilla ?
Hilla est un **framework moderne** pour créer des applications web full-stack en combinant **Java (Spring Boot)** et **TypeScript**. Il est basé sur Vaadin et offre :

### ✅ Avantages principaux
- **Intégration transparente** entre backend Java et frontend TypeScript.
- **Sécurité intégrée** avec gestion des endpoints.
- **Productivité accrue** grâce à des outils de génération de code et des composants UI prêts à l'emploi.
- **Support natif pour TypeScript et React**.

### 🔍 Cas d'utilisation
Hilla est idéal pour :
- Applications d'entreprise avec logique métier complexe.
- Interfaces modernes basées sur React.
- Projets nécessitant une forte intégration entre Java et frontend.

Pour en savoir plus : [Documentation officielle Hilla](https://hilla.dev/docs)


## ✅ Prérequis
Avant de commencer, assurez-vous d'avoir installé :
- **Java 17 ou supérieur** (JDK)
- **Node.js** (version LTS recommandée)
- **npm** ou **pnpm**
- **Git**

## 📥 Étapes d'installation
1. **Cloner le projet depuis GitHub** :
   ```bash
   git clone https://github.com/votre-utilisateur/nom-du-projet.git
   cd nom-du-projet
   ```

2. **Installer les dépendances frontend** :
   ```bash
   npm install
   ```
   ou si vous utilisez **pnpm** :
   ```bash
   pnpm install
   ```

3. **Compiler le projet Java** :
   ```bash
   ./mvnw clean install
   ```

## ▶️ Lancer le projet
Pour démarrer le serveur Hilla :
```bash
./mvnw
```
Cela démarre l'application sur [http://localhost:8080](http://localhost:8080).

## ✅ Vérification
- Ouvrez votre navigateur et accédez à `http://localhost:8080`.
- Vous devriez voir l'interface Hilla fonctionner.

## 🔍 Conseils
- Si vous avez des erreurs liées à Node.js, vérifiez la version avec :
  ```bash
  node -v
  ```
- Pour relancer après modification du code, utilisez :
  ```bash
  ./mvnw
  ```

## 📚 Ressources utiles
- [Documentation officielle Hilla](https://hilla.dev/docs)
- [Guide Maven](https://maven.apache.org/guides/index.html)
