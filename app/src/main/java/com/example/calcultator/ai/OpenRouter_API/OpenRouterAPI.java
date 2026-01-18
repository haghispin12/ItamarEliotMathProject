package com.example.calcultator.ai.OpenRouter_API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OpenRouterAPI {
    @POST("chat/completions")
    Call<OpenRouterModels.ChatCompletionResponse> createChatCompletion(
            @Header("Authorization") String authorization,
            @Header("HTTP-Referer") String referer,
            @Header("X-Title") String title,
            @Header("Content-Type") String contentType,
            @Body OpenRouterModels.ChatCompletionRequest request
    );
}

