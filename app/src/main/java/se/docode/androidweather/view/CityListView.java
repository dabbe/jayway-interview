package se.docode.androidweather.view;

import java.util.List;

import se.docode.androidweather.model.WeatherData;

/**
 * Created by Daniel on 2016-04-06.
 */
public interface CityListView {
    void showSearchResult(List<WeatherData> weatherData);
}
