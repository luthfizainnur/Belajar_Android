package com.example.user.uploadimagefromphotoandgallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 13/07/2016.
 */
public class MapsActivity extends AppCompatActivity {
    /*public static final String UPLOAD_URL = "http://192.168.43.179/android2/uploadLokasi.php";*/
    /*public static final String UPLOAD_URL = "http://192.168.0.100/android2/uploadLokasiBaru.php";*/
    /*public static final String UPLOAD_URL = "http://192.168.43.179/android2/uploadLokasiBaru.php";*/
    public static final String UPLOAD_URL = "http://192.168.0.164:8090/mobile-luthfi-insert-1";

    final String TAG = "Insert Activity";
    Button btnShowLocation;

    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        btnShowLocation = (Button) findViewById(R.id.show_location);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, UPLOAD_URL);
                gps = new GPSTracker(MapsActivity.this);

                if(gps.canGetLocation()) {
                    upload();

                }else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    //gps.showSettingsAlert();
                }
            }
        });
    }

    private void upload(){
        final double latitude = gps.getLatitude();
        final double longitude = gps.getLongitude();

        final MyCommand myCommand = new MyCommand(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                if(response.contains("inserted success")){
                    /*Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();*/
                }
                else{
                    Toast.makeText(getApplicationContext(), "Inserted Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error while uploading, please submit again", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                /*params.put("longitude", String.valueOf(longitude));
                params.put("latitude", String.valueOf(latitude));*/ //untuk ke local postgres

                params.put("long", String.valueOf(longitude));
                params.put("lat", String.valueOf(latitude));
                return params;
            }
        };

        myCommand.add(stringRequest);
        myCommand.execute();
    }
}
