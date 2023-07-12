package com.example.bthomepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

//import com.applandeo.materialcalendarview.CalendarView;
//import com.applandeo.materialcalendarview.EventDay;
//import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;



public class MatCal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_cal);


        MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView2);
//calendar created referencing com.prolificinteractive.materialcalendarview.MaterialCalendarView

        //minimum and maximum calendar dates set
        materialCalendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2019, 12, 31))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

//user cannot manually select date on calendar
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userID = auth.getCurrentUser().getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection("users").document(userID);
//specific user is referenced trhoguh unique id and if task is successful
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    //MyUser class is referenced is to retrieve user data
                    DocumentSnapshot document = task.getResult();
                    MyUser user = document.toObject(MyUser.class);
                    String strdata = user.getName();
                    List<Timestamp> timestamps = user.getCompletedExerciseDates();
                    Timestamp myTime;
                    Date date;
//completed exercise dates are looped through and date is highlighted on calendar
                    for (int i = 0; i < timestamps.size(); i++) {
                        myTime = timestamps.get(i);
                        date = myTime.toDate();
                        materialCalendarView.setDateSelected(date, true);

                    }

                } else {
                    Toast.makeText(MatCal.this, "Failed to get exercise dates at this time", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    //button click leads to progress page

    public void sendBack(View view) {
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }

}





