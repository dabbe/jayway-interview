package se.docode.androidweather.core;

import android.content.Context;

import se.docode.androidweather.R;

/**
 * Created by Daniel on 2016-04-06.
 */
public class CelsiusConverter extends TemperatureConverter {

    public CelsiusConverter(Context c) {
        super(c);
    }

    @Override
    public double convert(double kelvin) {
        return kelvin - 273.15f;
    }

    @Override
    protected int getStringResource() {
        return R.string.degrees_celsius;
    }
}
