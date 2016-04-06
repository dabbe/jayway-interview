package se.docode.androidweather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

import se.docode.androidweather.MyAdapter;
import se.docode.androidweather.R;
import se.docode.androidweather.core.CelsiusConverter;
import se.docode.androidweather.core.FahrenheitConverter;
import se.docode.androidweather.model.WeatherData;
import se.docode.androidweather.presenter.Presenter;
import se.docode.androidweather.presenter.PresenterImpl;

/**
 * Created by Daniel on 2016-04-06.
 */
public class MainFragment extends Fragment implements MvpView {

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

    private Button mFahrenheitButton;
    private Button mCelsiusButton;

    /**
     * Components
     */
    private final Presenter mPresenter;
    private MyAdapter mAdapter;
    private ViewFlipper mViewFlipper;

    public MainFragment() {
        mPresenter = new PresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mSearchInput = (EditText) view.findViewById(R.id.search_input);
        mSearchButton = (Button) view.findViewById(R.id.search_button);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mViewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
        mEmptyView = (TextView) view.findViewById(R.id.emptyview);
        mFahrenheitButton = (Button) view.findViewById(R.id.fahrenheit);
        mCelsiusButton = (Button) view.findViewById(R.id.celsius);

        mViewFlipper.setDisplayedChild(VIEWFLIPPER_SEARCH);

        mFahrenheitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setConverter(new FahrenheitConverter(getActivity()));
            }
        });

        mCelsiusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setConverter(new CelsiusConverter(getActivity()));
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setDisplayedChild(VIEWFLIPPER_LOADING);
                mPresenter.search(mSearchInput.getText().toString());
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mAdapter = new MyAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
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
