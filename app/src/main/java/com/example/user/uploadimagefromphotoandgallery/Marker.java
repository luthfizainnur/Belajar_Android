package com.example.user.uploadimagefromphotoandgallery;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kosalgeek.android.json.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

public class Marker extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    final String TAG = "Show Marker Project";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;


/*        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-6.1718139, 106.9419476);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        String url = "http://10.0.2.2/android2/getLanLotMarker.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray arr = json.getJSONArray("longlat");
                            Vector<dataObject> tes = new Vector<>();
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                double latitude = Double.parseDouble(obj.getString("latitude"));
                                double longitude = Double.parseDouble(obj.getString("longitude"));
                                tes.add(new dataObject(latitude, longitude));
                            }

                            for(int a = 0; a< tes.size(); a++){
                                Double Latitude = tes.get(a).getLatitude();
                                Log.d(TAG + "a", String.valueOf(Latitude));

                                Double Longitude = tes.get(a).getLongitude();
                                Log.d(TAG + "b", String.valueOf(Longitude));

                                LatLng x = new LatLng(Latitude, Longitude);
                                Log.d(TAG + "xxx", String.valueOf(x));

                                mMap.addMarker(new MarkerOptions().position(x));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(x));

                                /*CameraPosition cameraPosition = new CameraPosition.Builder().zoom(17).build();
                                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

                                /*Longitude = tes.get(a).getLongitude();
                                Latitude = tes.get(a).getLatitude();
                                mMap.addMarker(new MarkerOptions().position(new LatLng(Latitude, Longitude)));*/

                                /*LatLng sydney = new LatLng(-6.1718139, 106.9419476);
                                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error while reading data", Toast.LENGTH_LONG).show();
                    }
                }
        );
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private static class dataObject{
        Double longitude, latitude;
        public dataObject(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }
    }
}
