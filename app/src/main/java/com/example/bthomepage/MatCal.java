//import android.os.Bundle;
//import android.widget.CalendarView;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class MatCal extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calendar_new);
//
//        CalendarView calendarView = findViewById(R.id.calendarView);
//
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                // Display the selected date using a Toast
//                String selectedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
//                Toast.makeText(MatCal.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}

package com.example.bthomepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.HashSet;

public class MatCal extends AppCompatActivity {

    private CalendarView calendarView;
    private HashSet<Long> loginDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_new);

        calendarView = findViewById(R.id.calendarView);
        loginDates = new HashSet<>(); // Store login dates as milliseconds

        // Simulated list of login dates (replace with actual dates)
        Calendar calendar = Calendar.getInstance();
        loginDates.add(calendar.getTimeInMillis()); // Add today's date as an example

        // Set up the date change listener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                if (loginDates.contains(selectedDate.getTimeInMillis())) {
                    // Date is logged in
                    Toast.makeText(MatCal.this, "Logged-in Date: " + selectedDate.getTime(), Toast.LENGTH_SHORT).show();
                } else {
                    // Date is not logged in
                    Toast.makeText(MatCal.this, "Normal Date: " + selectedDate.getTime(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void sendBack1(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}



