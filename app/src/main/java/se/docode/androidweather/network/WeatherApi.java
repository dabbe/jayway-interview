package se.docode.androidweather.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import se.docode.androidweather.model.SearchResult;
import se.docode.androidweather.model.WeatherForecast;

/**
 * Created by Daniel on 2016-04-06.
 */
public interface WeatherApi {

    @GET("find?type=like&cnt=100&APPID=89794ef3be7110927c49db761c39bf1c")
    Call<SearchResult> searchByFreeText(@Query("q") String query);

    @GET("forecast/daily?cnt=3&APPID=89794ef3be7110927c49db761c39bf1c")
    Call<WeatherForecast> searchById(@Query("id") int id);
}
