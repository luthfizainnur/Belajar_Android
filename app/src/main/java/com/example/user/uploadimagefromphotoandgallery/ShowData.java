package com.example.user.uploadimagefromphotoandgallery;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;

/**
 * Created by User on 21/07/2016.
 */

public class ShowData extends AppCompatActivity {
    ListView showData;
    SwipeRefreshLayout swiperefresh;
    final String TAG = "Show Data";

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.show_data);

        showData = (ListView)findViewById(R.id.showData);
        readData();

        swiperefresh = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                readData();
            }
        });

    }

    private void readData(){
        String url = "http://10.0.2.2/android2/getLanLot.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String jsonText) {
                        Log.d(TAG, jsonText);

                        final ArrayList<Detail> productList = new JsonConverter<Detail>().toArrayList(jsonText, Detail.class);
                        Log.d(TAG, String.valueOf(productList.size()));

                        BindDictionary<Detail> dictionary = new BindDictionary<>();
                        Log.d(TAG, dictionary.toString());
                        dictionary.addStringField(R.id.longitude, new StringExtractor<Detail>() {
                            @Override
                            public String getStringValue(Detail detail, int position) {
                                return detail.longitude.toString();
                            }
                        });

                        dictionary.addStringField(R.id.latitude, new StringExtractor<Detail>() {
                            @Override
                            public String getStringValue(Detail detail, int position) {
                                return detail.latitude.toString();
                            }
                        });

                        FunDapter<Detail> adapter = new FunDapter<>(
                                getApplicationContext(),
                                productList,
                                R.layout.data_show,
                                dictionary
                        );
                        showData.setAdapter(adapter);
                        Log.d(TAG, String.valueOf(adapter));

                        if(swiperefresh.isRefreshing()){
                            swiperefresh.setRefreshing(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error while reading data", Toast.LENGTH_LONG).show();

                        if(swiperefresh.isRefreshing()){
                            swiperefresh.setRefreshing(false);
                        }
                    }
                }
        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
