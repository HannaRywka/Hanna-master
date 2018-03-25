package com.example.rgome.hanna;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> stations = new ArrayList<>();
    private Button listView;
    private Button mapa;
    private Button about;

    public static ArrayList<Double> stationLat = new ArrayList<>();
    public static ArrayList<Double> stationLon = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        listView = (Button) findViewById(R.id.ltView);
        mapa = (Button) findViewById(R.id.mapa);
        about = (Button) findViewById(R.id.about);

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FetchData process = new FetchData();
                process.execute();

                new CountDownTimer(3000, 3000) {
                    public void onTick(long millisecondsUntilDone){
                        Toast.makeText(MainActivity.this, "Wait a second", Toast.LENGTH_SHORT).show();
                    }

                    @SuppressLint("SetTextI18n")
                    public void onFinish() {
                        startActivity(new Intent(MainActivity.this, ListView.class));
                    }
                }.start();


            }


        });

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FetchData process = new FetchData();
                process.execute();


                new CountDownTimer(3000, 3000) {
                    public void onTick(long millisecondsUntilDone){
                        Toast.makeText(MainActivity.this, "Wait a second", Toast.LENGTH_SHORT).show();
                    }

                    @SuppressLint("SetTextI18n")
                    public void onFinish() {
                        startActivity(new Intent(MainActivity.this, MapsActivity.class));
                    }
                }.start();
            }

        });

        about.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, About.class));
            }
        });
    }

}