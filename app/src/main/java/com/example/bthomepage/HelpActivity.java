package com.example.bthomepage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//public class HelpActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
public class HelpActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_options);

        Button sendToUserButton = findViewById(R.id.emailbtn);

        sendToUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataFromFirestore();
            }
        });
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

//    public void sendMessageEmail(View view) {
//        Intent intent = new Intent(this, EmailActivity.class);
//        startActivity(intent);
//    }

    private static final String TAG = "EmailActivity"; // For logging

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
                    System.out.println("create csvContent with len =" + csvContent.length());
                    String filePath = saveToCSVFile(csvContent); // Get the file path
                    if (filePath != null) {
                        sendEmailWithAttachment(filePath);
                    }
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

    private String saveToCSVFile(String csvContent) {
        String fileName = "BreatheEasy_progress.csv";
        File file = new File(getExternalFilesDir(null), fileName);
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(csvContent.getBytes());
            Log.d(TAG, "Saved to " + file.getAbsolutePath());
            return file.getAbsolutePath(); // Return the file path
        } catch (IOException e) {
            Log.e(TAG, "Error saving CSV file", e);
            return null;
        }
    }

    private void sendEmailWithAttachment(String filePath) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Progress with BreatheEasy");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Please find my progress report attached.");

        // Create a content URI for the file using FileProvider
        Uri fileUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", new File(filePath));

        // Grant read permission to the receiving app
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.putExtra(Intent.EXTRA_STREAM, fileUri);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (ActivityNotFoundException ex) {
            Log.e(TAG, "No email clients installed.", ex);
        }
    }


//    @Override
//    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//        return false;
//    }
}

