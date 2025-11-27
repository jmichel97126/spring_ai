# 🚀 MCP Server et Client avec Spring AI : AMQP, STDIO et SSE

## ✅ Introduction à MCP et ses Starters
MCP (**Model Context Protocol**) est un protocole standardisé pour interagir avec des modèles IA via différents **transports**. Spring AI propose des **starters** pour simplifier la mise en place :

- **Starter MCP Server** : Expose un serveur MCP pour gérer les prompts et réponses IA.
- **Starter MCP Client** : Permet à une application de consommer un serveur MCP via des transports (AMQP, STDIO, SSE).

### Dépendances Maven
#### ✅ Côté Serveur
```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-mcp-server-starter</artifactId>
</dependency>
```

#### ✅ Côté Client
```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-mcp-client-starter</artifactId>
</dependency>
```

---

## 🔍 Transports disponibles
MCP supporte plusieurs transports pour échanger des prompts et réponses IA :
- **AMQP** : Communication asynchrone via RabbitMQ.
- **STDIO** : Interaction CLI pour prompts IA.
- **SSE** : Streaming temps réel pour UI web.

---

## ✅ AMQP (Advanced Message Queuing Protocol)
AMQP est idéal pour des architectures distribuées et des traitements asynchrones.

### Avantages
- Fiabilité et persistance des messages
- Découplage entre producteurs et consommateurs

### Exemple Spring AI + AMQP
```java
@Service
public class McpAmqpHandler {
    private final AmqpTemplate amqpTemplate;
    private final ChatClient chatClient;

    public McpAmqpHandler(AmqpTemplate amqpTemplate, ChatClient chatClient) {
        this.amqpTemplate = amqpTemplate;
        this.chatClient = chatClient;
    }

    public void handlePrompt(String prompt) {
        String response = chatClient.call(prompt);
        amqpTemplate.convertAndSend("mcp.responses", response);
    }
}
```

---

## ✅ STDIO (Standard Input/Output)
STDIO est utilisé pour des interactions simples via la ligne de commande.

### Avantages
- Idéal pour scripts et outils CLI
- Simple à mettre en place

### Exemple Spring Shell + Spring AI
```java
@ShellComponent
public class McpStdIoHandler {
    private final ChatClient chatClient;

    public McpStdIoHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @ShellMethod("Interagir avec MCP via STDIO")
    public String ask(String prompt) {
        return chatClient.call(prompt);
    }
}
```

---

## ✅ SSE (Server-Sent Events)
SSE permet le streaming des réponses IA en temps réel vers des clients web.

### Avantages
- Connexion unidirectionnelle serveur → client
- Idéal pour notifications et dashboards temps réel

### Exemple Spring MVC SSE
```java
@RestController
public class McpSseController {
    private final ChatClient chatClient;

    public McpSseController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping(value = "/mcp-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamResponse(@RequestParam String prompt) {
        return Flux.just(chatClient.call(prompt));
    }
}
```

Client JS :
```javascript
const source = new EventSource('/mcp-stream?prompt=Bonjour');
source.onmessage = (event) => console.log(event.data);
```

---

## 🛠 Transport par défaut des starters MCP
### ✅ MCP Server Starter
- **Transport par défaut** : HTTP (via REST endpoints)
```properties
spring.ai.mcp.server.transport=http
```

### ✅ MCP Client Starter
- **Transport par défaut** : STDIO
```properties
spring.ai.mcp.client.transport=stdio
```

Pour activer plusieurs transports :
```properties
spring.ai.mcp.client.transport=stdio,amqp,sse
```

---

## ✅ Architecture globale
```text
[MCP Client] ←→ [MCP Server + Spring AI]
   | AMQP | STDIO | SSE
```

---

## ✅ Cas d’usage
- **AMQP** : Traitement asynchrone des prompts IA
- **STDIO** : Interaction rapide via CLI
- **SSE** : Streaming temps réel pour UI web
