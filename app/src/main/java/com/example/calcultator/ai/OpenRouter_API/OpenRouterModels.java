package com.example.calcultator.ai.OpenRouter_API;

public class OpenRouterModels {

    public static class ChatCompletionRequest {
        public String model;
        public Message[] messages;
        public boolean stream = false;

        public ChatCompletionRequest(String model, Message[] messages) {
            this.model = model;
            this.messages = messages;
        }
    }

    public static class Message {
        public String role;
        public String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

    public static class ChatCompletionResponse {
        public Choice[] choices;
    }

    public static class Choice {
        public Message message;
    }
}

