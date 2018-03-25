package com.example.rgome.hanna;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class FetchData  extends AsyncTask<Void, Void, Void> {
    //Variables
    String data = "";


    @Override
    protected Void doInBackground(Void... voids) {//Connect JSON to fetch data
        try {
            //Get city from MainActivity and encode to URL
           // String encodedCityName = URLEncoder.encode((String) MainActivity.selectCity.getSelectedItem(), "UTF-8");

            ////Connect with JSON
            URL url = new URL("https://api.jcdecaux.com/vls/v1/stations?contract=Dublin&apiKey=a427526be465ab128e86faf3e187e5b08cdee994");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";

            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);

            //Get data from JSON
            for (int i = 0; i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                JSONObject Position = (JSONObject) JO.get("position");


                MainActivity.stations.add("Station Name: " + JO.get("name") + "\nAddress: " + JO.get("address") + "\nAvailable Bike Stands: " + JO.get("available_bike_stands") + "\nAvailable Bikes: " + JO.get("available_bikes"));

                MainActivity.stationLat.add(Position.getDouble("lat"));
                MainActivity.stationLon.add(Position.getDouble("lng"));

               // System.out.println(JO.get("name"));
              //  JSONObject JOPosition = (JSONObject) JO.getJSONObject("position");
              //  MainActivity.locations.add((String) JO.get("name"));
              //  MainActivity.posLatitude.add((Double) JOPosition.get("lat"));
             //   MainActivity.posLongitude.add((Double) JOPosition.get("lng"));
              //  MainActivity.moreInfo.add((String) "\n" + JO.get("name") + "\n\nStatus: " + JO.get("status") + "\nBike Stands: "  + JO.get("bike_stands") + "\nAvailable Bike Stands: "  + JO.get("available_bike_stands") + "\nAvaiable Bikes: "  + JO.get("available_bikes") + "\n");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    //Send fetched data to MainActivity
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

}}