# Guide : Installer Typesense sur Google Cloud (Ubuntu VM)

Ce guide explique comment déployer **Typesense** sur une machine Ubuntu hébergée sur **Google Cloud**, en réalisant toutes les actions depuis la console GCP.

---

## ✅ 1. Créer une VM Ubuntu sur Google Cloud
1. Accédez à [Google Cloud Console](https://console.cloud.google.com).
2. Activez **Compute Engine**.
3. Créez une instance :
   - **Nom** : `typesense-vm`
   - **Zone** : proche de vos utilisateurs.
   - **Machine type** : `e2-standard-2` (2 vCPU, 4 Go RAM suffisent pour Typesense).
   - **Image** : Ubuntu 22.04 LTS.
   - **Disque** : 20 Go SSD.
4. **Configurer le firewall** :
   - Autorisez **HTTP/HTTPS**.
   - Ajoutez une règle pour **port 8108** (port par défaut de Typesense).

---

## ✅ 2. Se connecter à la VM
Dans la console GCP :
- Cliquez sur **SSH** pour ouvrir un terminal dans le navigateur.

---

## ✅ 3. Installer Docker et Docker Compose
Exécutez :
```bash
sudo apt-get update && sudo apt-get upgrade -y
sudo apt-get install -y docker.io docker-compose
sudo systemctl enable docker
sudo systemctl start docker
```

---

## ✅ 4. Créer le fichier `docker-compose.yml`
Toujours dans la VM :
```bash
nano docker-compose.yml
```

Collez :
```yaml
version: "3.9"
services:
  typesense:
    image: typesense/typesense:latest
    container_name: typesense
    ports:
      - "8108:8108"
    environment:
      TYPESENSE_API_KEY: mytypesensekey
    volumes:
      - typesense_data:/data
volumes:
  typesense_data:
```

---

## ✅ 5. Lancer Typesense
```bash
sudo docker-compose up -d
```

Vérifiez :
```bash
docker ps
```

---

## ✅ 6. Configurer l’accès externe
- Allez dans **VPC Network > Firewall rules**.
- Créez une règle :
  - **Nom** : `allow-typesense`
  - **Ports** : `8108`
  - **Source** : `0.0.0.0/0` (ou IP spécifique pour plus de sécurité).

---

## ✅ 7. Vérifier l’IP publique
- Allez dans **Compute Engine > Instances**.
- Notez l’**Adresse IP externe** (exemple : `34.66.190.138`).

---

## ✅ 8. Informations de connexion
- **Host** : `34.66.190.138`
- **Port** : `8108`
- **API Key** : `mytypesensekey`

Ces informations seront utilisées par vos applications ou SDK pour se connecter à Typesense.

---

## ✅ 9. (Optionnel) Tester avec curl
Pour vérifier que Typesense fonctionne :
```bash
curl http://34.66.190.138:8108/health \ 
  -H "X-TYPESENSE-API-KEY: mytypesensekey"
```

---

### 📌 Références
- [Documentation Typesense](https://typesense.org/docs/)
- [Google Cloud Compute Engine](https://cloud.google.com/compute/docs)

---