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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FaqActivity extends AppCompatActivity {
    ImageView up1, up2, up3, up4;
    TextView f1, f2, f3, f4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_expandable);

        //initialize variables
        up1 = findViewById(R.id.up1);
        up2 = findViewById(R.id.up2);
        up3 = findViewById(R.id.up3);
        up4 = findViewById(R.id.up4);

        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);

        // Set click listeners for expanding and collapsing FAQ sections
        findViewById(R.id.down1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show1();
            }
        });

        findViewById(R.id.down2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show2();
            }
        });

        findViewById(R.id.down3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show3();
            }
        });

        findViewById(R.id.down4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show4();
            }
        });

        findViewById(R.id.up1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide1();
            }
        });

        findViewById(R.id.up2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide2();
            }
        });

        findViewById(R.id.up3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide3();
            }
        });

        findViewById(R.id.up4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide4();
            }
        });
    }

    // Show and hide methods for FAQ sections
    private void show1() {
        f1.setVisibility(View.VISIBLE);
        up1.setVisibility(View.VISIBLE);
        findViewById(R.id.down1).setVisibility(View.GONE);
    }

    private void hide1() {
        f1.setVisibility(View.GONE);
        up1.setVisibility(View.GONE);
        findViewById(R.id.down1).setVisibility(View.VISIBLE);
    }

    private void show2() {
        f2.setVisibility(View.VISIBLE);
        up2.setVisibility(View.VISIBLE);
        findViewById(R.id.down2).setVisibility(View.GONE);
    }

    private void hide2() {
        f2.setVisibility(View.GONE);
        up2.setVisibility(View.GONE);
        findViewById(R.id.down2).setVisibility(View.VISIBLE);
    }

    private void show3() {
        f3.setVisibility(View.VISIBLE);
        up3.setVisibility(View.VISIBLE);
        findViewById(R.id.down3).setVisibility(View.GONE);
    }

    private void hide3() {
        f3.setVisibility(View.GONE);
        up3.setVisibility(View.GONE);
        findViewById(R.id.down3).setVisibility(View.VISIBLE);
    }

    private void show4() {
        f3.setVisibility(View.VISIBLE);
        up3.setVisibility(View.VISIBLE);
        findViewById(R.id.down4).setVisibility(View.GONE);
    }

    private void hide4() {
        f3.setVisibility(View.GONE);
        up3.setVisibility(View.GONE);
        findViewById(R.id.down4).setVisibility(View.VISIBLE);
    }


    // Add the method for the button
}


