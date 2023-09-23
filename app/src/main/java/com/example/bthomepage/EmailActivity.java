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
//        */
    }
}
