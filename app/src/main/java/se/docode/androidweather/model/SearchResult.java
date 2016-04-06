package se.docode.androidweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import se.docode.androidweather.network.ResponseParameters;

/**
 * Created by Daniel on 2016-04-06.
 */
public class SearchResult {

    @SerializedName(ResponseParameters.WEATHER_DATA)
    private List<WeatherData> mWeatherData;

    public List<WeatherData> getWeatherData() {
        return mWeatherData;
    }
}
