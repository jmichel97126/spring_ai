# RAG vs MCP : Guide Comparatif

## ✅ Qu'est-ce que RAG ?
**RAG (Retrieval-Augmented Generation)** est une approche qui enrichit les modèles de langage avec des données externes avant la génération.

### Objectif
- Améliorer la précision des réponses en ajoutant des informations fiables.

### Architecture
1. **Indexation** : Embedding des documents dans une base vectorielle.
2. **Recherche** : Récupération des passages pertinents.
3. **Génération** : Le LLM produit une réponse en tenant compte des documents.

### Cas d’usage
- FAQ dynamiques
- Assistants métier
- Recherche documentaire

---

## ✅ Qu'est-ce que MCP ?
**MCP (Model Context Protocol)** est un protocole standard permettant aux LLM d’appeler des outils et des services externes.

### Objectif
- Donner au modèle la capacité d’exécuter des actions (API, bases de données).

### Architecture
1. **Définition des outils** : Fonctions disponibles pour le LLM.
2. **Appel sécurisé** : Le modèle invoque un outil via MCP.
3. **Retour de résultat** : Intégration dans le contexte pour la génération.

### Cas d’usage
- Agents IA orchestrateurs
- Automatisation (ex. créer un ticket Jira)
- Intégration avec systèmes métier

---

## ✅ Tableau Comparatif
| Critère        | RAG                                      | MCP                                      |
|---------------|------------------------------------------|------------------------------------------|
| **Objectif**  | Ajouter des données externes au contexte | Permettre des actions via des outils    |
| **Approche**  | Recherche + génération                   | Appels de fonctions sécurisés           |
| **Cas d’usage**| FAQ, recherche documentaire             | Automatisation, intégration systèmes    |
| **Technos**   | Bases vectorielles, embeddings          | Protocoles, API, orchestrateurs         |

---

## ✅ Schéma simplifié

### RAG
```
[Question] → [Recherche dans base vectorielle] → [Prompt enrichi] → [LLM]
```

### MCP
```
[Question] → [LLM] → [Appel outil via MCP] → [Résultat intégré] → [Réponse finale]
```

---
**Documentation recommandée** :
- [Spring AI RAG](https://docs.spring.io/spring-ai/reference/rag.html)
- [Spring AI MCP](https://docs.spring.io/spring-ai/reference/mcp.html)
