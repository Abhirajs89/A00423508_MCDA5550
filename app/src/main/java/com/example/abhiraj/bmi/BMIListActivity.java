package com.example.abhiraj.bmi;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BMIListActivity extends ListActivity {

    //BMIResult[] results = new BMIResult[2];
    List<BMIResult> results = new ArrayList<BMIResult>();
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = bundle.getString("email");
        }
        //results[0] = new BMIResult(5.5,100, "18-07-2018");
        //results[1] = new BMIResult(4.3,156, "18-07-2018");
        InClassDatabaseHelper helper = new InClassDatabaseHelper(BMIListActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor resultSet = db.rawQuery("Select BMI,DATE from "+InClassDatabaseHelper.TABLE_NAME_BMI+" where EMAIL='" + email +"'",null);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat df2 = new DecimalFormat(".##");
        while(resultSet.moveToNext()) {
            BMIResult bmi = new BMIResult();
            double bmiValue = Double.parseDouble(resultSet.getString(0));
            bmi.setBmi(Double.parseDouble(df2.format(bmiValue)));
            bmi.setDate(resultSet.getString(1));
            results.add(bmi);
        }

        ListView listBMIResults = getListView();
        ArrayAdapter<BMIResult> listAdapter = new ArrayAdapter<BMIResult>(
                this,
                android.R.layout.simple_list_item_1,
                results);

        listBMIResults.setAdapter(listAdapter);
    }

    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id){
        //System.out.println("CLicked on" +results[position].toString());
    }


}