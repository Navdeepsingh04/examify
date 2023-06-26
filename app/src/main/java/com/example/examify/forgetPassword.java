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
import com.google.firebase.auth.FirebaseAuth;

public class forgetPassword extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        // Initialize the Firebase authentication instance
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        // Initialize the UI elements
        emailEditText = findViewById(R.id.emailEditText);
        resetButton = findViewById(R.id.resetPasswordButton);

        // Set the click listener for the reset button
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the value from the email input field
                String email = emailEditText.getText().toString().trim();

                // Validate the email input field
                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("Please enter your email");
                    emailEditText.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEditText.setError("Please enter a valid email address");
                    emailEditText.requestFocus();
                    return;
                }

                // Send a password reset email to the user's email address
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(forgetPassword.this, "Password reset email sent",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(forgetPassword.this, "Password reset email failed to send",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
