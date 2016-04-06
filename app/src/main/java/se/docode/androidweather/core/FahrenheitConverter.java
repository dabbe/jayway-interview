package se.docode.androidweather.core;

import android.content.Context;

import se.docode.androidweather.R;

/**
 * Created by Daniel on 2016-04-06.
 */
public class FahrenheitConverter extends TemperatureConverter {

    public FahrenheitConverter(Context c) {
        super(c);
    }

    @Override
    public double convert(double kelvin) {
        return kelvin * 9 / 5f - 459.67;
    }

    @Override
    protected int getStringResource() {
        return R.string.degrees_fahrenheit;
    }

}
