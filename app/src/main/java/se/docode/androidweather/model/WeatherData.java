package se.docode.androidweather.model;

import com.google.gson.annotations.SerializedName;

import se.docode.androidweather.network.ResponseParameters;

/**
 * Created by Daniel on 2016-04-06.
 */
public class WeatherData {

    @SerializedName(ResponseParameters.CITY_NAME)
    private String mName;

    @SerializedName(ResponseParameters.TEMPERATURE)
    private Temperature mTemperature;

    @SerializedName(ResponseParameters.CITY_ID)
    private int mId;

    public Temperature getTemperature() {
        return mTemperature;
    }

    public String getName() {
        return mName;
    }

    public int getId() {
        return mId;
    }
}
