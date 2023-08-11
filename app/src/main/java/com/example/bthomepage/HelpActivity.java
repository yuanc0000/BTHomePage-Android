package com.example.bthomepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

//public class HelpActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
public class HelpActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_options);
    }
//    BottomNavigationView bottomNavigationView;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lungs);
//
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.exerciseActivity);
//
////bottom navigation implemented
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.diagnosisActivity:
//                        Intent intent1 = new Intent(HelpActivity.this, DiagnosisActivity.class);
//                        startActivity(intent1);
//                        break;
//
//                    case R.id.exerciseActivity:
//                        Intent intent2 = new Intent(HelpActivity.this, LungsActivity.class);
//                        startActivity(intent2);
//                        break;
//
//                    case R.id.homeActivity:
//                        Intent intent3 = new Intent(HelpActivity.this, HomePage.class);
//                        startActivity(intent3);
//                        break;
//
//                    case R.id.progressActivity:
//                        Intent intent4 = new Intent(HelpActivity.this, ProgressActivity.class);
//                        startActivity(intent4);
//                        break;
//
//                }
//                return false;
//            }
//        });
////        bottomNavigationView.setSelectedItemId(R.id.person);
//
//
//    }

    public void sendMessageFAQ(View view) {
        Intent intent = new Intent(this, FaqActivity.class);
        startActivity(intent);
    }

    public void sendMessageEmail(View view) {
        Intent intent = new Intent(this, EmailActivity.class);
        startActivity(intent);
    }


//    @Override
//    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//        return false;
//    }
}

