package se.docode.androidweather.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import java.util.List;

import se.docode.androidweather.R;
import se.docode.androidweather.adapter.ForecastAdapter;
import se.docode.androidweather.core.CelsiusConverter;
import se.docode.androidweather.core.FahrenheitConverter;
import se.docode.androidweather.model.ForecastDay;
import se.docode.androidweather.model.WeatherForecast;
import se.docode.androidweather.presenter.CityPresenter;
import se.docode.androidweather.presenter.CityPresenterImpl;

/**
 * Created by Daniel on 2016-04-06.
 */
public class CityFragment extends Fragment implements CityView {

    private static final int VIEWFLIPPER_LOADING = 0;
    private static final int VIEWFLIPPER_CITY_OVERVIEW = 1;

    private CityPresenter mPresenter;
    private ViewFlipper mViewFlipper;

    private ToggleButton mFahrenheitButton;
    private ToggleButton mCelsiusButton;
    private TextView mTemperature;

    private double mTemperatureValue;

    private ForecastAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public CityFragment() {
        mPresenter = new CityPresenterImpl(this);
    }

    private void reloadDegrees() {
        mTemperature.setText(mAdapter.getConverter().convert(mTemperatureValue));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mViewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);

        mViewFlipper.setDisplayedChild(VIEWFLIPPER_LOADING);

        mCelsiusButton = (ToggleButton) view.findViewById(R.id.celsius);
        mFahrenheitButton = (ToggleButton) view.findViewById(R.id.fahrenheit);

        mTemperature = (TextView) view.findViewById(R.id.temperature);


        boolean isCelsius = getActivity().getIntent().getBooleanExtra(getString(R.string.is_celsius), true);


        mAdapter = new ForecastAdapter(getActivity());
        mAdapter.setConverter(isCelsius ? new CelsiusConverter(getActivity()) : new FahrenheitConverter(getActivity()));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mCelsiusButton.setChecked(isCelsius);
        mFahrenheitButton.setChecked(!isCelsius);

        mTemperatureValue = getActivity().getIntent().getDoubleExtra(getString(R.string.temperature), Double.NaN);
        reloadDegrees();

        mFahrenheitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setConverter(new FahrenheitConverter(getActivity()));
                reloadDegrees();
                mFahrenheitButton.setChecked(true);
                mCelsiusButton.setChecked(false);
            }
        });

        mCelsiusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setConverter(new CelsiusConverter(getActivity()));
                reloadDegrees();
                mCelsiusButton.setChecked(true);
                mFahrenheitButton.setChecked(false);
            }
        });

        Intent intent = getActivity().getIntent();
        String cityName = intent.getStringExtra(getString(R.string.city_name));
        int cityId = intent.getIntExtra(getString(R.string.city_id), -1);

        TextView city = ((TextView) view.findViewById(R.id.city_name));
        city.setText(getString(R.string.in_city, cityName));

        mPresenter.initialize(cityId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

    @Override
    public void onForecastFetched(WeatherForecast weatherForecast) {
        List<ForecastDay> days = weatherForecast.getForecastDays();
        if (days != null)
            mAdapter.setForecastDays(days);
        mViewFlipper.setDisplayedChild(VIEWFLIPPER_CITY_OVERVIEW);
    }
}
