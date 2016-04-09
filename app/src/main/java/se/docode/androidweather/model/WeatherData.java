package se.docode.androidweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 2016-04-06.
 */
public class WeatherData {

    @SerializedName("name")
    private String mName;

    @SerializedName("main")
    private WeatherDataTemperature mTemperature;

    @SerializedName("id")
    private int mId;

    public WeatherDataTemperature getTemperature() {
        return mTemperature;
    }

    public String getName() {
        return mName;
    }

    public int getId() {
        return mId;
    }
}
