package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//completed
public class BmiActivity extends AppCompatActivity {

    private float heightData;
    private float weightData;
    EditText height_data;
    EditText weight_data;
    Button calculate_bmi;
    TextView result;

    // Function to calculate the BMI
    private void calculateBMI(){
        RelativeLayout background = findViewById(R.id.backgroundOfBMI);
        String resultString = String.format("%.1f",weightData/(heightData*heightData));
        //Log.i("BmiActivity", resultString);
        if(heightData == 0 || weightData == 0){
            Toast.makeText(BmiActivity.this,"Invalid height or weight!!!",Toast.LENGTH_SHORT).show();
            return;
        }else if(Float.valueOf(resultString)>24){
            result.setText("Your BMI is "+ resultString+"\nYou are overweight!!!");
            background.setBackgroundColor(0x22FF0000);
        }else if(Float.valueOf(resultString)<19){
            result.setText("Your BMI is "+ resultString+"\nYou are underweight!!!");
            background.setBackgroundColor(0x22FF0000);
        }else{
            result.setText("Your BMI is "+ resultString+"\nYour weight is normal");
            background.setBackgroundColor(0x2200FF00);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        calculate_bmi = findViewById(R.id.calculateBMI);
        result = findViewById(R.id.result);

        height_data = findViewById(R.id.height_data);
        weight_data = findViewById(R.id.weight_data);

        calculate_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    heightData = Float.parseFloat(height_data.getText().toString());
                    weightData = Float.parseFloat(weight_data.getText().toString());
                    calculateBMI();
                } catch (NumberFormatException e) {
                    Toast.makeText(BmiActivity.this,"Enter your height and weight!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}