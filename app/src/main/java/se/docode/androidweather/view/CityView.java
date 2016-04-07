package se.docode.androidweather.view;

import se.docode.androidweather.model.WeatherForecast;

/**
 * Created by Daniel on 2016-04-07.
 */
public interface CityView {
    void onForecastFetched(WeatherForecast weatherForecast);
}
