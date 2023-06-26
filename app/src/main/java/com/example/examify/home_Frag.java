package com.example.examify;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class home_Frag extends Fragment {





    public home_Frag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home_, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



        super.onViewCreated(view, savedInstanceState);



        FirebaseAuth mAuth = FirebaseAuth.getInstance();



        TextView textView = getView().findViewById(R.id.usernameTextView);
        Button startExamButton = getView().findViewById(R.id.startExamButton);
        //Button viewResultsButton = getView().findViewById(R.id.viewResultsButton);
        Button practiceExamButton = getView().findViewById(R.id.practiceExamButton);
        Button studyMaterialButton = getView().findViewById(R.id.studyMaterialButton);
        Button settingsButton = getView().findViewById(R.id.settingsButton);
        Button helpButton = getView().findViewById(R.id.helpButton);
       // textView.setText("Welcome! "+getIntent().getStringExtra("UserName"));
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String displayName = user.getDisplayName();
            if (displayName != null) {
                 textView = getView().findViewById(R.id.usernameTextView);
                textView.setText("Welcome to Examify");
            }
        }


        // Set click listeners for the buttons
        startExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNext = new Intent(getContext(),ExamActivity.class);
                startActivity(iNext);
            }
        });

        //viewResultsButton.setOnClickListener(new View.OnClickListener() {
            //@Override
        //    public void onClick(View v) {
       //         Intent iNext = new Intent(getContext(),ResultActivity.class);
       //         startActivity(iNext);
       //     }
     //   });

        practiceExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNext = new Intent(getContext(),practice.class);
                startActivity(iNext);
            }
        });

        studyMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNext = new Intent(getContext(),ExaminationScheduleActivity.class);
                startActivity(iNext);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNext = new Intent(getContext(),SettingsActivity.class);
                startActivity(iNext);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inext = new Intent(getContext(),help.class);
                startActivity(inext);
            }
        });
}}