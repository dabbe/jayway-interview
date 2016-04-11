package se.docode.androidweather.presenter;

import se.docode.androidweather.model.WeatherForecast;
import se.docode.androidweather.network.OnForecastFetchedCallback;
import se.docode.androidweather.view.DetailView;

/**
 * Created by Daniel on 2016-04-07.
 */
public class DetailPresenterImpl implements DetailPresenter {

    private final NetworkHandler mNetworkHandler;
    private final DetailView mView;

    public DetailPresenterImpl(DetailView view) {
        mNetworkHandler = new NetworkHandler();
        mView = view;
    }

    @Override
    public void initialize(int cityId) {
        mNetworkHandler.searchById(cityId, new OnForecastFetchedCallback() {
            @Override
            public void onForecastFetched(WeatherForecast weatherForecast) {
                mView.onForecastFetched(weatherForecast);
            }
        });
    }
}
