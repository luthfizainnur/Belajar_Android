package com.example.user.uploadimagefromphotoandgallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by User on 22/07/2016.
 */
public class ShowXML extends AppCompatActivity {
    EditText Ed1, Ed2, Ed3, Ed4,Ed5;
    private String url1 = "http://192.168.0.103/android2/test.xml";
    private HandleXML obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parse_xml);

        Ed1 = (EditText)findViewById(R.id.Ed1);
        Ed2 = (EditText)findViewById(R.id.Ed2);
        Ed3 = (EditText)findViewById(R.id.Ed3);
        Ed4 = (EditText)findViewById(R.id.Ed4);
        Ed5 = (EditText) findViewById(R.id.Ed5);

        String url = Ed1.getText().toString();
        String finalUrl = url1 + url;
        Ed2.setText(finalUrl);

        obj = new HandleXML(finalUrl);
        obj.fetchXML();

        while(obj.parsingComplete);
        Ed1.setText(obj.getCode());
        Ed2.setText(obj.getNum());
        Ed3.setText(obj.getDate());
        Ed4.setText(obj.getAmount());
        Ed5.setText(obj.getCurrency());
    }
}
