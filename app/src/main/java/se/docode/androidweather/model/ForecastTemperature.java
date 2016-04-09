package se.docode.androidweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 2016-04-09.
 */
public class ForecastTemperature {

    @SerializedName("max")
    private double mMaximumTemperature;

    @SerializedName("min")
    private double mMinimumTemperature;


    public double getMaximumTemperature() {
        return mMaximumTemperature;
    }

    public double getMinimumTemperature() {
        return mMinimumTemperature;
    }
}
