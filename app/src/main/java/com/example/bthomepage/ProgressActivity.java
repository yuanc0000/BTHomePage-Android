package com.example.bthomepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.ContentValues.TAG;


public class ProgressActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    int minteger = 0;
    int minteger1 = 0;
    int minteger2 = 0;
    int minteger3 = 0;
    int minteger4 = 0;
    int minteger5 = 0;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.progressActivity);

//bottom navigation view implemented

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.diagnosisActivity:
                        Intent intent1 = new Intent(ProgressActivity.this, DiagnosisActivity.class);
                        startActivity(intent1);
                        break;


                    case R.id.exerciseActivity:
                        Intent intent2 = new Intent(ProgressActivity.this, LungsActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.homeActivity:
                        Intent intent3 = new Intent(ProgressActivity.this, HomePage.class);
                        startActivity(intent3);
                        break;

                    case R.id.progressActivity:
                        break;

                    case R.id.settingsActivity:
                        Intent intent5 = new Intent(ProgressActivity.this, SettingsActivity.class);
                        startActivity(intent5);
                        break;



                }
                return false;
            }
        });
    }
    //ability for user to increase and decrese number of exercises completed
    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger(View view) {
        minteger = minteger - 1;
        display(minteger);
    }

    public void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number);
        displayInteger.setText("" + number);
    }

    public void increaseInteger1(View view) {
        minteger1 = minteger1 + 1;
        display1(minteger1);

    }public void decreaseInteger1(View view) {
        minteger1 = minteger1 - 1;
        display1(minteger1);
    }

    public void display1(int number1) {
        TextView displayInteger1 = (TextView) findViewById(
                R.id.integer_number1);
        displayInteger1.setText("" + number1);
    }

    public void increaseInteger2(View view) {
        minteger2 = minteger2 + 1;
        display2(minteger2);

    }public void decreaseInteger2(View view) {
        minteger2 = minteger2 - 1;
        display2(minteger2);
    }

    public void display2(int number2) {
        TextView displayInteger2 = (TextView) findViewById(
                R.id.integer_number2);
        displayInteger2.setText("" + number2);
    }

    public void increaseInteger3(View view) {
        minteger3 = minteger3 + 1;
        display3(minteger3);

    }public void decreaseInteger3(View view) {
        minteger3 = minteger3 - 1;
        display3(minteger3);
    }

    public void display3(int number3) {
        TextView displayInteger3 = (TextView) findViewById(
                R.id.integer_number3);
        displayInteger3.setText("" + number3);
    }

    public void increaseInteger4(View view) {
        minteger4 = minteger4 + 1;
        display4(minteger4);

    }public void decreaseInteger4(View view) {
        minteger4 = minteger4 - 1;
        display4(minteger4);
    }

    public void display4(int number4) {
        TextView displayInteger4 = (TextView) findViewById(
                R.id.integer_number4);
        displayInteger4.setText("" + number4);
    }

    public void increaseInteger5(View view) {
        minteger5 = minteger5 + 1;
        display5(minteger5);

    }public void decreaseInteger5(View view) {
        minteger5 = minteger5 - 1;
        display5(minteger5);
    }

    private void display5(int number5) {
        TextView displayInteger5 = (TextView) findViewById(
                R.id.integer_number5);
        displayInteger5.setText("" + number5);
    }

    public void sendMessageCalendar(View view) {
        Intent intent = new Intent(this, MatCal.class);
        startActivity(intent);
    }

    //save progress button adds timestamp value to completedExerciseDates

    public void saveProgress(View view) {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseFirestore fstore = FirebaseFirestore.getInstance();
            String userID = auth.getCurrentUser().getUid();

            // Get the current date as a string (e.g., "2023-09-23")
            //tring currentDate = java.text.DateFormat.getDateInstance().format(new java.util.Date());
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String currentDateTime = sdf.format(new Date());

            // Create a map to hold the scores
            Map<String, Object> scoresData = new HashMap<>();
            scoresData.put("Pursed Lip Breathing", minteger);
            scoresData.put("Position for Drainage", minteger1);
            scoresData.put("Diaphragmatic Breathing", minteger2);
            scoresData.put("ACBT", minteger3);
            scoresData.put("Autogenic Breathing", minteger4);
            scoresData.put("Percussions and Vibrations", minteger5);

            // Create a map to hold the scores under the current date
            Map<String, Object> dateData = new HashMap<>();
            dateData.put(currentDateTime, scoresData);

            // Update the Firestore document with the nested map
            DocumentReference documentReference = fstore.collection("users").document(userID);
            documentReference
                    .update(dateData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            minteger=0;
                            minteger1=0;
                            minteger2=0;
                            minteger3=0;
                            minteger4=0;
                            minteger5=0;
                            // Add this inside your onSuccess method after the Toast
                            TextView textView = findViewById(R.id.integer_number);
                            TextView textView1 = findViewById(R.id.integer_number1);
                            TextView textView2 = findViewById(R.id.integer_number2);
                            TextView textView3 = findViewById(R.id.integer_number3);
                            TextView textView4 = findViewById(R.id.integer_number4);
                            TextView textView5 = findViewById(R.id.integer_number5);

                            textView.setText("0");
                            textView1.setText("0");
                            textView2.setText("0");
                            textView3.setText("0");
                            textView4.setText("0");
                            textView5.setText("0");

                            Log.d(TAG, "onSuccess: saved progress data for " + currentDateTime);
                            Toast.makeText(getApplicationContext(), "Progress saved for " + currentDateTime, Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NotNull Exception e) {
                            Log.d(TAG, "Failed to save progress data: " + e.toString());
                            Toast.makeText(getApplicationContext(), "Failed to save progress data: " + e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }







    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        return false;
    }




}