package com.example.springaineo4jvectorsearchdemo.controller;

import com.example.springaineo4jvectorsearchdemo.service.Neo4jVectorService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Neo4jVectorController {

    @Autowired
    Neo4jVectorService neo4jVectorService;

    @GetMapping("/load")
    public void load() {
        neo4jVectorService.load();
    }

    @GetMapping("/search")
    public List<Document> search() {
        return neo4jVectorService.search("Mountains!");
    }
}
