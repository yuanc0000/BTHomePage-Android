package com.example.bthomepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class MatCal extends AppCompatActivity {

    private HashMap<String, ScoreData> dataMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_new); // 请确保此处布局名称正确

        fetchDataFromFirebase();
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
                            System.out.println(key);

                            if (key.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                                // The key matches the format, so add it to the set
                                System.out.println("yes right format");
                                //keysWithMatchingFormat.add(key);

                                // Print the value
                                Object value = entry.getValue();
                                System.out.println("Key: " + key + ", Value: " + value);
                            }


                        }
                    }
                }
            }
        });
    }

    private void printDataMap() {
        for (Map.Entry<String, ScoreData> entry : dataMap.entrySet()) {
            String dateTime = entry.getKey();
            ScoreData scoreData = entry.getValue();
            System.out.println("Date: " + dateTime + ", Score: " + scoreData.getScore());
        }
    }

    public class ScoreData {
        int score;

        public ScoreData(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }
}
