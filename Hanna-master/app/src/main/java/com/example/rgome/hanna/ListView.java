package com.example.rgome.hanna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;


public class ListView extends AppCompatActivity {
    public static android.widget.ListView bikeStations;
   // public ListView bikeStations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        bikeStations = (android.widget.ListView)findViewById(R.id.bikeStations);
       // bikeStations = (ListView) findViewById(R.id.bikeStations);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.stations);

       bikeStations.setAdapter(arrayAdapter);

    }
}
