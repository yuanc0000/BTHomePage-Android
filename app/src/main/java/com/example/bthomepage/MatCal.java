package com.example.bthomepage;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MatCal extends AppCompatActivity {

    Set<LocalDate> uniqueDates = new HashSet<>();
    private CompactCalendarView compactCalendarView;
    private TextView monthYearTextView;
    BottomNavigationView bottomNavigationView;

    private void updateMonthYearDisplay(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        String monthYear = dateFormat.format(date);
        monthYearTextView.setText(monthYear);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_new);
        fetchDataFromFirebase();

        monthYearTextView = findViewById(R.id.monthYearTextView);
// 显示当前月份
        updateMonthYearDisplay(new Date());

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.progressActivity);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.diagnosisActivity:
                        Intent intent1 = new Intent(MatCal.this, DiagnosisActivity.class);
                        startActivity(intent1);
                        break;


                    case R.id.exerciseActivity:
                        Intent intent2 = new Intent(MatCal.this, LungsActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.homeActivity:
                        Intent intent3 = new Intent(MatCal.this, HomePage.class);
                        startActivity(intent3);
                        break;

                    case R.id.progressActivity:
                        break;

                    case R.id.settingsActivity:
                        Intent intent5 = new Intent(MatCal.this, SettingsActivity.class);
                        startActivity(intent5);
                        break;



                }
                return false;
            }
        });



        compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        // Initialize the TextView for the month and year
        monthYearTextView = findViewById(R.id.monthYearTextView);


        // Deselect the current day
        compactCalendarView.shouldSelectFirstDayOfMonthOnScroll(false);
        //compactCalendarView.deselectCurrentDay(); // This will make sure no day is selected

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                // Update the TextView with the current month and year
//                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
//                String monthYear = dateFormat.format(firstDayOfNewMonth);
//                monthYearTextView.setText(monthYear);

                updateMonthYearDisplay(firstDayOfNewMonth);

                Toast.makeText(MatCal.this, "Month changed: " + new SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(firstDayOfNewMonth), Toast.LENGTH_SHORT).show();

                //Toast.makeText(MatCal.this, "Month changed: " + monthYear, Toast.LENGTH_SHORT).show();
            }

            private String formatDataForDisplay(Map<String, Object> dataMap) {


                StringBuilder formattedData = new StringBuilder();

                for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    String formattedEntry = key + ":   " + value.toString() + "<br>";
                    formattedData.append(formattedEntry);
                }

                return formattedData.toString();
            }

            public void onDayClick(Date dateClicked) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String clickedDateString = dateFormat.format(dateClicked); // Convert the clicked date to "yyyy-MM-dd" format
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEE MMM dd yyyy", Locale.getDefault());
                String clickedDateString1 = dateFormat1.format(dateClicked);

                FirebaseFirestore fstore = FirebaseFirestore.getInstance();
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("users").document(userID);

                final boolean[] foundDataForDate = {false}; // Set an initial flag as false

                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Since keys are in "yyyy-MM-dd HH:mm:ss" format, let's iterate to find any keys that start with our clickedDateString
                            for (Map.Entry<String, Object> entry : documentSnapshot.getData().entrySet()) {
                                String key = entry.getKey();
                                if (key.startsWith(clickedDateString)) {

                                    Map<String, Object> dataForTheDate = (Map<String, Object>) entry.getValue();

                                    String formattedMessage = formatDataForDisplay(dataForTheDate);
                                    System.out.println(formattedMessage);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(MatCal.this);
                                    builder.setTitle(clickedDateString1);
                                    builder.setMessage(Html.fromHtml(formattedMessage));
                                    builder.setPositiveButton("OK", null);
                                    builder.show();

                                    foundDataForDate[0] = true; // Set the flag as true because we found data
                                    break;
                                }
                            }
                            if (!foundDataForDate[0]) {  // If data was not found, then display the Toast for normal date
                                Toast.makeText(MatCal.this, "Normal Date: " + dateClicked, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MatCal.this, "Error fetching details.", Toast.LENGTH_SHORT).show();
                    }
                });
            }



        });
    }

    private void fetchDataFromFirebase() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        String userID = auth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        for (Map.Entry<String, Object> entry : document.getData().entrySet()) {
                            String key = entry.getKey();


                            if (key.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {

                                // Extract year, month, and day and convert to LocalDate
                                LocalDate date = null;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    date = LocalDate.parse(key.substring(0, 10));
                                }

                                // Add the extracted date to the set
                                uniqueDates.add(date);

                                // Highlight the specific date
                                Calendar highlightDate = Calendar.getInstance();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    highlightDate.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth()); // Subtract 1 from month value because Calendar months are 0-based
                                }
                                Event highlightEvent = new Event(Color.BLUE, highlightDate.getTimeInMillis(), "Highlighted Date");
                                compactCalendarView.addEvent(highlightEvent);
                            }


                        }
                    }
                }
            }
        });
    }
    public void backToProgress(View view) {
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }


}