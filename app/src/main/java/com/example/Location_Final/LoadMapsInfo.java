package com.example.Location_Final;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadMapsInfo {

    private List<HashMap<String,String>> mData;
    double latitude,longitude;

    LoadMapsInfo(){
        latitude = 120.646538;
        longitude = 24.178843;
        mData = new ArrayList<HashMap<String,String>>();
    }

    LoadMapsInfo(double latitude,double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        mData = new ArrayList<HashMap<String,String>>();
    }

    //執行即刻獲取API的結果並回傳固定資訊
    public List<HashMap<String,String>> excute_PlacesTask(){
        StringBuilder sbValue = new StringBuilder(sbMethod());
        PlacesTask placesTask = new PlacesTask();
        placesTask.execute(sbValue.toString());
        if(mData.isEmpty()){
            Log.d("LoadMap","mData Is null");
            mData = new ArrayList<HashMap<String,String>>();
        }
        return mData;
    }

    public StringBuilder sbMethod() {
        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=" + longitude + "," + latitude);
        sb.append("&radius=1000");
        sb.append("&types=" + "restaurant");
        sb.append("&sensor=true");
        sb.append("&key=");
        Log.d("Map", "api: " + sb.toString());

        return sb;
    }

    private class PlacesTask extends AsyncTask<String, Integer, String> {

        String data = null;

        // Invoked by execute() method of this object
        @Override
        protected String doInBackground(String... url) {
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(String result) {
            ParserTask parserTask = new ParserTask();

            // Start parsing the Google places in JSON format
            // Invokes the "doInBackground()" method of the class ParserTask
            parserTask.execute(result);
        }

        public String downloadUrl(String strUrl) throws IOException {
            String data = "";
            InputStream iStream = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(strUrl);

                // Creating an http connection to communicate with url
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url
                urlConnection.connect();

                // Reading data from url
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();

                br.close();

            } catch (Exception e) {
                Log.d("Exception while downloading url", e.toString());
            } finally {
                iStream.close();
                urlConnection.disconnect();
            }
            return data;
        }
    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

        JSONObject jObject;

        // Invoked by execute() method of this object
        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;
            Place_JSON placeJson = new Place_JSON();

            try {
                jObject = new JSONObject(jsonData[0]);

                places = placeJson.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return places;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(List<HashMap<String, String>> list) {

            Log.d("Map", "list size: " + list.size());
            /*
            for (int i = 0; i < list.size(); i++) {
                // Creating a marker
                //MarkerOptions markerOptions = new MarkerOptions();

                // Getting a place from the places list
                HashMap<String, String> hmPlace = list.get(i);

                // Getting latitude of the place
                double lat = Double.parseDouble(hmPlace.get("lat"));

                // Getting longitude of the place
                double lng = Double.parseDouble(hmPlace.get("lng"));

                // Getting name
                String name = hmPlace.get("place_name");

                // Getting vicinity
                //String vicinity = hmPlace.get("vicinity");

                // Getting vicinity
                //String reference = hmPlace.get("reference");

                Log.d("Map", "place: " + name + ",lat: " + lat + ",lng: " + lng);


                //LatLng latLng = new LatLng(lat, lng);

                // Setting the position for the marker
                //markerOptions.position(latLng);

                //markerOptions.title(name + " : " + vicinity);

                //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

                // Placing a marker on the touched position
                //Marker m = mGoogleMap.addMarker(markerOptions);
            }
            */
        }

        private class Place_JSON {

            /**
             * Receives a JSONObject and returns a list
             */
            public List<HashMap<String, String>> parse(JSONObject jObject) {

                JSONArray jPlaces = null;
                try {
                    /** Retrieves all the elements in the 'places' array */
                    jPlaces = jObject.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /** Invoking getPlaces with the array of json object
                 * where each json object represent a place
                 */
                return getPlaces(jPlaces);
            }

            private List<HashMap<String, String>> getPlaces(JSONArray jPlaces) {
                int placesCount = jPlaces.length();
                List<HashMap<String, String>> placesList = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> place = null;

                /** Taking each place, parses and adds to list object */
                for (int i = 0; i < placesCount; i++) {
                    try {
                        /** Call getPlace with place JSON object to parse the place */
                        place = getPlace((JSONObject) jPlaces.get(i));
                        if(place.isEmpty()){
                            Log.d("Hash_getPlace","Is null");
                        }
                        placesList.add(i,place);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(!placesList.isEmpty()){
                    Log.d("Hash_getPlaces","Not null, copy to mData");
                    mData = placesList;
                }
                return placesList;
            }

            /**
             * Parsing the Place JSON object
             */
            private HashMap<String, String> getPlace(JSONObject jPlace) {

                HashMap<String, String> place = new HashMap<String, String>();
                String placeName = "-NA-";
                String latitude = "";
                String longitude = "";

                try {
                    //Log.d("Hash","First");
                    // Extracting Place name, if available
                    if (!jPlace.isNull("name")) {
                        placeName = jPlace.getString("name");
                        //Log.d("Hash","Name:"+placeName);
                    }

                    latitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
                    longitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");

                    place.put("place_name", placeName);
                    place.put("lat", latitude);
                    place.put("long", longitude);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String tt = "temp = new HashMap<String,String>();";
                String name = "temp.put(\"name\",";
                String lat = "temp.put(\"lat\",";
                String lng = "temp.put(\"long\",";
                String add = "mData.add(number_,temp);\n";
                String tail = ");\n";
                Log.d("Hash", "\n" + tt + "\n" +
                        name + "\"" + placeName + "\"" + tail +
                        lat + "\"" + latitude + "\"" + tail +
                        lng + "\"" + longitude + "\"" + tail + add);

                return place;
            }
        }
    }

}
