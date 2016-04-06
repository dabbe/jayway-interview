package se.docode.androidweather.model;

import com.google.gson.annotations.SerializedName;

import se.docode.androidweather.network.ResponseParameters;

/**
 * Created by Daniel on 2016-04-06.
 */
public class Temperature {
    @SerializedName(ResponseParameters.AVERAGE_TEMPERATURE)
    private double mAverageTemperature;

    @SerializedName(ResponseParameters.MINIMUM_TEMPERATURE)
    private double mMinimumTemperature;

    @SerializedName(ResponseParameters.MAXIMUM_TEMPERATURE)
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
