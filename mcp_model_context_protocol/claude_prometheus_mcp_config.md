# Configuration Claude pour Prometheus avec Docker (MCP Server)

Ce document décrit comment configurer **Claude Desktop** pour lancer **Prometheus** dans un conteneur Docker,
dans le cadre d'un **MCP Server** pour un projet **Spring Boot** utilisant **Spring AI**.

## 📌 Objectif
- Déployer Prometheus via Docker
- Intégrer la configuration dans `claude_desktop_config.json`
- Préparer l'environnement pour un projet Spring Boot avec Spring AI

## ✅ Configuration JSON (claude_desktop_config.json)

```json
{
  "mcpServers": {
    "prometheus": {
      "type": "docker",
      "image": "prom/prometheus:latest",
      "command": [
        "/bin/prometheus"
      ],
      "args": [
        "--config.file=/etc/prometheus/prometheus.yml",
        "--storage.tsdb.path=/prometheus",
        "--web.enable-lifecycle"
      ],
      "env": {
        "PROMETHEUS_USERNAME": "",
        "PROMETHEUS_PASSWORD": ""
      },
      "ports": [
        {
          "containerPort": 9090,
          "hostPort": 9090
        }
      ],
      "volumes": [
        {
          "hostPath": "./prometheus.yml",
          "containerPath": "/etc/prometheus/prometheus.yml"
        }
      ]
    }
  }
}
```

## 🔍 Explications
- **type** : indique que le serveur tourne dans un conteneur Docker
- **image** : image officielle Prometheus
- **command** et **args** : binaire et options CLI
- **env** : variables d'environnement pour username/password
- **ports** : expose le port 9090
- **volumes** : monte le fichier `prometheus.yml` depuis l'hôte

## 🚀 Intégration avec Spring Boot + Spring AI
- Ajoutez Prometheus comme serveur MCP pour monitorer votre application Spring Boot
- Configurez Spring Boot Actuator pour exposer des métriques compatibles Prometheus



## ⚙️ Configuration Spring Boot pour Prometheus

Pour exposer des métriques Prometheus dans votre application **Spring Boot** avec **Spring AI**, suivez ces étapes :

### 1️⃣ Dépendances Maven
Ajoutez les dépendances suivantes dans votre `pom.xml` :

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

### 2️⃣ Configuration `application.properties`

```properties
management.endpoints.web.exposure.include=health,info,prometheus
management.metrics.export.prometheus.enabled=true
```

### 3️⃣ Exemple de classe Java (endpoint custom)

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    @GetMapping("/ai/status")
    public String status() {
        return "Spring AI service is running";
    }
}
```

### ✅ Vérification
- Démarrez votre application Spring Boot
- Accédez à `http://localhost:8080/actuator/prometheus` pour voir les métriques



## 📄 Exemple de fichier `prometheus.yml`

Voici un exemple minimaliste pour configurer Prometheus afin de scraper les métriques d'une application Spring Boot :

```yaml
global:
  scrape_interval: 15s  # Intervalle global de scraping

scrape_configs:
  - job_name: 'spring-boot-app'  # Nom du job pour identifier la source
    metrics_path: '/actuator/prometheus'  # Endpoint exposé par Spring Boot Actuator
    static_configs:
      - targets: ['localhost:8080']  # Adresse de l'application Spring Boot
```

### 🔍 Explications
- **scrape_interval** : fréquence à laquelle Prometheus collecte les métriques (par défaut 15s)
- **job_name** : identifiant du job (ici `spring-boot-app`)
- **metrics_path** : chemin où Spring Boot expose les métriques Prometheus
- **targets** : liste des hôtes à surveiller (ici `localhost:8080`)

➡️ Placez ce fichier dans le chemin `/etc/prometheus/prometheus.yml` dans le conteneur Prometheus (comme indiqué dans la configuration Claude).


## 💬 Exemples d'utilisation depuis Claude (MCP)

Voici comment interagir avec le **MCP Server Prometheus** directement depuis **Claude Desktop** une fois votre `claude_desktop_config.json` en place.

### 🔌 Exemple de prompts à utiliser dans Claude

1. **Vérifier que le serveur Prometheus est lancé**
   ```text
   MCP: Liste les serveurs disponibles et vérifie que le serveur `prometheus` est en état `running`.
   ```

2. **Lister les jobs configurés dans Prometheus**
   ```text
   MCP: Appelle l'API HTTP de Prometheus `/api/v1/targets` et me donne la liste des `activeTargets` avec leur `job` et `health`.
   ```

3. **Vérifier que l'application Spring Boot expose des métriques**
   ```text
   MCP: Fais une requête GET sur `http://localhost:8080/actuator/prometheus` et confirme que des métriques Micrometer sont présentes (ex: `jvm_memory_used_bytes`).
   ```

4. **Recharger la configuration de Prometheus à chaud**
   _Nécessite l'argument `--web.enable-lifecycle` dans Prometheus._
   ```text
   MCP: Envoie une requête POST sur `http://localhost:9090/-/reload` pour recharger la configuration.
   ```

5. **Exécuter une requête PromQL**
   ```text
   MCP: Appelle `http://localhost:9090/api/v1/query?query=up` et renvoie le `status` et les `result`.
   ```

### 🧪 Scénario complet (pas à pas)

1. **Démarrage** : Assure-toi que Claude Desktop a bien chargé la config, puis demande :
   ```text
   MCP: Démarre le serveur `prometheus` défini dans `mcpServers` et confirme quand le conteneur est prêt.
   ```
2. **Santé des cibles** :
   ```text
   MCP: Récupère `http://localhost:9090/api/v1/targets` et affiche les `lastScrape` pour le job `spring-boot-app`.
   ```
3. **Requête PromQL sur les métriques JVM** :
   ```text
   MCP: Exécute une PromQL `jvm_memory_used_bytes` et affiche les 5 premières séries avec leurs labels.
   ```

### 🧰 Conseils pratiques
- Si `PROMETHEUS_USERNAME`/`PROMETHEUS_PASSWORD` sont utilisés avec un reverse proxy, précise les en-têtes `Authorization` dans tes requêtes MCP.
- Pour des environnements multiples (dev, staging, prod), duplique la section `mcpServers.prometheus` avec des noms distincts (ex: `prometheus-dev`, `prometheus-prod`) et des `hostPort` différents.

