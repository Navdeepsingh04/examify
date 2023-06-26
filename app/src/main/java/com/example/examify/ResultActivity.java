package com.example.examify;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        resultTextView = findViewById(R.id.resultTextView);

        Intent intent = getIntent();
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);
        int correctAnswers = intent.getIntExtra("correctAnswers", 0);

        String resultText = "You got " + correctAnswers + " out of " + totalQuestions + " questions correct!";
        resultTextView.setText(resultText);
    }
}
