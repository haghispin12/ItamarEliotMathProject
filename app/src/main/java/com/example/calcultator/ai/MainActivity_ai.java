package com.example.calcultator.ai;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import com.example.calcultator.ai.OpenRouter_API.OpenRouterClient;

import com.example.calcultator.R;

public class

MainActivity_ai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ai);

        OpenRouterClient test = new OpenRouterClient();

        String myapi = "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";

        Log.d("AI_TEST", "מתחיל לשלוח הודעה לבדיקה...");


        test.sendMessage(
                myapi,
                "tngtech/tng-r1t-chimera:free",
                "תגיד 'הצלחתי' אם אתה שומע אותי",
                 null,
                null,
                new OpenRouterClient.ResponseCallback() {
                    @Override
                    public void onSuccess(String response) {

                        Log.d("AI_TEST", "תשובה מה-AI: " + response);
                    }

                    @Override
                    public void onError(String error) {

                        Log.e("AI_TEST", "אוי לא, שגיאה: " + error);
                    }
                }
        );


    }
}