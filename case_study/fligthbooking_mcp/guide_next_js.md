# 🚀 Installation et Lancement du Projet Next.js

## ✅ Prérequis
Avant de commencer, assurez-vous d’avoir :
- **Node.js** (version recommandée : LTS) → [Télécharger Node.js](https://nodejs.org/)
- **npm** ou **yarn** installé
- **Git** pour cloner le projet

---

## 📥 1. Cloner le projet
```bash
git clone <URL_DU_REPO>
cd <NOM_DU_PROJET>
```

---

## 📦 2. Installer les dépendances
Avec **npm** :
```bash
npm install
```

Ou avec **yarn** :
```bash
yarn install
```

---

## ▶️ 3. Lancer le projet en mode développement
```bash
npm run dev
```
Ou :
```bash
yarn dev
```

Le projet sera accessible sur :  
[http://localhost:3000](http://localhost:3000)

---

## 🏗 4. Build pour la production
```bash
npm run build
npm start
```

---

## 🔍 5. Vérifier la configuration
- Fichier **`.env.local`** pour les variables d’environnement
- Vérifier la version de **Next.js** dans `package.json`

---

## ✅ Commandes utiles
- `npm run lint` → Vérifier le code
- `npm run test` → Lancer les tests (si configurés)
