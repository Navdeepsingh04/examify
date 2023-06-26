package com.example.examify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExaminationScheduleActivity extends AppCompatActivity {



        private TextView androidTutorialContent;
        private TextView webDevTutorialContent;
        private TextView mlTutorialContent;
        private TextView hrmTutorialContent;
        private TextView csTutorialContent;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_examination_schedule);

            androidTutorialContent = findViewById(R.id.android_tutorial_content);
            webDevTutorialContent = findViewById(R.id.webdev_tutorial_content);
            mlTutorialContent = findViewById(R.id.ml_tutorial_content);
            hrmTutorialContent = findViewById(R.id.hrm_tutorial_content);
            csTutorialContent = findViewById(R.id.cs_tutorial_content);

            androidTutorialContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open the study material for Android development
                    String url = "https://www.youtube.com/playlist?list=PLjVLYmrlmjGdDps6HAwOOVoAtBPAgIOXL";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });

            webDevTutorialContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open the study material for web development
                    String url = "https://www.youtube.com/playlist?list=PLfqMhTWNBTe3H6c9OGXb5_6wcc1Mca52n";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });

            mlTutorialContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open the study material for machine learning
                    String url = "https://www.youtube.com/playlist?list=PLjVLYmrlmjGe-xLyoCdDrt8Nil1Alg_L3";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });

            hrmTutorialContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open the study material for human resource management
                    String url = "https://www.youtube.com/playlist?list=PLsh2FvSr3n7f63hhfOBbYwUsUAlvHFDxA";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });

            csTutorialContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open the study material for cyber security
                    String url = "https://www.youtube.com/playlist?list=PL-JvKqQx2AteIbm-z4X709scVr9OaHpIY";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });
        }
    }
