package se.docode.androidweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.docode.androidweather.R;
import se.docode.androidweather.core.CelsiusConverter;
import se.docode.androidweather.core.Constants;
import se.docode.androidweather.core.TemperatureConverter;
import se.docode.androidweather.core.WeatherColorer;
import se.docode.androidweather.model.WeatherData;

/**
 * Created by Daniel on 2016-04-06.
 */
public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private List<WeatherData> mWeatherData;
    private TemperatureConverter mConverter;
    private OnCityClickedListener mOnCityClickedListener;

    public DetailAdapter(Context c, OnCityClickedListener listener) {
        mWeatherData = new ArrayList<>();
        mConverter = new CelsiusConverter(c);
        mOnCityClickedListener = listener;
    }

    public void setConverter(TemperatureConverter converter) {
        mConverter = converter;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.overview_square, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WeatherData weatherdata = mWeatherData.get(position);

        int color = WeatherColorer.getColor(weatherdata.getTemperature().getAverageTemperature());
        holder.rootView.setBackgroundColor(color);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCityClickedListener.onCityClicked(mWeatherData.get(position), holder.temperature, holder.name, weatherdata.getTemperature().getAverageTemperature());
            }
        });

        holder.name.setText(weatherdata.getName());
        holder.weatherImage.setImageResource(weatherdata.getTemperature().getAverageTemperature() > Constants.TEMPERATURE_THRESHOLD ? R.drawable.ic_thumb_up : R.drawable.ic_thumb_down);

        holder.temperature.setText(mConverter.convert(weatherdata.getTemperature().getAverageTemperature()));
    }

    @Override
    public int getItemCount() {
        return mWeatherData.size();
    }

    public void replaceSearchResult(List<WeatherData> weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView temperature;
        ImageView weatherImage;
        View rootView;

        public ViewHolder(View v) {
            super(v);
            rootView = v;
            name = (TextView) v.findViewById(R.id.name);
            temperature = (TextView) v.findViewById(R.id.temperature);
            weatherImage = (ImageView) v.findViewById(R.id.weather_image);
        }
    }

    public interface OnCityClickedListener {
        void onCityClicked(WeatherData weatherData, TextView temperatureText, TextView cityName, double temperature);
    }

}
