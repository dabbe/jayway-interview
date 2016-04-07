package se.docode.androidweather.presenter;

import se.docode.androidweather.model.WeatherForecast;
import se.docode.androidweather.network.OnForecastFetchedCallback;
import se.docode.androidweather.view.CityView;

/**
 * Created by Daniel on 2016-04-07.
 */
public class CityPresenterImpl implements CityPresenter {

    private final NetworkHandler mNetworkHandler;
    private final CityView mView;

    public CityPresenterImpl(CityView view) {
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
