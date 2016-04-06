package se.docode.androidweather.view;

import se.docode.androidweather.model.SearchResult;

/**
 * Created by Daniel on 2016-04-06.
 */
public interface MvpView {
    void showSearchResult(SearchResult searchResult);
}
