//package com.example.bthomepage;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//public class COPDDescript extends AppCompatActivity {
////COPD info page is displyed
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_copddescript);
//    }
//
//    public void BackToDiagnoses(View view) {
//        Intent intent = new Intent(this, DiagnosisActivity.class);
//        startActivity(intent);
//    }
//}

package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class COPDDescript extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copddescript_expandable);

        setupExpandableSection(R.id.questionLayout, R.id.f1);
        setupExpandableSection(R.id.questionLayout2, R.id.f2);
        setupExpandableSection(R.id.questionLayout3, R.id.f3);
        setupExpandableSection(R.id.questionLayout4, R.id.f4);
        setupExpandableSection(R.id.questionLayout5, R.id.f5);
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

    public void BackToDiagnoses(View view) {
        Intent intent = new Intent(this, DiagnosisActivity.class);
        startActivity(intent);
    }
}
