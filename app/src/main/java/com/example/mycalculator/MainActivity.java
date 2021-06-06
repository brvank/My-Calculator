package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private void createIntentsForEveryActivity(){
        //intent for basic activity
        TextView basic = findViewById(R.id.basic);
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BasicActivity.class);
                startActivity(intent);
            }
        });

        //intent for scientific activity
        TextView scientific = findViewById(R.id.scientific);
        scientific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ScientificActivity.class);
                startActivity(intent);
            }
        });

        //intent for bmi activity
        TextView bmi = findViewById(R.id.bmi);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BmiActivity.class);
                startActivity(intent);
            }
        });

        //intent for interest activity
        TextView interest = findViewById(R.id.interest);
        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InterestActivity.class);
                startActivity(intent);
            }
        });

        //intent for conversion activity
        TextView conversion = findViewById(R.id.conversion);
        conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ConversionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createIntentsForEveryActivity();
    }
}