
# Ollama : Qu'est-ce que c'est ?

Ollama est une plateforme permettant d'exécuter des **modèles de langage (LLM)** directement en local sur votre machine. 
Elle offre une interface simple pour :
- Télécharger et gérer différents modèles IA (comme Llama, Mistral, etc.)
- Exécuter des requêtes sans dépendre du cloud
- Garantir la confidentialité des données en travaillant hors ligne

Ollama est particulièrement utile pour les développeurs, chercheurs et passionnés d'IA souhaitant tester ou intégrer des modèles avancés dans leurs applications.

---
# Installation d'Ollama sur Windows

## ✅ Prérequis
- Windows 10 ou version ultérieure
- Accès administrateur
- Connexion Internet

## 🔽 Étape 1 : Télécharger Ollama
1. Rendez-vous sur le site officiel : [https://ollama.com/download](https://ollama.com/download)
2. Cliquez sur **Download for Windows** pour récupérer l'installateur.

## 💻 Étape 2 : Installer Ollama
1. Exécutez le fichier `.exe` téléchargé.
2. Suivez les instructions de l'assistant d'installation.
3. Une fois terminé, Ollama sera installé sur votre système.

## ⚙️ Étape 3 : Configurer Ollama
- Après l'installation, ouvrez **PowerShell** ou **Invite de commandes**.
- Vérifiez que Ollama est accessible en tapant :

```bash
ollama --version
```

## 🧪 Étape 4 : Tester Ollama
- Pour tester un modèle, exécutez :

```bash
ollama run llama2
```

Cela téléchargera et exécutera le modèle **Llama 2**.

## ❓ Dépannage
- Si la commande `ollama` n'est pas reconnue, redémarrez votre terminal ou ajoutez le chemin d'installation d'Ollama à la variable d'environnement `PATH`.

## 📚 Ressources utiles
- [Documentation officielle Ollama](https://ollama.com/docs)


## 🦙 Étape 5 : Exécuter le modèle Llama 3.1 (8B)
- Pour utiliser le modèle **llama3.1:8B**, exécutez la commande suivante :

```bash
ollama run llama3.1:8b
```

Cette commande téléchargera et exécutera le modèle **Llama 3.1 avec 8 milliards de paramètres**.

> 💡 Assurez-vous d'avoir suffisamment de ressources (RAM et CPU/GPU) pour exécuter ce modèle.
