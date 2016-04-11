package se.docode.androidweather.presenter;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import se.docode.androidweather.model.SearchResult;
import se.docode.androidweather.network.OnSearchResultFetchedCallback;
import se.docode.androidweather.view.OverviewView;

/**
 * Created by Daniel on 2016-04-06.
 */
public class OverviewPresenterImpl implements OverviewPresenter {
    private final NetworkHandler mNetworkHandler;
    private OverviewView mView;

    public OverviewPresenterImpl(OverviewView view) {
        mView = view;
        mNetworkHandler = new NetworkHandler();
    }

    @Override
    public void search(String query) {
        mNetworkHandler.searchByFreeText(query, new OnSearchResultFetchedCallback() {
            @Override
            public void onSearchResultFetched(SearchResult searchResult) {
                mView.showSearchResult(searchResult.getWeatherData());
            }
        });
    }

    @Override
    public void validateQuery(String query) {
        if(query.length() < 3){
            mView.showTooShortQuery();
        } else{
            mView.loadSearch();
            search(query);
        }
    }
}
