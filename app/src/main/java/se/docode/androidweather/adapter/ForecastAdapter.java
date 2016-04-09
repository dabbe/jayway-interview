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
import se.docode.androidweather.core.TemperatureConverter;
import se.docode.androidweather.model.ForecastDay;
import se.docode.androidweather.model.ForecastTemperature;

/**
 * Created by Daniel on 2016-04-09.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private static final String[] DEFINED_DAYS = {"Today", "Tomorrow", "The Day After Tomorrow"};
    private final Context mContext;
    private List<ForecastDay> mForecastDays;
    private TemperatureConverter mConverter;


    public ForecastAdapter(Context c) {
        mForecastDays = new ArrayList<>();
        mContext = c;
    }

    public TemperatureConverter getConverter() {
        return mConverter;
    }

    public void setForecastDays(List<ForecastDay> forecastDays) {
        mForecastDays = forecastDays;
        notifyDataSetChanged();
    }

    public void setConverter(TemperatureConverter converter) {
        mConverter = converter;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_data_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ForecastDay day = mForecastDays.get(position);
        ForecastTemperature temp = day.getTemperature();

        holder.title.setText(DEFINED_DAYS[position]);
        holder.maxTemp.setText(mConverter.convert(temp.getMaximumTemperature()));
        holder.minTemp.setText(mConverter.convert(temp.getMinimumTemperature()));
        holder.cloudPercentage.setText(mContext.getString(R.string.percent_value, day.getCloudPercentage()));
        holder.weatherImage.setImageResource(temp.getMaximumTemperature() > 290 ? R.drawable.ic_thumb_up : R.drawable.ic_thumb_down);
    }

    @Override
    public int getItemCount() {
        return mForecastDays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView maxTemp;
        TextView minTemp;
        TextView cloudPercentage;
        ImageView weatherImage;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            maxTemp = (TextView) view.findViewById(R.id.max_temp);
            minTemp = (TextView) view.findViewById(R.id.min_temp);
            cloudPercentage = (TextView) view.findViewById(R.id.cloud_percentage);
            weatherImage = (ImageView) view.findViewById(R.id.weather_image);
        }
    }
}
