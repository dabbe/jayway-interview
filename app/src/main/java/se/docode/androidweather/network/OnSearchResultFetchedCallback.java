package se.docode.androidweather.network;

import se.docode.androidweather.model.SearchResult;

/**
 * Created by Daniel on 2016-04-06.
 */
public interface OnSearchResultFetchedCallback {
    void onSearchResultFetched(SearchResult searchResult);
}
