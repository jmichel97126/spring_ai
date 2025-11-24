# Observabilité avec Spring Boot, Spring AI et Prometheus

Ce guide liste les URLs importantes pour surveiller et diagnostiquer une application Spring Boot utilisant Spring AI et Prometheus.

---

## ✅ Endpoints Actuator
- **Santé de l'application** :
```
http://localhost:8080/actuator/health
```

- **Informations générales** :
```
http://localhost:8080/actuator/info
```

- **Métriques (Prometheus)** :
```
http://localhost:8080/actuator/prometheus
```

- **Toutes les métriques Spring Boot** :
```
http://localhost:8080/actuator/metrics
```

- **Détails d'une métrique spécifique** (exemple : `jvm.memory.used`):
```
http://localhost:8080/actuator/metrics/jvm.memory.used
```

- **Points de terminaison disponibles** :
```
http://localhost:8080/actuator
```

---

## ✅ Endpoints utiles pour le monitoring
- **Prometheus Scrape Endpoint** :
```
http://localhost:8080/actuator/prometheus
```

- **Health Check pour Kubernetes / Docker** :
```
http://localhost:8080/actuator/health/liveness
http://localhost:8080/actuator/health/readiness
```

---

## ✅ Si Swagger est activé
- **Documentation API** :
```
http://localhost:8080/swagger-ui.html
```

---

## 📚 Ressources utiles
- [Spring Boot Actuator Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [Prometheus Documentation](https://prometheus.io/docs/introduction/overview/)
