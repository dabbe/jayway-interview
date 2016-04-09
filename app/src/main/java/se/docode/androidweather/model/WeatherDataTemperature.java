package se.docode.androidweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 2016-04-06.
 */
public class WeatherDataTemperature {
    @SerializedName("temp")
    private double mAverageTemperature;

    @SerializedName("temp_min")
    private double mMinimumTemperature;

    @SerializedName("temp_max")
    private double mMaximumTemperature;

    public double getAverageTemperature() {
        return mAverageTemperature;
    }

    public double getMinimumTemperature() {
        return mMinimumTemperature;
    }

    public double getMaximumTemperature() {
        return mMaximumTemperature;
    }
}
