package se.docode.androidweather.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import se.docode.androidweather.R;
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

    public CityFragment() {
        mPresenter = new CityPresenterImpl(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mViewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
        mViewFlipper.setDisplayedChild(VIEWFLIPPER_LOADING);

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
        mViewFlipper.setDisplayedChild(VIEWFLIPPER_CITY_OVERVIEW);
    }
}
