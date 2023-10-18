package com.example.bthomepage;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

public class MatCal extends AppCompatActivity {

    private CompactCalendarView compactCalendarView;
    private HashSet<Long> loginDates;
    private TextView monthYearTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_new);

        compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        loginDates = new HashSet<>();

        // Simulated list of login dates (replace with actual dates)
        Calendar calendar = Calendar.getInstance();
        loginDates.add(calendar.getTimeInMillis()); // Add today's date as an example
        Event ev1 = new Event(Color.GREEN, calendar.getTimeInMillis(), "Logged in on this day");
        compactCalendarView.addEvent(ev1);

        // Highlight a specific date (e.g., October 17, 2023)
        Calendar highlightDate = Calendar.getInstance();
        highlightDate.set(2023, Calendar.OCTOBER, 17);
        Event highlightEvent = new Event(Color.BLUE, highlightDate.getTimeInMillis(), "Highlighted Date");
        compactCalendarView.addEvent(highlightEvent);

        // Initialize the TextView for the month and year
        monthYearTextView = findViewById(R.id.monthYearTextView);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                if (loginDates.contains(dateClicked.getTime())) {
                    // Date is logged in
                    Toast.makeText(MatCal.this, "Logged-in Date: " + dateClicked, Toast.LENGTH_SHORT).show();
                } else {
                    // Date is not logged in
                    Toast.makeText(MatCal.this, "Normal Date: " + dateClicked, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                // Update the TextView with the current month and year
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
                String monthYear = dateFormat.format(firstDayOfNewMonth);
                monthYearTextView.setText(monthYear);

                Toast.makeText(MatCal.this, "Month changed: " + monthYear, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendBack1(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
