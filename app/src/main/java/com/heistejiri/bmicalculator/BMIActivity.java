package com.heistejiri.bmicalculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class BMIActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Result");

        TextView bmiDisplay = findViewById(R.id.bmidisplay);
        TextView bmiCategory = findViewById(R.id.bmicategorydispaly);
        TextView genderView = findViewById(R.id.genderdisplay);
        Button goBack = findViewById(R.id.gotomain);
        ImageView imageView = findViewById(R.id.imageview);
        RelativeLayout layout = findViewById(R.id.background);

        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        int height = intent.getIntExtra("height", 170);
        int weight = intent.getIntExtra("weight", 55);

        float heightInM = height / 100f;
        float bmi = weight / (heightInM * heightInM);
        String bmiResult = String.format("%.1f", bmi);
        bmiDisplay.setText(bmiResult);

        genderView.setText(gender);

        if (bmi < 16) {
            bmiCategory.setText("Severe Thinness");
            layout.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.crosss);
        } else if (bmi < 17) {
            bmiCategory.setText("Moderate Thinness");
            imageView.setImageResource(R.drawable.warning);
        } else if (bmi < 18.5) {
            bmiCategory.setText("Mild Thinness");
            imageView.setImageResource(R.drawable.warning);
        } else if (bmi <= 24.9) {
            bmiCategory.setText("Normal");
            imageView.setImageResource(R.drawable.ok);
        } else if (bmi <= 29.9) {
            bmiCategory.setText("Overweight");
            imageView.setImageResource(R.drawable.warning);
        } else if (bmi <= 34.9) {
            bmiCategory.setText("Obese Class I");
            imageView.setImageResource(R.drawable.warning);
        } else {
            bmiCategory.setText("Obese Class II");
            layout.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.crosss);
        }

        goBack.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }
}