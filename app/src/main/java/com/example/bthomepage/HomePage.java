package com.example.bthomepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class HomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.homeActivity);

//bottom navigation view implemented
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.diagnosisActivity:
                        Intent intent1 = new Intent(HomePage.this, DiagnosisActivity.class);
                        startActivity(intent1);
                        return true;

                    case R.id.exerciseActivity:
                        Intent intent2 = new Intent(HomePage.this, LungsActivity.class);
                        startActivity(intent2);
                        return true;

                    case R.id.homeActivity:
                        // No need to handle, just return true
                        return true;

                    case R.id.progressActivity:
                        Intent intent4 = new Intent(HomePage.this, ProgressActivity.class);
                        startActivity(intent4);
                        return true;
                }
                return false;
            }
        });

//click on image leads to diagnosis options
        ImageView imgClickDiagnosis;
        imgClickDiagnosis = (ImageView)findViewById(R.id.imgDiagnosis);
        Intent intentdiagnosis = new Intent(this, DiagnosisActivity.class);
        imgClickDiagnosis.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v)
            {
//                Toast.makeText(HomePage.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                startActivity(intentdiagnosis);
            } });
//click on image leads to exercise options
        ImageView imgClickLungs;
        imgClickLungs = (ImageView)findViewById(R.id.imgLungs);
        Intent intentlungs = new Intent(this, LungsActivity.class);
        imgClickLungs.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v)
            {
//                Toast.makeText(HomePage.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                startActivity(intentlungs);
            } });
//click on image leads to information on diet and lifestyle
        ImageView imgClickDiet;
        imgClickDiet = (ImageView)findViewById(R.id.img_recommendation);
        Intent intentdiet = new Intent(this, DietActivity.class);
        imgClickDiet.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v)
            {
//                Toast.makeText(HomePage.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                startActivity(intentdiet);
            } });
//click on image leads to progress logging page
        ImageView imgClickProgress;
        imgClickProgress = (ImageView)findViewById(R.id.imgProgress);
        Intent intentprogress = new Intent(this, ProgressActivity.class);
        imgClickProgress.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v)
            {
//                Toast.makeText(HomePage.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                startActivity(intentprogress);
            } });
//click on image leads to calendar page
        ImageView imgClickSettings;
        imgClickSettings = (ImageView)findViewById(R.id.imgCalendar);
        Intent intentCalendar = new Intent(this, MatCal.class);
        imgClickSettings.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v)
            {
//                Toast.makeText(HomePage.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                startActivity(intentCalendar);
            } });
//click on image leads to faq page
        ImageView imgClickFaq;
        imgClickFaq = (ImageView)findViewById(R.id.imgFAQ);
        Intent intentfaq = new Intent(this, HelpActivity.class);
        imgClickFaq.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v)
            {
//                Toast.makeText(HomePage.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                startActivity(intentfaq);
            } });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(HomePage.this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomePage.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        return false;
    }

}




