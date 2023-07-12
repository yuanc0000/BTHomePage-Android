package com.example.bthomepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class SettingsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private Button logout;

    BottomNavigationView bottomNavigationView;

//settings page is displayed

//bottom navigation view is implemented
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        logout = findViewById(R.id.logout);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.settingsActivity);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.diagnosisActivity:
                        Intent intent1 = new Intent(SettingsActivity.this, DiagnosisActivity.class);
                        startActivity(intent1);
                        break;


                    case R.id.exerciseActivity:
                        Intent intent2 = new Intent(SettingsActivity.this, LungsActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.homeActivity:
                        Intent intent3 = new Intent(SettingsActivity.this, HomePage.class);
                        startActivity(intent3);
                        break;

                    case R.id.progressActivity:
                        Intent intent4 = new Intent(SettingsActivity.this, ProgressActivity.class);
                        startActivity(intent4);
                        break;

                    case R.id.settingsActivity:
                        break;



                }
                return false;
            }
        });

    }
    //ability for user to log out
    public void sendMessageLogOut (View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(SettingsActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SettingsActivity.this, Registration.class));


    }
    //ability for user to navigate to lifestyle page
    public void sendMessageLifestyle (View view){
        startActivity(new Intent(SettingsActivity.this, DietActivity.class));


    }
    //ability for user to navigate to faq page
    public void sendMessageFAQ (View view){
        startActivity(new Intent(SettingsActivity.this, FaqActivity.class));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        return false;
    }



}