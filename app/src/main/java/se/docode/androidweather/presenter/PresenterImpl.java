package se.docode.androidweather.presenter;

import se.docode.androidweather.model.SearchResult;
import se.docode.androidweather.network.OnSearchResultFetchedCallback;
import se.docode.androidweather.view.MvpView;

/**
 * Created by Daniel on 2016-04-06.
 */
public class PresenterImpl implements Presenter {
    private final NetworkHandler mNetworkHandler;
    private MvpView mView;

    public PresenterImpl(MvpView view) {
        mView = view;
        mNetworkHandler = new NetworkHandler();
    }

    @Override
    public void search(String query) {
        mNetworkHandler.search(query, new OnSearchResultFetchedCallback() {
            @Override
            public void onSearchResultFetched(SearchResult searchResult) {
                mView.showSearchResult(searchResult);
            }
        });
    }
}
