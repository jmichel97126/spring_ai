# Guide : Préparation et Configuration MCP avec Claude Desktop

## ✅ Introduction
**MCP (Model Context Protocol)** est un standard permettant aux modèles IA (comme Claude) d’interagir avec des outils et des données externes de manière sécurisée et structurée.

**Claude Desktop** est l’application officielle d’Anthropic pour utiliser Claude en local avec des fonctionnalités avancées, dont le support MCP.

---

## ✅ Pré-requis
1. **Installer Claude Desktop** :
   - Téléchargez depuis [https://claude.ai/download](https://claude.ai/download).
   - Installez l’application sur votre système (Windows, macOS).

2. **Créer un compte Anthropic** :
   - Rendez-vous sur [https://claude.ai](https://claude.ai) et connectez-vous.

3. **Obtenir une clé API Anthropic** (si nécessaire pour certaines intégrations) :
   - Accédez à [https://console.anthropic.com](https://console.anthropic.com).

4. **Environnement** :
   - Node.js (si vous utilisez des outils MCP basés sur JavaScript).
   - Variables d’environnement pour sécuriser vos clés.

---

## ✅ Étapes de Configuration
### 1. Activer MCP dans Claude Desktop
- Ouvrez Claude Desktop.
- Allez dans **Paramètres > Intégrations > MCP**.
- Activez le support MCP.

### 2. Définir les outils MCP
- Créez un fichier `mcp.json` ou utilisez l’interface graphique.
- Exemple :
```json
{
  "tools": [
    {
      "name": "searchDocs",
      "description": "Recherche dans la documentation",
      "endpoint": "http://localhost:3000/search"
    }
  ]
}
```

### 3. Gérer les permissions
- Vérifiez que chaque outil a les autorisations nécessaires.
- Claude vous demandera de confirmer avant d’appeler un outil.

---

## ✅ Vérification et Test
- Lancez Claude Desktop.
- Demandez à Claude d’utiliser un outil MCP (ex. `searchDocs`).
- Vérifiez les logs pour confirmer l’appel.

---

## ✅ Liens Utiles
- [Documentation MCP](https://modelcontextprotocol.io)
- [Claude Desktop](https://claude.ai)
- [Anthropic API](https://docs.anthropic.com)

---
**Astuce** : Combinez MCP avec RAG pour enrichir les réponses de Claude avec vos données locales.


---

## ✅ Exemples d'Actions Locales avec MCP

### 1. Lire un fichier local
Configuration MCP :
```json
{
  "tools": [
    {
      "name": "readFile",
      "description": "Lire le contenu d'un fichier local",
      "command": "cat /path/to/file.txt"
    }
  ]
}
```
Prompt pour Claude :
```
Utilise l'outil `readFile` pour afficher le contenu du fichier /path/to/file.txt.
```

### 2. Lancer un script shell
Configuration MCP :
```json
{
  "tools": [
    {
      "name": "runScript",
      "description": "Exécuter un script shell local",
      "command": "bash /path/to/script.sh"
    }
  ]
}
```
Prompt pour Claude :
```
Exécute le script local via l'outil `runScript`.
```

### 3. Accéder à une base SQLite
Configuration MCP :
```json
{
  "tools": [
    {
      "name": "querySQLite",
      "description": "Exécuter une requête sur une base SQLite",
      "command": "sqlite3 /path/to/database.db 'SELECT * FROM users;'"
    }
  ]
}
```
Prompt pour Claude :
```
Utilise l'outil `querySQLite` pour récupérer tous les utilisateurs.
```

**Astuce** : Ces actions doivent être validées par l'utilisateur avant exécution pour des raisons de sécurité.
