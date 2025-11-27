package com.example.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "hotel-booking-customer-support")
public class Application implements AppShellConfigurator {

    // logger
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(EmbeddingModel embeddingModel, VectorStore vectorStore,
                                               @Value("classpath:booking-terms,txt" ) Resource termsOfServiceDocs) {
        return args -> {
            List<Document> documents = new TextReader(termsOfServiceDocs).read();
            logger.info("documents size: {}", documents.size());

            TextSplitter textSplitter = new TokenTextSplitter();

            for (Document document : documents) {
                List<Document> splitedDocuments = textSplitter.split(document);
                vectorStore.write(splitedDocuments);
                TimeUnit.SECONDS.sleep(20);
                logger.info("Ingested document: {}", document.getContent());
            }
        };
    }

}
