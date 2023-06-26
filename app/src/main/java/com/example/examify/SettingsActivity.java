package com.example.examify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.examifyQuestion.Question;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    private EditText questionEditText;
    private EditText optionAEditText;
    private EditText optionBEditText;
    private EditText optionCEditText;
    private EditText optionDEditText;
    private Spinner answerSpinner;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        questionEditText = findViewById(R.id.question_edit_text);
        optionAEditText = findViewById(R.id.option_a_edit_text);
        optionBEditText = findViewById(R.id.option_b_edit_text);
        optionCEditText = findViewById(R.id.option_c_edit_text);
        optionDEditText = findViewById(R.id.option_d_edit_text);
        answerSpinner = findViewById(R.id.correct_answer_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.answer_choices, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        answerSpinner.setAdapter(adapter);

        // Get a reference to the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = questionEditText.getText().toString();
                String optionA = optionAEditText.getText().toString();
                String optionB = optionBEditText.getText().toString();
                String optionC = optionCEditText.getText().toString();
                String optionD = optionDEditText.getText().toString();


                String answer = answerSpinner.getSelectedItem().toString();
                if(answer.equals("Option A")){
                    answer = optionA;
                }
                else if(answer.equals("Option B")){
                    answer = optionB;
                }
                else if(answer.equals("Option C")){
                    answer = optionC;
                }
                else if(answer.equals("Option D")){
                    answer = optionD;
                }

                uploadQuestion(question, optionA, optionB, optionC, optionD, answer);
            }
        });
    }

    private void uploadQuestion(String question, String optionA, String optionB, String optionC, String optionD, String answer) {
        // Get a reference to the "questions" node in the database
        DatabaseReference questionsRef = databaseReference.child("questions");

        // Generate a unique key for the question
        String key = questionsRef.push().getKey();

        // Create a Question object
        Question questionObject = new Question(question, optionA, optionB, optionC, optionD, answer);

        // Upload the question to the Firebase Realtime Database
        questionsRef.child(key).setValue(questionObject)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SettingsActivity.this, "Question uploaded successfully!", Toast.LENGTH_SHORT).show();
                        questionEditText.setText("");
                        optionAEditText.setText("");
                        optionBEditText.setText("");
                        optionCEditText.setText("");
                        optionDEditText.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SettingsActivity.this, "Question upload failed!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

