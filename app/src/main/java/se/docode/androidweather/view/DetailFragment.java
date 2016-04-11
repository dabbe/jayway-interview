package se.docode.androidweather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import se.docode.androidweather.R;
import se.docode.androidweather.adapter.ForecastAdapter;
import se.docode.androidweather.core.CelsiusConverter;
import se.docode.androidweather.core.FahrenheitConverter;
import se.docode.androidweather.core.TemperatureConverter;
import se.docode.androidweather.model.ForecastDay;
import se.docode.androidweather.model.WeatherForecast;
import se.docode.androidweather.presenter.DetailPresenter;
import se.docode.androidweather.presenter.DetailPresenterImpl;

/**
 * Created by Daniel on 2016-04-06.
 */
public class DetailFragment extends WeatherFragment implements DetailView {

    /**
     * Constants
     */
    private static final int VIEWFLIPPER_LOADING = 0;
    private static final int VIEWFLIPPER_CITY_OVERVIEW = 1;

    /**
     * Views
     */
    private TextView mTemperature;

    /**
     * State
     */
    private double mTemperatureValue;

    /**
     * Components
     */
    private ForecastAdapter mAdapter;
    private DetailPresenter mPresenter;


    public DetailFragment() {
        mPresenter = new DetailPresenterImpl(this);
    }

    private void reloadDegrees() {
        mTemperature.setText(mAdapter.getConverter().convert(mTemperatureValue));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle extras = getActivity().getIntent().getExtras();

        String cityName = extras.getString(getString(R.string.city_name));
        int cityId = extras.getInt(getString(R.string.city_id), -1);
        boolean isCelsius = extras.getBoolean(getString(R.string.is_celsius), true);

        TextView city = ((TextView) view.findViewById(R.id.city_name));
        city.setText(getString(R.string.in_city, cityName));

        mTemperature = (TextView) view.findViewById(R.id.temperature);

        mViewFlipper.setDisplayedChild(VIEWFLIPPER_LOADING);

        mAdapter = new ForecastAdapter(getActivity());

        mCelsiusButton.setChecked(isCelsius);
        mFahrenheitButton.setChecked(!isCelsius);
        mAdapter.setConverter(isCelsius ? new CelsiusConverter(getActivity()) : new FahrenheitConverter(getActivity()));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mTemperatureValue = extras.getDouble(getString(R.string.temperature), Double.NaN);
        reloadDegrees();

        mPresenter.initialize(cityId);
    }

    @Override
    protected void changeConverter(TemperatureConverter converter) {
        mAdapter.setConverter(converter);
        reloadDegrees();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onForecastFetched(WeatherForecast weatherForecast) {
        List<ForecastDay> days = weatherForecast.getForecastDays();
        if (days != null)
            mAdapter.setForecastDays(days);
        mViewFlipper.setDisplayedChild(VIEWFLIPPER_CITY_OVERVIEW);
    }
}
