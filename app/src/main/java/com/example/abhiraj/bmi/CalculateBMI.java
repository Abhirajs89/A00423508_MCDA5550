package com.example.abhiraj.bmi;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculateBMI extends AppCompatActivity {

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = bundle.getString("email");
        }
    }

    public void viewHistory(View view){
        Intent bmiListIntent = new Intent(CalculateBMI.this,BMIListActivity.class);
        Bundle b = new Bundle();
        b.putString("email",email);
        bmiListIntent.putExtras(b);
        startActivity(bmiListIntent);
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

        System.out.println("email::"+email);

        InClassDatabaseHelper helper = new InClassDatabaseHelper(CalculateBMI.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ContentValues bmiValues = new ContentValues();
        bmiValues.put("HEIGHT", height.getText().toString());
        bmiValues.put("WEIGHT", weight.getText().toString());
        bmiValues.put("BMI", calc.toString());
        bmiValues.put("DATE", dateFormat.format(new Date()));
        bmiValues.put("EMAIL", email);
        db.insert(InClassDatabaseHelper.TABLE_NAME_BMI,null, bmiValues);

        //db.execSQL("INSERT INTO " + InClassDatabaseHelper.TABLE_NAME_BMI + "(HEIGHT,WEIGHT,BMI,EMAIL) VALUES ('" + height.getText().toString() + "');");
    }
}
