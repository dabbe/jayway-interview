package se.docode.androidweather.core;

import android.content.Context;

/**
 * Created by Daniel on 2016-04-06.
 */
public abstract class TemperatureConverter {

    protected Context mContext;

    public TemperatureConverter(Context c){
        mContext = c;
    }
    protected abstract double convertValue(double kelvin);
    protected abstract int getStringResource();

    public String convert(double kelvin){
        return mContext.getString(getStringResource(), convertValue(kelvin));
    }
}
