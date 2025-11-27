package com.example.evaluatoroptimizerexample.model;

import java.util.List;

public record RefinedResponse(String solution, List<Generation> chainOfThought) {
}