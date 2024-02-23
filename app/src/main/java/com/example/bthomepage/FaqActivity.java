//package com.example.bthomepage;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//public class FaqActivity extends AppCompatActivity {
////faq information displayed
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_faq);
//    }
//
//    public void sendBack1(View view) {
//        Intent intent = new Intent(this, HomePage.class);
//        startActivity(intent);
//    }
//
//}


package com.example.bthomepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FaqActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_new);

        setupExpandableSection(R.id.questionLayout, R.id.f1);
        setupExpandableSection(R.id.questionLayout2, R.id.f2);
        setupExpandableSection(R.id.questionLayout3, R.id.f3);
        setupExpandableSection(R.id.questionLayout4, R.id.f4);
    }

    private void setupExpandableSection(int questionLayoutId, int answerTextViewId) {
        LinearLayout questionLayout = findViewById(questionLayoutId);
        final TextView answerTextView = findViewById(answerTextViewId);

        questionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDescription(answerTextView);
            }
        });
    }

    private void toggleDescription(TextView answerTextView) {
        int visibility = answerTextView.getVisibility();
        answerTextView.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    public void sendBack1(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

}


