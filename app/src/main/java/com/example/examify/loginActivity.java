package com.example.examify;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class loginActivity extends AppCompatActivity {

    // Declare your UI elements
    EditText usernameEditText, passwordEditText;
    Button loginButton;
    TextView forgotPasswordTextView, registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize FirebaseApp
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // Get a reference to the FirebaseAuth instance
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        // Get references to your UI elements
        usernameEditText = findViewById(R.id.login_username);
        passwordEditText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        forgotPasswordTextView = findViewById(R.id.login_forgot_password);
        registerTextView = findViewById(R.id.login_register);

        // Set an OnClickListener on the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login logic here
                String email = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();


                if(TextUtils.isEmpty(email)){

                    final MediaPlayer mp = MediaPlayer.create(loginActivity.this,R.raw.ringtone3);
                    mp.start();

                    usernameEditText.setError("Please enter the UserName");

                    usernameEditText.requestFocus();



                    return;
                }

                if(TextUtils.isEmpty(password)){

                    final MediaPlayer mp = MediaPlayer.create(loginActivity.this,R.raw.ringtone3);
                    mp.start();

                    passwordEditText.setError("Please enter the password");
                    passwordEditText.requestFocus();

                    return;
                }

                // Sign in the user with the given email and password
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    // Get a reference to the current user and log their information
//                                    String user = mAuth.getCurrentUser().getUid();
//                                    Log.d("Uid","Current user is "+user);
//                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                                    Log.d(TAG,"Database"+database);
//
//                                    DatabaseReference userRef = database.getReference("users" + mAuth.getCurrentUser().getUid());
//                                    Log.d(TAG,"userRef is"+userRef);

                                    // Get a reference to the user's node in the database
                                   // DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);






                                    // Add a ValueEventListener to get the user's name from the database
//                                    userRef.addValueEventListener(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                            // Get the user's name from the snapshot
//                                            for(DataSnapshot data:snapshot.getChildren()){
//                                                String name = snapshot.child("name").getValue(String.class);
//                                                Log.d(TAG, "User's name is: " + name);
//
//                                                // Create an intent to start the next activity
//                                                Intent intent = new Intent(loginActivity.this, home.class);
//                                                // Add the username as an extra to the intent
//                                                intent.putExtra("USERNAME_KEY", name);
//                                                // Start the next activity
//                                                startActivity(intent);
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError error) {
//                                            // Handle the error
//                                        }
//                                    };
                                    final String[] userNam = {""};
                                    FirebaseDatabase.getInstance().getReference("users")
                                            .addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    for(DataSnapshot data:snapshot.getChildren()) {
                                                        if (email.equals(data.child("email").getValue(String.class))) {
                                                            userNam[0] = data.child("name").getValue(String.class);
                                                        }
                                                    }
                                                    Log.d("User Name",userNam[0]);



                                                    Intent i = new Intent(loginActivity.this,loginactivitynew.class);
                                                  //  i.putExtra("UserName",userNam[0]);
                                                    startActivity(i);
                                                    //final MediaPlayer mp = MediaPlayer.create(loginActivity.this,R.raw.ringtone1);
                                                    //mp.start();
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });

//                                    String user = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid()).child("Name")
                                } else {

                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(loginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    final MediaPlayer mp = MediaPlayer.create(loginActivity.this,R.raw.ringtone3);
                                    mp.start();
                                }
                            }});



            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iNext = new Intent(loginActivity.this,forgetPassword.class);
                startActivity(iNext);

            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle register logic here
                Intent iNext = new Intent(loginActivity.this,SignUpActivity.class);
                startActivity(iNext);

            }
        });
    }
}
