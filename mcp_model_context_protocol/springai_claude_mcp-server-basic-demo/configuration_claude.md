# Guide MCP pour Claude Desktop sous Windows

## ✅ Introduction
Ce guide explique comment configurer **Claude Desktop** sous **Windows** pour exécuter un serveur MCP Java via le JAR `mcp-server-basic-demo.jar`.

---

## ✅ Emplacement du fichier de configuration
Sous Windows, le fichier de configuration Claude Desktop se trouve ici :
```
%APPDATA%\Claude\claude_desktop_config.json
```

---

## ✅ Configuration JSON pour exécuter le JAR
Voici un exemple complet de configuration :
```json
{
  "mcpServers": {
    "basic-demo": {
      "command": "java.exe",
      "args": [
        "-jar",
        "C:\absolute\path\to\mcp-server-basic-demo.jar"
      ],
      "env": {
        "JAVA_TOOL_OPTIONS": "-Xms256m -Xmx512m"
      },
      "autoStart": true
    }
  }
}
```

### Conseils :
- Remplacez `C:\absolute\path\to\mcp-server-basic-demo.jar` par le chemin complet vers votre JAR.
- Si Java n’est pas dans le PATH, utilisez le chemin complet vers `java.exe` (ex. `C:\Program Files\Java\jdk-21\bin\java.exe`).
- `autoStart: true` démarre le serveur automatiquement au lancement de Claude Desktop.

---

## ✅ Exemples de prompts pour Claude
Après avoir redémarré Claude Desktop :

### 1. Lister les outils disponibles
```
Liste les outils disponibles via le serveur MCP `basic-demo`.
```

### 2. Appeler un outil spécifique
```
Utilise l’outil du serveur MCP `basic-demo`(en passant un argument si necessaire: give me list of books by author pritest mistry).
```

### 3. Vérifier le statut du serveur
```
Demande au serveur MCP `basic-demo` son état actuel.
```

---

## ✅ Liens utiles
- [Documentation MCP](https://modelcontextprotocol.io)
- [Claude Desktop](https://claude.ai)
- [Anthropic API](https://docs.anthropic.com)

**Astuce** : Vous pouvez ajouter plusieurs serveurs MCP dans le même fichier JSON si nécessaire.
