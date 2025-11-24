# Guide : Extraire et importer le certificat OpenSearch dans le JDK

Ce guide explique comment :
1. Créer un répertoire temporaire.
2. Extraire le certificat SSL d'OpenSearch avec OpenSSL.
3. Importer ce certificat dans le keystore du JDK.

---

## ✅ 1. Créer un répertoire temporaire
Sur Windows, ouvrez **PowerShell** ou **Invite de commandes** et exécutez :
```powershell
mkdir C:\temp
```

---

## ✅ 2. Extraire le certificat SSL d'OpenSearch
Dans le répertoire `C:\temp`, exécutez la commande suivante :
```bash
openssl s_client -showcerts -connect localhost:9200 < /dev/null | sed -n -e '/-BEGIN/,/-END/ p' > certifs.cer
```

### Explications :
- `openssl s_client -showcerts -connect localhost:9200` : se connecte au service OpenSearch sur le port 9200 et affiche les certificats.
- `sed -n -e '/-BEGIN/,/-END/ p'` : extrait uniquement le bloc du certificat.
- `> certifs.cer` : enregistre le certificat dans un fichier.

Le fichier `certifs.cer` sera créé dans `C:\temp`.

---

## ✅ 3. Importer le certificat dans le keystore du JDK
Utilisez **keytool** pour importer le certificat :
```bash
keytool -import -alias opensearchcert -file "C:\temp\certifs.cer" -keystore "C:\Program Files\Java\jdk-22\lib\security\cacerts"
```

### Explications :
- `-alias opensearchcert` : nom sous lequel le certificat sera stocké.
- `-file "C:\temp\certifs.cer"` : chemin du certificat.
- `-keystore "C:\Program Files\Java\jdk-22\lib\security\cacerts"` : keystore du JDK.

Vous devrez entrer le mot de passe du keystore (par défaut : `changeit`).

---

### 📌 Remarques importantes
- Assurez-vous que **OpenSSL** est installé sur votre machine.
- Exécutez les commandes avec les droits administrateur.
- Sauvegardez le keystore avant modification.

---

### ✅ Vérification
Pour vérifier que le certificat est bien importé :
```bash
keytool -list -keystore "C:\Program Files\Java\jdk-22\lib\security\cacerts" | findstr opensearchcert
```

---