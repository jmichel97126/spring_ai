# Ollama : Qu'est-ce que c'est ?

Ollama est une plateforme permettant d'exécuter des **modèles de langage (LLM)** directement en local sur votre machine. 
Elle offre une interface simple pour :
- Télécharger et gérer différents modèles IA (comme Llama, Mistral, etc.)
- Exécuter des requêtes sans dépendre du cloud
- Garantir la confidentialité des données en travaillant hors ligne

Ollama est particulièrement utile pour les développeurs, chercheurs et passionnés d'IA souhaitant tester ou intégrer des modèles avancés dans leurs applications.

---

# Installation d'Ollama sur Ubuntu

## ✅ Prérequis
- Ubuntu 20.04 ou version ultérieure
- Accès administrateur (sudo)
- Connexion Internet

## 📥 Étape 1 : Ajouter le dépôt Ollama
Exécutez les commandes suivantes dans votre terminal :

```bash
curl -fsSL https://ollama.com/install.sh | sh
```

Cette commande télécharge et installe Ollama via le script officiel.

## ⚙️ Étape 2 : Vérifier l'installation
Après l'installation, vérifiez que Ollama est accessible :

```bash
ollama --version
```

## 🧪 Étape 3 : Tester Ollama
Pour utiliser le modèle **llama3.1:8B**, exécutez :

```bash
ollama run llama3.1:8b
```

Cette commande téléchargera et exécutera le modèle **Llama 3.1 avec 8 milliards de paramètres**.

> 💡 Assurez-vous d'avoir suffisamment de ressources (RAM et CPU/GPU) pour exécuter ce modèle.

## ❓ Dépannage
- Si la commande `ollama` n'est pas reconnue, assurez-vous que le script d'installation a bien ajouté Ollama à votre PATH.
- Redémarrez votre terminal si nécessaire.

## 📚 Ressources utiles
- [Documentation officielle Ollama](https://ollama.com/docs)
