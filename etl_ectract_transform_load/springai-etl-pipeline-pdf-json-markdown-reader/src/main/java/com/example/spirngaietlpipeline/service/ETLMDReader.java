package com.example.spirngaietlpipeline.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ETLMDReader {

    @Value("classpath:input.md")
    Resource resource;

    public List<Document> loadMarkdownasDocuemnts() {
        MarkdownDocumentReaderConfig config =  MarkdownDocumentReaderConfig.builder()
                .withHorizontalRuleCreateDocument(true)
                .withIncludeCodeBlock(false)
                .withIncludeCodeBlock(false)
                .withAdditionalMetadata("filename","input.md")
                .build();
        MarkdownDocumentReader reader = new MarkdownDocumentReader(resource,config);
        return reader.get();
    }
}
