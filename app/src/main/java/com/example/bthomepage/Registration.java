package com.example.bthomepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

import java.util.Calendar;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private EditText userName, userPassword, userEmail;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth auth;


//registration page is displayed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

    }

    public void sendMessageReg(View view) {
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnRegister);

        String userPasswordString = userPassword.getText().toString();
        String userEmailString = userEmail.getText().toString();
//password requirements for user to register
        if (userPasswordString.isEmpty() || userEmailString.isEmpty()){
            Toast.makeText(Registration.this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }else if (userPasswordString.length() < 6){
            Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
        }
        else {
            //if requirements are met then user is registered

            auth = FirebaseAuth.getInstance();
            registerUser(userEmailString, userPasswordString);
        }
    }

    private void registerUser(String userEmail, String userPassword) {
//user is registered on firestore
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String userID = auth.getCurrentUser().getUid();
                    FirebaseFirestore fstore = FirebaseFirestore.getInstance();
                    DocumentReference documentReference = fstore.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("email", userEmail);
                    user.put("name", userEmail);
                    user.put("uid", userID);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Registration.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Registration.this, HomePage.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(Registration.this, "Unable to save your registration information", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else{

                    Exception e = task.getException();
                    String errorMessage = e != null ? e.getMessage() : "Unknown error";
                    Toast.makeText(Registration.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
//ability for user to navigate to login page
    public void sendMessageOldLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
        startActivity(intent);

    }
}
