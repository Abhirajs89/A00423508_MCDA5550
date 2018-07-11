package com.example.abhiraj.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CalculateBMI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);
    }

    public void calculateBMI(View view){

        //Gets Height
        EditText height = (EditText)findViewById(R.id.textHeight);
        String value = height.getText().toString();
        Double heightAsInt = Double.parseDouble(value);

        //Gets Weight
        EditText weight = (EditText)findViewById(R.id.textWeight);
        String weightValue = weight.getText().toString();
        Double weighttAsInt = Double.parseDouble(weightValue);

        Double calc = (weighttAsInt/(heightAsInt*heightAsInt));
        EditText result = (EditText) findViewById(R.id.textResult);

        result.setText(calc.toString());
    }
}
