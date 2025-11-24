# Guide : Installer Milvus sur Google Cloud (Ubuntu VM)

Ce guide explique comment déployer **Milvus** sur une machine Ubuntu hébergée sur **Google Cloud**, en réalisant toutes les actions depuis la console GCP.

---

## ✅ 1. Créer une VM Ubuntu sur Google Cloud
1. Accédez à https://console.cloud.google.com.
2. Activez **Compute Engine**.
3. Créez une instance :
   - **Nom** : `milvus-vm`
   - **Zone** : proche de vos utilisateurs.
   - **Machine type** : `e2-standard-4` (4 vCPU, 16 Go RAM recommandé).
   - **Image** : Ubuntu 22.04 LTS.
   - **Disque** : 50 Go SSD.
4. **Configurer le firewall** :
   - Autorisez **HTTP/HTTPS**.
   - Ajoutez une règle pour **port 19530** (Milvus) et **port 9091** (dashboard).

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
  milvus:
    image: milvusdb/milvus:v2.2.10
    container_name: milvus
    ports:
      - "19530:19530"
      - "9091:9091"
    environment:
      MILVUS_ROOT_USER: root
      MILVUS_ROOT_PASSWORD: milvus
    volumes:
      - milvus_data:/var/lib/milvus
volumes:
  milvus_data:
```

---

## ✅ 5. Lancer Milvus
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
  - **Nom** : `allow-milvus`
  - **Ports** : `19530,9091`
  - **Source** : `0.0.0.0/0` (ou IP spécifique pour plus de sécurité).

---

## ✅ 7. Vérifier l’IP publique
- Allez dans **Compute Engine > Instances**.
- Notez l’**Adresse IP externe** (exemple : `34.66.190.138`).

---

## ✅ 8. Informations de connexion
- **Host** : `34.66.190.138`
- **Port** : `19530`
- **Username** : `root`
- **Password** : `milvus`

Ces informations seront utilisées par vos applications ou outils (UI, SDK, etc.) pour se connecter à Milvus.

---

## ✅ 9. (Optionnel) Ajouter une interface graphique (Attu)
Pour gérer Milvus via une UI :
```bash
docker run -d -p 3000:3000 zilliz/attu:latest
```
Accédez à : `http://34.66.190.138:3000`.

---

### 📌 Références
- [Documentation Milvus](https://milvus.io/docs/install-overview.md)
- https://cloud.google.com/compute/docs

---