package com.example.examify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private List<String[]> mQuestions = new ArrayList<>();
    private int mQuestionIndex = 0;
    private int mScore = 0;

    private TextView questionTextView;

    private Button option1Button,option2Button,option3Button,option4Button,nextQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Initialize UI elements
        questionTextView = findViewById(R.id.questionTextView);
        option1Button = findViewById(R.id.option1RadioButton);
        option2Button = findViewById(R.id.option2RadioButton);
        option3Button = findViewById(R.id.option3RadioButton);
        option4Button = findViewById(R.id.option4RadioButton);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);

        option1Button.setEnabled(true);
        option2Button.setEnabled(true);
        option3Button.setEnabled(true);
        option4Button.setEnabled(true);



        // Get reference to Firebase database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Fetch questions from database
        mDatabase.child("questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                    String question = questionSnapshot.child("text").getValue(String.class);
                    String option1 = questionSnapshot.child("optionA").getValue(String.class);
                    String option2 = questionSnapshot.child("optionB").getValue(String.class);
                    String option3 = questionSnapshot.child("optionC").getValue(String.class);
                    String option4 = questionSnapshot.child("optionD").getValue(String.class);
                    String answer = questionSnapshot.child("answer").getValue(String.class);
                    mQuestions.add(new String[]{question, option1, option2, option3, option4, answer});
                }

                // Display the first question
                displayQuestion();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
            }
        });

        // Set click listeners for the answer buttons
        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                option1Button.setEnabled(true);
                option2Button.setEnabled(true);
                option3Button.setEnabled(true);
                option4Button.setEnabled(true);
                checkAnswer(option1Button.getText().toString());
            }
        });

        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                option1Button.setEnabled(true);
                option2Button.setEnabled(true);
                option3Button.setEnabled(true);
                option4Button.setEnabled(true);
                checkAnswer(option2Button.getText().toString());
            }
        });

        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                option1Button.setEnabled(true);
                option2Button.setEnabled(true);
                option3Button.setEnabled(true);
                option4Button.setEnabled(true);
                checkAnswer(option3Button.getText().toString());
            }
        });

        option4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                option1Button.setEnabled(true);
                option2Button.setEnabled(true);
                option3Button.setEnabled(true);
                option4Button.setEnabled(true);
                checkAnswer(option4Button.getText().toString());
            }
        });

        // Set click listener for the next question button
        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuestionIndex++;
                displayQuestion();
            }
        });
    }



    // Method to display the current question and its options
    private void displayQuestion() {
        if (mQuestionIndex >= mQuestions.size()) {
            // End of exam
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("correctAnswers", mScore);
            intent.putExtra("totalQuestions",mQuestions.size());
            startActivity(intent);
            finish();


        } else {
            String[] question = mQuestions.get(mQuestionIndex);
            questionTextView.setText(question[0]);
            option1Button.setText(question[1]);
            option2Button.setText(question[2]);
            option3Button.setText(question[3]);
            option4Button.setText(question[4]);

            ((RadioGroup) findViewById(R.id.optionsRadioGroup)).clearCheck();
        }
    }




// Method to check if the answer is correct and move
// Method to check if the answer is correct and move to next question
private void checkAnswer(String answer) {
    String correctAnswer = mQuestions.get(mQuestionIndex)[5];
    if (answer.equals(correctAnswer)) {
        // Answer is correct
        Toast.makeText(this, "Correct answer!", Toast.LENGTH_SHORT).show();
        // Update score
        mScore++;
    } else {
        // Answer is incorrect
        Toast.makeText(this, "Incorrect answer!", Toast.LENGTH_SHORT).show();
    }



}
public void nextQuestionButtonClick(View view){
        mQuestionIndex++;
        displayQuestion();
}

}

