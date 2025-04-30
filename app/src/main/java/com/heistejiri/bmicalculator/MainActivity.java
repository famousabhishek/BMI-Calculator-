package com.heistejiri.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    String gender = "";
    int height = 170, weight = 55, age = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        TextView heightText = findViewById(R.id.currentheight);
        SeekBar heightSeek = findViewById(R.id.seekbarforheight);
        TextView weightText = findViewById(R.id.currentweight);
        TextView ageText = findViewById(R.id.currentage);
        Button calculateBtn = findViewById(R.id.calculatebmi);

        RelativeLayout male = findViewById(R.id.male);
        RelativeLayout female = findViewById(R.id.female);

        male.setOnClickListener(v -> {
            gender = "Male";
            male.setBackground(ContextCompat.getDrawable(this, R.drawable.malefemalefocus));
            female.setBackground(ContextCompat.getDrawable(this, R.drawable.malefemalenotfocus));
        });

        female.setOnClickListener(v -> {
            gender = "Female";
            female.setBackground(ContextCompat.getDrawable(this, R.drawable.malefemalefocus));
            male.setBackground(ContextCompat.getDrawable(this, R.drawable.malefemalenotfocus));
        });

        heightSeek.setMax(300);
        heightSeek.setProgress(height);
        heightText.setText(String.valueOf(height));

        heightSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                height = progress;
                heightText.setText(String.valueOf(progress));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        findViewById(R.id.incremetweight).setOnClickListener(v -> {
            weight++;
            weightText.setText(String.valueOf(weight));
        });
        findViewById(R.id.decrementweight).setOnClickListener(v -> {
            if (weight > 1) weight--;
            weightText.setText(String.valueOf(weight));
        });
        findViewById(R.id.incrementage).setOnClickListener(v -> {
            age++;
            ageText.setText(String.valueOf(age));
        });
        findViewById(R.id.decrementage).setOnClickListener(v -> {
            if (age > 1) age--;
            ageText.setText(String.valueOf(age));
        });

        calculateBtn.setOnClickListener(v -> {
            if (gender.isEmpty()) {
                Toast.makeText(this, "Select your gender", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, BMIActivity.class);
                intent.putExtra("gender", gender);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                startActivity(intent);
            }
        });
    }
}