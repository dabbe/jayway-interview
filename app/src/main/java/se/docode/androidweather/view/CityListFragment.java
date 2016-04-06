package se.docode.androidweather.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import java.util.List;

import se.docode.androidweather.CityAdapter;
import se.docode.androidweather.R;
import se.docode.androidweather.core.CelsiusConverter;
import se.docode.androidweather.core.FahrenheitConverter;
import se.docode.androidweather.model.WeatherData;
import se.docode.androidweather.presenter.CityListPresenter;
import se.docode.androidweather.presenter.CityListPresenterImpl;

/**
 * Created by Daniel on 2016-04-06.
 */
public class CityListFragment extends Fragment implements CityListView {

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
    private RecyclerView mRecyclerView;
    private TextView mEmptyView;

    private ToggleButton mFahrenheitButton;
    private ToggleButton mCelsiusButton;

    /**
     * Components
     */
    private final CityListPresenter mCityListPresenter;
    private CityAdapter mAdapter;
    private ViewFlipper mViewFlipper;

    public CityListFragment() {
        mCityListPresenter = new CityListPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_citylist, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mSearchInput = (EditText) view.findViewById(R.id.search_input);
        mSearchButton = (Button) view.findViewById(R.id.search_button);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mViewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
        mEmptyView = (TextView) view.findViewById(R.id.emptyview);
        mFahrenheitButton = (ToggleButton) view.findViewById(R.id.fahrenheit);
        mCelsiusButton = (ToggleButton) view.findViewById(R.id.celsius);
        mCelsiusButton.setChecked(true);

        mViewFlipper.setDisplayedChild(VIEWFLIPPER_SEARCH);

        mFahrenheitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setConverter(new FahrenheitConverter(getActivity()));
                mFahrenheitButton.setChecked(true);
                mCelsiusButton.setChecked(false);
            }
        });

        mCelsiusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setConverter(new CelsiusConverter(getActivity()));
                mCelsiusButton.setChecked(true);
                mFahrenheitButton.setChecked(false);
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mAdapter = new CityAdapter(getActivity(), new CityAdapter.OnCityClickedListener() {
            @Override
            public void onCityClicked(WeatherData weatherData, ImageView imageView, TextView textView) {
                Intent intent = new Intent(getActivity(), CityActivity.class);
                intent.putExtra(getString(R.string.city_name), textView.getText().toString());

                Pair<View, String> p1 = Pair.create((View) imageView, getString(R.string.imageview_transition));
                Pair<View, String> p2 = Pair.create((View) textView, getString(R.string.textview_transition));
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

    private void performSearch() {
        mViewFlipper.setDisplayedChild(VIEWFLIPPER_LOADING);
        mCityListPresenter.search(mSearchInput.getText().toString());

        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showSearchResult(List<WeatherData> weatherData) {
        mAdapter.replaceSearchResult(weatherData);
        if (weatherData.isEmpty()) {
            mEmptyView.setText(getString(R.string.no_results_for, mSearchInput.getText().toString()));
            mViewFlipper.setDisplayedChild(VIEWFLIPPER_EMPTY);
        } else {
            mViewFlipper.setDisplayedChild(VIEWFLIPPER_RECYCLER);
        }
    }
}
