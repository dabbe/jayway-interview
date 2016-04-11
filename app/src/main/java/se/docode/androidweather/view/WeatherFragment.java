package se.docode.androidweather.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import se.docode.androidweather.R;
import se.docode.androidweather.core.CelsiusConverter;
import se.docode.androidweather.core.FahrenheitConverter;
import se.docode.androidweather.core.TemperatureConverter;

/**
 * Created by Daniel on 2016-04-11.
 */
public abstract class WeatherFragment extends Fragment {

    /**
     * Views
     */
    protected ToggleButton mFahrenheitButton;
    protected ToggleButton mCelsiusButton;
    protected ViewFlipper mViewFlipper;
    protected RecyclerView mRecyclerView;

    /**
     * Components
     */
    protected Toast mToast;

    protected void toast(@StringRes int text) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mViewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mFahrenheitButton = (ToggleButton) view.findViewById(R.id.fahrenheit);
        mCelsiusButton = (ToggleButton) view.findViewById(R.id.celsius);

        mFahrenheitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeConverter(new FahrenheitConverter(getActivity()));
                mFahrenheitButton.setChecked(true);
                mCelsiusButton.setChecked(false);
            }
        });

        mCelsiusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeConverter(new CelsiusConverter(getActivity()));
                mCelsiusButton.setChecked(true);
                mFahrenheitButton.setChecked(false);
            }
        });
    }

    protected abstract void changeConverter(TemperatureConverter converter);
}
