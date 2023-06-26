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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class rating extends Fragment {






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RatingBar rb = getView().findViewById(R.id.ratingBar);
        Button btn = getView().findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = String.valueOf(rb.getRating());
                TextView tv = getView().findViewById(R.id.textView);
                //tv.setVisibility(View.VISIBLE);

                Toast.makeText(getContext(), "Thank You for Rating us "+rating+" stars", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getContext(),loginactivitynew.class));




            }

        });
   }
}