package se.docode.androidweather.model;

import com.google.gson.annotations.SerializedName;

import se.docode.androidweather.network.ResponseParameters;

/**
 * Created by Daniel on 2016-04-06.
 */
public class WeatherData {

    @SerializedName(ResponseParameters.CITY_NAME)
    private String mName;

    public String getName() {
        return mName;
    }
}
