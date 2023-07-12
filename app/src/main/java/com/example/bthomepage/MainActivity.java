package com.example.bthomepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.bthomepage.MESSAGE";
    private FirebaseAuth auth;
    private TextView userReg;
//user login page is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

    }

    /** Called when the user taps the login button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, HomePage.class);
        EditText editTextUser = (EditText) findViewById(R.id.etUsername);
        EditText editTextPassword = (EditText) findViewById(R.id.etPassword);
        String username = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();


//checks if all fields are complete to proceed
        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(MainActivity.this, "Please enter all details correctly", Toast.LENGTH_SHORT).show();
        }
        else {

            loginUser(username, password);


        }
    }

    private void loginUser(String username, String password) {

//user is logged in through firebase verification
        auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Login successful
                    String userID;

//unique user id is called and path is referenced in firestore
                    userID = auth.getCurrentUser().getUid();
                    FirebaseFirestore fstore = FirebaseFirestore.getInstance();
                    DocumentReference documentReference = fstore.collection("users").document(userID);
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                MyUser myuser = document.toObject(MyUser.class);
                                //if user has not been initiated in firestore, object is created with credentials stored
                                if (myuser == null) {
                                    //created since sometimes user isn't registered with firestore even if authenticated, therefore created object
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("email", username);
                                    user.put("name", username);
                                    user.put("uid", userID);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NotNull Exception e) {
                                            Log.d(TAG, "onFailure: " + e.toString());
                                        }
                                    });

                                }

                            }
                        }
                    });
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    //button click leads to registration page

    public void sendMessagenewReg(View view) {
        Intent intent = new Intent(this, Registration.class);
        userReg = (TextView)findViewById(R.id.tvUserReg);
        startActivity(intent);


    }
}
