package se.docode.androidweather.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.docode.androidweather.core.Constants;
import se.docode.androidweather.model.SearchResult;
import se.docode.androidweather.model.WeatherForecast;
import se.docode.androidweather.network.OnForecastFetchedCallback;
import se.docode.androidweather.network.OnSearchResultFetchedCallback;
import se.docode.androidweather.network.WeatherApi;

/**
 * Created by Daniel on 2016-04-06.
 */
public class NetworkHandler {

    private WeatherApi mApi;

    public NetworkHandler() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(WeatherApi.class);
    }

    public void searchByFreeText(String query, final OnSearchResultFetchedCallback callback) {
        Map<String, String> map = new HashMap<>();

        map.put(Constants.URL_PARAM_QUERY, query);
        map.put(Constants.URL_PARAM_COUNT, "100");
        map.put(Constants.URL_PARAM_APIKEY, Constants.APIKEY);

        Call<SearchResult> call = mApi.searchByFreeText(map);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                callback.onSearchResultFetched(response.body());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "No response");
            }
        });
    }

    public void searchById(int id, final OnForecastFetchedCallback callback) {
        Map<String, String> map = new HashMap<>();

        map.put(Constants.URL_PARAM_ID, String.valueOf(id));
        map.put(Constants.URL_PARAM_COUNT, "3");
        map.put(Constants.URL_PARAM_APIKEY, Constants.APIKEY);
        Call<WeatherForecast> call = mApi.searchById(map);
        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                callback.onForecastFetched(response.body());
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "No response");
            }
        });

    }

}
