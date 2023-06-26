package com.example.examify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.lang.CharSequence;

public class SignUpActivity extends AppCompatActivity {





    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_sign_up);

        // Initialize the Firebase database reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();



        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        // Initialize the UI elements
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        signUpButton = findViewById(R.id.signUpButton);

        // Set the click listener for the sign-up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the values from the input fields
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();

                // Validate the input fields
                if (TextUtils.isEmpty(name)) {
                    nameEditText.setError("Please enter your name");
                    nameEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("Please enter your email");
                    emailEditText.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEditText.setError("Please enter a Valid Email address");
                    emailEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Please enter your password");
                    passwordEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(confirmPassword)) {
                    confirmPasswordEditText.setError("Please confirm your password");
                    confirmPasswordEditText.requestFocus();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    confirmPasswordEditText.setError("Passwords do not match");
                    confirmPasswordEditText.requestFocus();
                    return;
                }

                // Create a new user account with the email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    // Create a new child node under the "users" node in the database
                                    String userId = databaseReference.child("users").push().getKey();

                                    // Store the user's information under the child node
                                    DatabaseReference userRef = databaseReference.child("users").child(userId);
                                    userRef.child("name").setValue(name);
                                    userRef.child("email").setValue(email);

                                    // Display a success message to the user
                                    Toast.makeText(SignUpActivity.this, "User registered successfully",
                                            Toast.LENGTH_SHORT).show();

                                    // Clear the input fields
                                    nameEditText.setText("");
                                    emailEditText.setText("");
                                    passwordEditText.setText("");
                                    confirmPasswordEditText.setText("");
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
    }


