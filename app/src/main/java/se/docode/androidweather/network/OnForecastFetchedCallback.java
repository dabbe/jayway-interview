package se.docode.androidweather.network;

import se.docode.androidweather.model.WeatherForecast;

/**
 * Created by Daniel on 2016-04-07.
 */
public interface OnForecastFetchedCallback {
    void onForecastFetched(WeatherForecast weatherForecast);
}
