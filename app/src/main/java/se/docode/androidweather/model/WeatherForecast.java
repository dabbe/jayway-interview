package se.docode.androidweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Daniel on 2016-04-06.
 */
public class WeatherForecast {
    @SerializedName("list")
    private List<ForecastDay> mForecastDays;

    public List<ForecastDay> getForecastDays() {
        return mForecastDays;
    }
}
