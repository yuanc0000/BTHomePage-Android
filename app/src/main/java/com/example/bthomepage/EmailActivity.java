/*
package com.example.bthomepage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_doctor);

        final EditText doctorEmailEditText = findViewById(R.id.doctor_email_edittext);
        Button sendToUserButton = findViewById(R.id.send_to_user_button);
        Button sendToDoctorButton = findViewById(R.id.send_to_doctor_button);

//        sendToUserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String userEmail = "user@example.com"; // Replace with actual user email
//
//                // get data
//                String exerciseData = "Exercise data goes here";
//
//                // Send email to user
//                sendEmail(userEmail, "Congratulations on doing your eye exercises! Here are your weekly results:\n\n" + exerciseData);
//            }
//        });
//
//        sendToDoctorButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                final String doctorEmail = doctorEmailEditText.getText().toString().trim();
////                if (!doctorEmail.isEmpty()) {
////                    // get user's name and exercise data
////                    final String userName = "Username"; // user's name
////                    final String exerciseData = "Exercise data goes here";
////
////                    // Send email to doctor
////                    sendEmail(doctorEmail, "The following are the weekly results from " + userName + ":\n\n" + exerciseData);
////                } else {
////                    Toast.makeText(EmailActivity.this, "Please enter the doctor's email", Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
//    }
//
//    private void sendEmail(String recipientEmail, String message) {
//        // email sending logic
//        /*
//        if (emailSentSuccessfully) {
//            Toast.makeText(this, "Email sent successfully", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Failed to send email", Toast.LENGTH_SHORT).show();
//        }
//
    }
}
 */


package com.example.bthomepage;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmailActivity extends AppCompatActivity {
    private static final String TAG = "EmailActivity"; // For logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_doctor);

        Button sendToUserButton = findViewById(R.id.send_to_user_button);

        sendToUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataFromFirestore();
            }
        });
    }

    private void fetchDataFromFirestore() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        String userID = auth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("users").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    for (String date : documentSnapshot.getData().keySet()) {
                        System.out.println("Date: " + date);
                        Object dataObj = documentSnapshot.get(date);
                        if (dataObj instanceof Map) {
                            Map<String, Long> dataMap = (Map<String, Long>) dataObj;
                            for (Map.Entry<String, Long> entry : dataMap.entrySet()) {
                                System.out.println("CNT Name: " + entry.getKey() + ", Value: " + entry.getValue());
                            }
                        }
                    }
                    String csvContent = generateCSVContent(documentSnapshot);
                    System.out.println("create csvContent with len ="+csvContent.length());
                    saveToCSVFile(csvContent);
                    System.out.println("after call saveToCSVFile(csvContent);");

                } else {
                    Log.d(TAG, "No data found for user.");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Error fetching data: ", e);
            }
        });
    }

    /*
    private String generateCSVContent(DocumentSnapshot documentSnapshot) {
        StringBuilder csvBuilder = new StringBuilder();

        // Add header with column titles (dates)
        csvBuilder.append("Exercise Name,");
        for (String date : documentSnapshot.getData().keySet()) {
            csvBuilder.append(date).append(",");
        }
        csvBuilder.append("\n");

        // Iterate through exercises and fill data
        String firstDateKey = documentSnapshot.getData().keySet().iterator().next();
        Map<String, Long> firstDateData = (Map<String, Long>) documentSnapshot.get(firstDateKey);
        for (String exerciseName : firstDateData.keySet()) {
            csvBuilder.append(exerciseName).append(",");
            for (String date : documentSnapshot.getData().keySet()) {
                Object dataObj = documentSnapshot.get(date);
                if (dataObj instanceof Map) {
                    Map<String, Long> dataMap = (Map<String, Long>) dataObj;
                    csvBuilder.append(dataMap.get(exerciseName)).append(",");
                }
            }
            csvBuilder.append("\n");
        }

        return csvBuilder.toString();
    }*/
    private String generateCSVContent(DocumentSnapshot documentSnapshot) {
        StringBuilder csvBuilder = new StringBuilder();

        // First, create a list of valid date keys and valid exercise names
        List<String> validDates = new ArrayList<>();
        Set<String> exerciseNames = new HashSet<>();
        for (String key : documentSnapshot.getData().keySet()) {
            if (key.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                validDates.add(key);
                Map<String, Long> dataMap = (Map<String, Long>) documentSnapshot.get(key);
                exerciseNames.addAll(dataMap.keySet());
            }
        }

        // Create the header row with exercise names
        csvBuilder.append("Date,");
        for (String exercise : exerciseNames) {
            csvBuilder.append(exercise).append(",");
        }
        csvBuilder.append("\n");

        // Iterate through each valid date and fill in the data
        for (String date : validDates) {
            csvBuilder.append(date).append(",");
            Map<String, Long> dataMap = (Map<String, Long>) documentSnapshot.get(date);
            for (String exercise : exerciseNames) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    csvBuilder.append(dataMap.getOrDefault(exercise, 0L)).append(",");
                }
            }
            csvBuilder.append("\n");
        }

        return csvBuilder.toString();
    }


    private void saveToCSVFile(String csvContent) {
        String fileName = "exercise_data.csv";
        File file = new File(getExternalFilesDir(null), fileName);
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(csvContent.getBytes());
            Log.d(TAG, "Saved to " + file.getAbsolutePath());
            System.out.println("Saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.e(TAG, "Error saving CSV file", e);
            System.out.println("Error saving CSV file "+e);
        }
    }




}

