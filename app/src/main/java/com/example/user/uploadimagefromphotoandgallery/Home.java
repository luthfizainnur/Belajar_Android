package com.example.user.uploadimagefromphotoandgallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 13/07/2016.
 */
public class Home extends AppCompatActivity{
    private Button buttonMaps, buttonImage, buttonLogin, buttonShow, buttonShowXML, buttonMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        buttonMaps = (Button) findViewById(R.id.buttonMaps);
        buttonImage = (Button) findViewById(R.id.buttonImage);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonShow = (Button) findViewById(R.id.buttonShow);
        buttonShowXML = (Button) findViewById(R.id.buttonShowXML);
        buttonMarker = (Button) findViewById(R.id.buttonMarker);

        buttonMaps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(a);
            }
        });

        buttonImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(b);
                }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(c);
            }
        });

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(getApplicationContext(), ShowData.class);
                startActivity(d);
            }
        });

        buttonShowXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(getApplicationContext(), ShowXML.class);
                startActivity(e);
            }
        });

        buttonMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(getApplicationContext(), Marker.class);
                startActivity(f);
            }
        });
    }

}
