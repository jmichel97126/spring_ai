package com.example.spirngaietlpipeline.controller;

import com.example.spirngaietlpipeline.service.*;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ETLControler {

    @Autowired
    ETLReaderService etlReaderService;

    @Autowired
    ETLTranformerService etlTranformerService;

    @Autowired
    ETLWriterService etlWriterService;

    @Autowired
    ETLPdfReaderService etlPdfReaderService;

    @Autowired
    ETLJSONReaderService etlJSONReaderService;

    @Autowired
    ETLJSONTranformerService etlJSONTranformerService;

    @Autowired
    ETLMDReader etlMDReader;

    @Autowired
    ETLMDTranformerService etlMDTranformerService;

    @GetMapping("/etl")
    public String etl() {
        List<Document> documents = etlReaderService.loadTextasDocuemnts();
        List<Document> transformedDocuments = etlTranformerService.tranform(documents);
        etlWriterService.write(transformedDocuments);
        return "ETL Pipeline completed";
    }

    @GetMapping("/etlpdf")
    public String etlpdf() {
        List<Document> documents = etlPdfReaderService.loadPdfasDocuemnts();
        List<Document> transformedDocuments = etlTranformerService.tranform(documents);
        etlWriterService.write(transformedDocuments);
        return "ETL PDF Pipeline completed";
    }

    @GetMapping("/etljson")
    public String etljson() {
        List<Document> documents = etlJSONReaderService.loadJSONasDocuemnts();
        List<Document> transformedDocuments = etlJSONTranformerService.tranform(documents);
        etlWriterService.write(transformedDocuments);
        return "ETL JSON Pipeline completed";
    }

    @GetMapping("/etlmd")
    public String etlmd() {
        List<Document> documents = etlMDReader.loadMarkdownasDocuemnts();
        List<Document> transformedDocuments = etlMDTranformerService.tranform(documents);
        etlWriterService.write(transformedDocuments);
        return "ETL MD Pipeline completed";
    }
}
