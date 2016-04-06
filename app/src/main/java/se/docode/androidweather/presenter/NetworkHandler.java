package se.docode.androidweather.presenter;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.docode.androidweather.model.SearchResult;
import se.docode.androidweather.network.OnSearchResultFetchedCallback;
import se.docode.androidweather.network.WeatherApi;

/**
 * Created by Daniel on 2016-04-06.
 */
public class NetworkHandler {

    private WeatherApi mApi;

    public NetworkHandler() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(WeatherApi.class);
    }

    public void search(String query, final OnSearchResultFetchedCallback callback) {
        Call<SearchResult> call = mApi.searchByFreeText(query);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                callback.onSearchResultFetched(response.body());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.d("response", "No response");
            }
        });
    }

}
