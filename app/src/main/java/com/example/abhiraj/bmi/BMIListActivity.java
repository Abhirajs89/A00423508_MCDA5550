package com.example.abhiraj.bmi;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BMIListActivity extends ListActivity {

    BMIResult[] results = new BMIResult[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        results[0] = new BMIResult(5.5,100, "18-07-2018");
        results[1] = new BMIResult(4.3,156, "18-07-2018");

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
        System.out.println("CLicked on" +results[position].toString());
    }


}