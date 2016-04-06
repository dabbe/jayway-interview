package se.docode.androidweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.docode.androidweather.core.CelsiusConverter;
import se.docode.androidweather.core.TemperatureConverter;
import se.docode.androidweather.core.WeatherColorer;
import se.docode.androidweather.model.WeatherData;

/**
 * Created by Daniel on 2016-04-06.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private final Context mContext;
    private List<WeatherData> mWeatherData;
    private TemperatureConverter mConverter;
    private OnCityClickedListener mOnCityClickedListener;

    public CityAdapter(Context c, OnCityClickedListener listener) {
        mWeatherData = new ArrayList<>();
        mContext = c;
        mConverter = new CelsiusConverter(mContext);
        mOnCityClickedListener = listener;
    }

    public void setConverter(TemperatureConverter converter) {
        mConverter = converter;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_information, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        WeatherData weatherdata = mWeatherData.get(position);

        int color = WeatherColorer.getColor(weatherdata.getTemperature().getAverageTemperature());
        holder.rootView.setBackgroundColor(color);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCityClickedListener.onCityClicked(mWeatherData.get(position), holder.weatherImage, holder.name);
            }
        });

        holder.name.setText(weatherdata.getName());

        double degrees = mConverter.convert(weatherdata.getTemperature().getAverageTemperature());
        holder.temperature.setText(mConverter.getString(degrees));
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
        ImageView weatherImage;
        TextView name;
        TextView temperature;
        View rootView;

        public ViewHolder(View v) {
            super(v);
            rootView = v;
            name = (TextView) v.findViewById(R.id.name);
            weatherImage = (ImageView) v.findViewById(R.id.weather_image);
            temperature = (TextView) v.findViewById(R.id.temperature);
        }
    }

    public interface OnCityClickedListener {
        void onCityClicked(WeatherData weatherData, ImageView imageView, TextView cityName);
    }

}
