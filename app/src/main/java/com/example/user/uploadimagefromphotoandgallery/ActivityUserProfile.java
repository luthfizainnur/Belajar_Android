package com.example.user.uploadimagefromphotoandgallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by User on 21/07/2016.
 */
public class ActivityUserProfile extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        textView = (TextView) findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        textView.setText("Welcome User " + intent.getStringExtra(LoginActivity.KEY_USERNAME));
    }
}
