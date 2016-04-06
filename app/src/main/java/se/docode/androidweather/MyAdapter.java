package se.docode.androidweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.docode.androidweather.core.CelsiusConverter;
import se.docode.androidweather.core.TemperatureConverter;
import se.docode.androidweather.model.WeatherData;

/**
 * Created by Daniel on 2016-04-06.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final Context mContext;
    private List<WeatherData> mWeatherData;
    private TemperatureConverter mConverter;

    public MyAdapter(Context c) {
        mWeatherData = new ArrayList<>();
        mContext = c;
        mConverter = new CelsiusConverter(mContext);
    }

    public void setConverter(TemperatureConverter converter){
        mConverter = converter;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_information, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherData weatherdata = mWeatherData.get(position);

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
        TextView name;
        TextView temperature;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            temperature = (TextView) v.findViewById(R.id.temperature);
        }
    }

}
