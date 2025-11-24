package com.example.springaiopenaittsdemo001.controller;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechModel;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.ai.openai.audio.speech.StreamingSpeechModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class TTSController {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    SpeechModel speechModel;

    public TTSController(SpeechModel speechModel) {
        this.speechModel = speechModel;
    }


    @GetMapping("/tts")
    public byte[] tts(@RequestParam(value = "message",defaultValue = "Hello, World! This is test message for text to speech using openai") String message) {
        return speechModel.call(new SpeechPrompt(message)).getResult().getOutput();
    }

    @GetMapping("/ttsaudiostream")
    public Flux<byte[]> getstreaming() throws IOException {
        String content =  new String(Files.readAllBytes(Paths.get("tts.txt")));
        var openAIAudioApi = new OpenAiAudioApi(apiKey);
        var openAIAudioSpeechModel = new OpenAiAudioSpeechModel(openAIAudioApi);

        OpenAiAudioSpeechOptions options = OpenAiAudioSpeechOptions.builder()
                .withVoice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY)
                .withSpeed(1.1f)
                .withResponseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .withModel(OpenAiAudioApi.TtsModel.TTS_1.value)
                .build();

        SpeechPrompt speechPrompt = new SpeechPrompt(content, options);

        Flux<SpeechResponse> responseStream = openAIAudioSpeechModel.stream(speechPrompt);
        return responseStream.flatMap(speechResponse -> Flux.just(speechResponse.getResult().getOutput()));
    }
}
