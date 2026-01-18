package com.example.calcultator.ai.OpenRouter_API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenRouterClient {
    private static final String BASE_URL = "https://openrouter.ai/api/v1/";
    private OpenRouterAPI api;

    public OpenRouterClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // זמן התחברות ראשוני
                .readTimeout(60, TimeUnit.SECONDS)    // זמן המתנה לתשובת ה-AI
                .writeTimeout(30, TimeUnit.SECONDS)   // זמן שליחת הנתונים
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(OpenRouterAPI.class);
    }

    public interface ResponseCallback {
        void onSuccess(String response);
        void onError(String error);
    }

    public void sendMessage(String apiKey, String model, String userMessage,
                            String appUrl, String appName, ResponseCallback callback) {

        OpenRouterModels.Message[] messages = {
                new OpenRouterModels.Message("user", userMessage)
        };

        OpenRouterModels.ChatCompletionRequest request =
                new OpenRouterModels.ChatCompletionRequest(model, messages);

        Call<OpenRouterModels.ChatCompletionResponse> call = api.createChatCompletion(
                "Bearer " + apiKey,
                appUrl,
                appName,
                "application/json",
                request
        );

        call.enqueue(new Callback<OpenRouterModels.ChatCompletionResponse>() {
            @Override
            public void onResponse(Call<OpenRouterModels.ChatCompletionResponse> call,
                                   Response<OpenRouterModels.ChatCompletionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String aiResponse = response.body().choices[0].message.content;
                    callback.onSuccess(aiResponse);
                } else {
                    callback.onError("שגיאה בתשובה מהשרת");
                }
            }

            @Override
            public void onFailure(Call<OpenRouterModels.ChatCompletionResponse> call, Throwable t) {
                callback.onError("שגיאה בחיבור: " + t.getMessage());
            }
        });
    }
}

