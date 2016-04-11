package se.docode.androidweather.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import se.docode.androidweather.R;
import se.docode.androidweather.adapter.DetailAdapter;
import se.docode.androidweather.core.TemperatureConverter;
import se.docode.androidweather.model.WeatherData;
import se.docode.androidweather.presenter.OverviewPresenter;
import se.docode.androidweather.presenter.OverviewPresenterImpl;

/**
 * Created by Daniel on 2016-04-06.
 */
public class OverviewFragment extends WeatherFragment implements OverviewView {

    /**
     * Constants
     */
    private static final int VIEWFLIPPER_SEARCH = 0;
    private static final int VIEWFLIPPER_RECYCLER = 1;
    private static final int VIEWFLIPPER_EMPTY = 2;
    private static final int VIEWFLIPPER_LOADING = 3;

    /**
     * Views
     */
    private EditText mSearchInput;
    private Button mSearchButton;
    private TextView mEmptyView;

    /**
     * Components
     */
    private final OverviewPresenter mOverviewPresenter;
    private DetailAdapter mAdapter;

    public OverviewFragment() {
        mOverviewPresenter = new OverviewPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateViews(view);

        mCelsiusButton.setChecked(true);
        mViewFlipper.setDisplayedChild(VIEWFLIPPER_SEARCH);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mAdapter = new DetailAdapter(getActivity(), new DetailAdapter.OnCityClickedListener() {
            @Override
            public void onCityClicked(WeatherData weatherData, TextView temperatureText, TextView cityName, double temperature) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(getString(R.string.city_name), cityName.getText().toString());
                intent.putExtra(getString(R.string.city_id), weatherData.getId());
                intent.putExtra(getString(R.string.temperature), temperature);
                intent.putExtra(getString(R.string.is_celsius), mCelsiusButton.isChecked());

                Pair<View, String> p1 = Pair.create((View) cityName, getString(R.string.textview_transition));
                Pair<View, String> p2 = Pair.create((View) temperatureText, getString(R.string.degrees_transition));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), p1, p2);
                getActivity().startActivity(intent, options.toBundle());

            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mSearchInput.requestFocus();
        mSearchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });
    }

    private void inflateViews(View view){
        mSearchInput = (EditText) view.findViewById(R.id.search_input);
        mSearchButton = (Button) view.findViewById(R.id.search_button);
        mEmptyView = (TextView) view.findViewById(R.id.emptyview);
    }

    @Override
    protected void changeConverter(TemperatureConverter converter) {
        mAdapter.setConverter(converter);
    }

    private void performSearch() {
        mOverviewPresenter.validateQuery(mSearchInput.getText().toString());
    }

    @Override
    public void showSearchResult(List<WeatherData> weatherData) {
        mAdapter.replaceSearchResult(weatherData);
        if (weatherData == null || weatherData.isEmpty()) {
            mEmptyView.setText(getString(R.string.no_results_for, mSearchInput.getText().toString()));
            mViewFlipper.setDisplayedChild(VIEWFLIPPER_EMPTY);
        } else {
            mViewFlipper.setDisplayedChild(VIEWFLIPPER_RECYCLER);
        }
    }

    @Override
    public void showTooShortQuery() {
        toast(R.string.query_too_short);
    }

    @Override
    public void loadSearch() {
        mViewFlipper.setDisplayedChild(VIEWFLIPPER_LOADING);

        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

}
