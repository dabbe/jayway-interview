package se.docode.androidweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 2016-04-09.
 */
public class ForecastDay {

    @SerializedName("temp")
    private ForecastTemperature mTemperature;

    @SerializedName("clouds")
    private int mCloudPercentage;

    public ForecastTemperature getTemperature() {
        return mTemperature;
    }

    public int getCloudPercentage() {
        return mCloudPercentage;
    }
}
