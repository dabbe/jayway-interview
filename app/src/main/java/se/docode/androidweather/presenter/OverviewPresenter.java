package se.docode.androidweather.presenter;

/**
 * Created by Daniel on 2016-04-06.
 */
public interface OverviewPresenter {

    void search(String query);

    void validateQuery(String query);
}
