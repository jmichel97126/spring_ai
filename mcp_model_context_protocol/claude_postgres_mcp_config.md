# Guide : Configuration Claude MCP avec Docker pour PostgreSQL

## 🎯 Objectif
Configurer **Claude Desktop** pour lancer **PostgreSQL** dans un conteneur Docker via MCP, avec persistance des données et variables d'environnement sécurisées.

---

## ✅ Pré-requis
- Docker installé
- Claude Desktop configuré
- Variable d'environnement pour mot de passe PostgreSQL

---

## 🔧 Configuration MCP Servers (claude_desktop_config.json)
```json
{
  "mcpServers": {
    "postgres": {
      "type": "docker",
      "image": "postgres:latest",
      "ports": [{"containerPort": 5432, "hostPort": 5432}],
      "volumes": [{"hostPath": "./postgres-data", "containerPath": "/var/lib/postgresql/data"}],
      "env": {
        "POSTGRES_USER": "admin",
        "POSTGRES_PASSWORD": "${POSTGRES_PASSWORD}",
        "POSTGRES_DB": "mydatabase"
      }
    }
  }
}
```

### 🔍 Explications
- **image** : image officielle PostgreSQL
- **ports** : expose le port 5432
- **volumes** : persistance des données PostgreSQL
- **env** : variables pour utilisateur, mot de passe et base par défaut

---

## 🚀 Exemples Claude MCP : Interagir avec PostgreSQL

### ✅ 1) Vérifier la connexion
```text
MCP: Teste la connexion à PostgreSQL sur `localhost:5432` avec l'utilisateur `admin` et la base `mydatabase`.
```

### ✅ 2) Créer une table
```text
MCP: Exécute la requête SQL :
CREATE TABLE IF NOT EXISTS employees (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  role VARCHAR(50)
);
```

### ✅ 3) Insérer des données
```text
MCP: Exécute la requête SQL :
INSERT INTO employees (name, role) VALUES ('Alice', 'Developer');
```

### ✅ 4) Lire les données
```text
MCP: Exécute la requête SQL :
SELECT * FROM employees;
```

---

## ✅ Bonnes pratiques
- Utiliser des mots de passe forts et les injecter via variables d'environnement
- Versionner la configuration MCP mais **jamais** les secrets
- Sauvegarder le volume `postgres-data` pour la persistance
- Séparer les environnements (dev, staging, prod) avec des ports et bases distincts
