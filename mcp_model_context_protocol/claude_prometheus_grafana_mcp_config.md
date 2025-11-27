# Guide : Intégration Prometheus & Grafana avec Claude MCP et Spring Boot

## 🎯 Objectif
Mettre en place un monitoring complet avec **Prometheus** et **Grafana** via Docker, intégré à **Claude MCP** et un projet **Spring Boot**.

---

## ✅ Pré-requis
- Docker installé
- Claude Desktop configuré
- Projet Spring Boot avec Actuator

---

## 🔧 Configuration MCP Servers (claude_desktop_config.json)
```json
{
  "mcpServers": {
    "prometheus": {
      "type": "docker",
      "image": "prom/prometheus:latest",
      "command": ["/bin/prometheus"],
      "args": ["--config.file=/etc/prometheus/prometheus.yml", "--storage.tsdb.path=/prometheus", "--web.enable-lifecycle"],
      "ports": [{"containerPort": 9090, "hostPort": 9090}],
      "volumes": [{"hostPath": "./prometheus.yml", "containerPath": "/etc/prometheus/prometheus.yml"}]
    },
    "grafana": {
      "type": "docker",
      "image": "grafana/grafana:latest",
      "ports": [{"containerPort": 3000, "hostPort": 3000}],
      "volumes": [{"hostPath": "./grafana", "containerPath": "/var/lib/grafana"}],
      "env": {
        "GF_SECURITY_ADMIN_USER": "admin",
        "GF_SECURITY_ADMIN_PASSWORD": "admin",
        "GF_SECURITY_API_TOKEN": "${GRAFANA_API_TOKEN}"
      }
    }
  }
}
```

---

## 🚀 Intégration Spring Boot
### Dépendances Maven
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
  <groupId>io.micrometer</groupId>
  <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

### application.properties
```properties
management.endpoints.web.exposure.include=health,info,prometheus
management.metrics.export.prometheus.enabled=true
```

### Exemple de contrôleur
```java
@RestController
public class AiController {
  @GetMapping("/ai/status")
  public String status() { return "Spring AI service is running"; }
}
```

---

## 📄 prometheus.yml
```yaml
global:
  scrape_interval: 15s
scrape_configs:
  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['host.docker.internal:8080']
```

---

## 📊 Provisioning Grafana
Créez `datasource.yml` :
```yaml
datasources:
  - name: Prometheus
    type: prometheus
    access: proxy
    url: http://host.docker.internal:9090
    isDefault: true
```

---

## 🔐 Authentification API Grafana
### Définir le token dans Docker
`GF_SECURITY_API_TOKEN` est injecté via l'environnement du conteneur.

### Utilisation avec curl
```bash
curl -X POST http://localhost:3000/api/datasources   -H "Authorization: Bearer $GRAFANA_API_TOKEN"   -H "Content-Type: application/json"   -d '{"name":"Prometheus","type":"prometheus","access":"proxy","url":"http://host.docker.internal:9090","isDefault":true}'
```

---

## 🧪 Tests MCP
- Vérifier santé Spring Boot : `GET http://localhost:8080/actuator/health`
- Vérifier métriques : `GET http://localhost:8080/actuator/prometheus`
- Targets Prometheus : `GET http://localhost:9090/api/v1/targets`
- Requête PromQL : `GET http://localhost:9090/api/v1/query?query=up`
- Créer dashboard Grafana via API avec `$GRAFANA_API_TOKEN`

---

## ✅ Bonnes pratiques
- Utiliser HTTPS et tokens API en production
- Versionner prometheus.yml et provisioning Grafana
- Séparer environnements (dev, staging, prod) avec des ports distincts

---

## 📂 Comment utiliser `datasource.yml`

Le fichier `datasource.yml` permet de **provisionner automatiquement la datasource Prometheus dans Grafana** au démarrage.

### ✅ Étapes :
1. **Créer le fichier** dans votre projet :
   ```bash
   mkdir -p grafana/provisioning/datasources
   nano grafana/provisioning/datasources/datasource.yml
   ```

2. **Contenu minimal** :
   ```yaml
   datasources:
     - name: Prometheus
       type: prometheus
       access: proxy
       url: http://host.docker.internal:9090
       isDefault: true
   ```

3. **Monter le dossier dans le conteneur Grafana** via la configuration MCP ou docker-compose :
   ```json
   "volumes": [
     { "hostPath": "./grafana/provisioning", "containerPath": "/etc/grafana/provisioning" }
   ]
   ```

4. **Redémarrer Grafana** : le provisioning sera appliqué automatiquement.

### 🔑 Avantages :
- **Automatisation** : pas besoin d'appeler l'API Grafana pour créer la datasource.
- **Versioning** : vous pouvez inclure ce fichier dans votre dépôt Git.
- **Infrastructure as Code** : configuration déclarative et reproductible.

> 💡 Conseil : combinez cette approche avec le provisioning des dashboards pour un setup complet.

---

## 🖥️ Exemples Claude MCP : Créer un Dashboard Grafana pour JVM Metrics

Voici des prompts que vous pouvez utiliser dans Claude pour automatiser la création d'un dashboard Grafana affichant les métriques JVM d'une application Spring Boot.

### ✅ 1) Créer un dashboard avec panels pour JVM
```text
MCP: Envoie une requête POST sur `http://localhost:3000/api/dashboards/db` avec l'auth Bearer `$GRAFANA_API_TOKEN` et le JSON suivant :
{
  "dashboard": {
    "title": "Spring Boot JVM Metrics",
    "panels": [
      {
        "type": "graph",
        "title": "JVM Memory Used",
        "datasource": "Prometheus",
        "targets": [{"expr": "jvm_memory_used_bytes"}],
        "gridPos": {"x": 0, "y": 0, "w": 12, "h": 8}
      },
      {
        "type": "graph",
        "title": "JVM Threads Live",
        "datasource": "Prometheus",
        "targets": [{"expr": "jvm_threads_live"}],
        "gridPos": {"x": 12, "y": 0, "w": 12, "h": 8}
      }
    ]
  },
  "overwrite": true
}
Retourne le `slug` du dashboard.
```

### ✅ 2) Vérifier le dashboard créé
```text
MCP: Fais une requête GET sur `http://localhost:3000/api/search?query=Spring Boot JVM Metrics` et confirme que le dashboard existe.
```

### ✅ 3) Exécuter des requêtes PromQL pour tester les panels
```text
MCP: Appelle `http://localhost:9090/api/v1/query?query=jvm_memory_used_bytes` et affiche les 5 premières séries avec leurs labels.
```

### ✅ 4) Ajouter un panel CPU (optionnel)
```text
MCP: Ajoute un panel au dashboard existant avec la métrique `system_cpu_usage`.
```

> 💡 Conseil : Vous pouvez enrichir le dashboard avec des panels pour GC, heap, threads, etc.

---

## 🔍 Exemples Claude MCP : Interagir avec Prometheus

Voici des prompts pour automatiser des actions avec Prometheus via Claude MCP.

### ✅ 1) Vérifier les targets
```text
MCP: Appelle `http://localhost:9090/api/v1/targets` et liste les `activeTargets` avec `job`, `health` et `lastScrape`.
```

### ✅ 2) Exécuter des requêtes PromQL
- **Requête pour vérifier l'état des cibles** :
```text
MCP: Exécute `http://localhost:9090/api/v1/query?query=up` et affiche le `status` et les résultats.
```

- **Requête pour métriques JVM** :
```text
MCP: Exécute `http://localhost:9090/api/v1/query?query=jvm_memory_used_bytes` et affiche les 5 premières séries avec leurs labels.
```

- **Requête pour métriques HTTP** :
```text
MCP: Exécute `http://localhost:9090/api/v1/query?query=http_server_requests_seconds_count` et affiche les résultats.
```

### ✅ 3) Recharger la configuration Prometheus
```text
MCP: Envoie une requête POST sur `http://localhost:9090/-/reload` pour recharger la configuration (nécessite `--web.enable-lifecycle`).
```

### ✅ 4) Lister les jobs configurés
```text
MCP: Appelle `http://localhost:9090/api/v1/targets` et donne la liste des jobs actifs.
```

> 💡 Conseil : Combinez ces requêtes avec des dashboards Grafana pour une vue complète.
