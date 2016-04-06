package se.docode.androidweather.presenter;

import se.docode.androidweather.model.SearchResult;
import se.docode.androidweather.model.WeatherData;
import se.docode.androidweather.network.OnSearchResultFetchedCallback;
import se.docode.androidweather.view.CityListView;

/**
 * Created by Daniel on 2016-04-06.
 */
public class CityListPresenterImpl implements CityListPresenter {
    private final NetworkHandler mNetworkHandler;
    private CityListView mView;

    public CityListPresenterImpl(CityListView view) {
        mView = view;
        mNetworkHandler = new NetworkHandler();
    }

    @Override
    public void search(String query) {
        mNetworkHandler.search(query, new OnSearchResultFetchedCallback() {
            @Override
            public void onSearchResultFetched(SearchResult searchResult) {
                mView.showSearchResult(searchResult.getWeatherData());
            }
        });
    }

}
