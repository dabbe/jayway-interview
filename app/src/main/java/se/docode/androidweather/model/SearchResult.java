package se.docode.androidweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Daniel on 2016-04-06.
 */
public class SearchResult {

    @SerializedName("list")
    private List<WeatherData> mWeatherData;

    public List<WeatherData> getWeatherData() {
        return mWeatherData;
    }
}
