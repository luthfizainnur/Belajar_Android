package com.example.user.uploadimagefromphotoandgallery;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by User on 21/07/2016.
 */
public class Detail implements Serializable {
    @SerializedName("longi")
    public String longi;

    @SerializedName("lati")
    public String lati;

    @SerializedName("longitude")
    public Double longitude;

    @SerializedName("latitude")
    public Double latitude;

}
