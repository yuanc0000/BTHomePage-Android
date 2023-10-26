package com.example.bthomepage;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
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
    private HashSet<Long> loginDates;
    private TextView monthYearTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_new);
        fetchDataFromFirebase();

        compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        loginDates = new HashSet<>();

        //SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        //String monthYear = dateFormat.format(firstDayOfNewMonth);
        //monthYearTextView.setText(monthYear);


        // Simulated list of login dates (replace with actual dates)
        /*
        Calendar calendar = Calendar.getInstance();
        loginDates.add(calendar.getTimeInMillis()); // Add today's date as an example
        Event ev1 = new Event(Color.GREEN, calendar.getTimeInMillis(), "Logged in on this day");
        compactCalendarView.addEvent(ev1);*/

        // Highlight a specific date (e.g., October 17, 2023)
        /*
        Calendar highlightDate = Calendar.getInstance();
        highlightDate.set(2023, Calendar.OCTOBER, 17);
        Event highlightEvent = new Event(Color.BLUE, highlightDate.getTimeInMillis(), "Highlighted Date");
        compactCalendarView.addEvent(highlightEvent);
        */

        // Initialize the TextView for the month and year
        monthYearTextView = findViewById(R.id.monthYearTextView);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            /*
            @Override

            public void onDayClick(Date dateClicked) {
                if (loginDates.contains(dateClicked.getTime())) {
                    // Date is logged in
                    Toast.makeText(MatCal.this, "Logged-in Date: " + dateClicked, Toast.LENGTH_SHORT).show();
                } else {
                    // Date is not logged in
                    Toast.makeText(MatCal.this, "Normal Date: " + dateClicked, Toast.LENGTH_SHORT).show();
                }
            } */

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                // Update the TextView with the current month and year
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
                String monthYear = dateFormat.format(firstDayOfNewMonth);
                monthYearTextView.setText(monthYear);

                Toast.makeText(MatCal.this, "Month changed: " + monthYear, Toast.LENGTH_SHORT).show();
            }
            /*
            @Override
            public void onDayClick(Date dateClicked) {
                Calendar clickedCalendar = Calendar.getInstance();
                clickedCalendar.setTime(dateClicked);
                LocalDate clickedDate = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    clickedDate = LocalDate.of(clickedCalendar.get(Calendar.YEAR), clickedCalendar.get(Calendar.MONTH) + 1, clickedCalendar.get(Calendar.DAY_OF_MONTH));
                }

                if (uniqueDates.contains(clickedDate)) {
                    // Date is in the set of unique dates
                    AlertDialog.Builder builder = new AlertDialog.Builder(MatCal.this);
                    builder.setTitle("Details for " + clickedDate);
                    builder.setMessage("Your detailed information or events for this date go here."); // Replace this with actual data or events
                    builder.setPositiveButton("OK", null);
                    builder.show();
                } else {
                    // Date is not in the set of unique dates
                    Toast.makeText(MatCal.this, "Normal Date: " + dateClicked, Toast.LENGTH_SHORT).show();
                }
            }
             */
            private String formatDataForDisplay(Map<String, Object> dataMap) {
                StringBuilder formattedData = new StringBuilder();

                for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    String formattedEntry = "<b>" + key + ":</b> " + value.toString() + "<br>";  // 使用HTML标签来进行加粗并换行
                    formattedData.append(formattedEntry);
                }

                return formattedData.toString();
            }

            public void onDayClick(Date dateClicked) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String clickedDateString = dateFormat.format(dateClicked); // Convert the clicked date to "yyyy-MM-dd" format

                FirebaseFirestore fstore = FirebaseFirestore.getInstance();
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("users").document(userID);

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

                                    AlertDialog.Builder builder = new AlertDialog.Builder(MatCal.this);
                                    builder.setTitle("Details for " + clickedDateString);
                                    builder.setMessage(Html.fromHtml(formattedMessage));  // 使用Html.fromHtml来解析HTML标签
                                    builder.setPositiveButton("OK", null);
                                    builder.show();

                                    break;
                                }

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

                                // Print the value
                                System.out.println("date: " + date);
                            }


                        }
                    }
                }
            }
        });
    }



    /*
    private void printDataMap() {
        for (Map.Entry<String, ScoreData> entry : dataMap.entrySet()) {
            String dateTime = entry.getKey();
            ScoreData scoreData = entry.getValue();
            System.out.println("Date: " + dateTime + ", Score: " + scoreData.getScore());
        }
    }  */
}