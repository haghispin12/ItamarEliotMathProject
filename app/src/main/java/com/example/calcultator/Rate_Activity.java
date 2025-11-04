package com.example.calcultator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Rate_Activity extends AppCompatActivity {

    private Button SaveRate;
    private SeekBar Rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rate);

        SaveRate = findViewById(R.id.SaveRate);
        Rating = findViewById(R.id.Rating);

        SaveRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rate = Rating.getProgress();
                Intent intent = new Intent();
                intent.putExtra("Rate_key", rate);
                setResult(RESULT_OK, intent);
                finish();
            }


        });

    }

}