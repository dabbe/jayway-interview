package se.docode.androidweather.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import se.docode.androidweather.model.SearchResult;
import se.docode.androidweather.model.WeatherForecast;

/**
 * Created by Daniel on 2016-04-06.
 */
public interface WeatherApi {

    @GET("find?type=like")
    Call<SearchResult> searchByFreeText(@QueryMap Map<String, String> queryMap);

    @GET("forecast/daily")
    Call<WeatherForecast> searchById(@QueryMap Map<String, String> queryMap);
}
