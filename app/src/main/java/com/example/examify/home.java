package com.example.examify;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class home extends AppCompatActivity {

    private Button startExamButton, viewResultsButton, practiceExamButton, studyMaterialButton, settingsButton, helpButton;
    private GridLayout gridView;
    private TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exam_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.signout_item) {
            // Handle sign out
            Intent inxet = new Intent(home.this,loginActivity.class);
            startActivity(inxet);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();





        // Find the views by their IDs
        textView = findViewById(R.id.usernameTextView);
        startExamButton = findViewById(R.id.startExamButton);
        viewResultsButton = findViewById(R.id.viewResultsButton);
        practiceExamButton = findViewById(R.id.practiceExamButton);
        studyMaterialButton = findViewById(R.id.studyMaterialButton);
        settingsButton = findViewById(R.id.settingsButton);
        helpButton = findViewById(R.id.helpButton);
        textView.setText("Welcome! "+getIntent().getStringExtra("UserName"));
        FirebaseUser user = mAuth.getCurrentUser();
       if (user != null) {
           String displayName = user.getDisplayName();
          if (displayName != null) {
              TextView textView = findViewById(R.id.usernameTextView);
              textView.setText("Welcome to Examify");
            }
        }


        // Set click listeners for the buttons
        startExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNext = new Intent(home.this,ExamActivity.class);
                startActivity(iNext);
            }
        });

        viewResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent iNext = new Intent(home.this,ResultActivity.class);
               startActivity(iNext);
            }
        });

        practiceExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNext = new Intent(home.this,practice.class);
                startActivity(iNext);
            }
        });

        studyMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNext = new Intent(home.this,ExaminationScheduleActivity.class);
                startActivity(iNext);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNext = new Intent(home.this,SettingsActivity.class);
                startActivity(iNext);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this, "Help clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
