package com.example.bthomepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class DiagnosisActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.diagnosisActivity);
        //bottom navigation is implemented and new activity starts depending on icon selected

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.diagnosisActivity:
                        break;


                    case R.id.exerciseActivity:
                        Intent intent2 = new Intent(DiagnosisActivity.this, LungsActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.homeActivity:
                        Intent intent3 = new Intent(DiagnosisActivity.this, HomePage.class);
                        startActivity(intent3);
                        break;

                    case R.id.progressActivity:
                        Intent intent4 = new Intent(DiagnosisActivity.this, ProgressActivity.class);
                        startActivity(intent4);
                        break;

                    case R.id.settingsActivity:
                        Intent intent5 = new Intent(DiagnosisActivity.this, SettingsActivity.class);
                        startActivity(intent5);
                        break;


                }
                return false;
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        return false;
    }

//different diagnosis options and user selects icon to navigate there
    public void sendMessageCOPD(View view) {
        Intent intent = new Intent(this, COPDDescript.class);
        startActivity(intent);
    }

    public void sendMessageCF(View view) {
        Intent intent = new Intent(this, CFDescript.class);
        startActivity(intent);
    }

    public void sendMessageCB(View view) {
        Intent intent = new Intent(this, CBDescript.class);
        startActivity(intent);
    }

    public void sendMessageAsthma(View view) {
        Intent intent = new Intent(this, AsthmaDescript.class);
        startActivity(intent);
    }

}



//        DiagnosisActivity diagnosisactivity = new DiagnosisActivity();
//        LungsActivity lungsactivity = new LungsActivity();
//        DietActivity dietactivity = new DietActivity();
//        ProgressActivity progressactivity = new ProgressActivity();
//        SettingsActivity settingsactivity = new SettingsActivity();

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.diagnosisActivity:
//                getSupportParentActivityIntent().replace(R.id.container, diagnosisactivity).commit();
//                return true;
//
//            case R.id.home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, secondFragment).commit();
//                return true;
//
//            case R.id.settings:
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, thirdFragment).commit();
//                return true;
//        }
//        return false;
//    }




