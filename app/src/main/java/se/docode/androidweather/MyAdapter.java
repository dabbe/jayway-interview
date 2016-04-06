package se.docode.androidweather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.docode.androidweather.model.SearchResult;
import se.docode.androidweather.model.WeatherData;

/**
 * Created by Daniel on 2016-04-06.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<WeatherData> mWeatherData;

    public MyAdapter(){
        mWeatherData = new ArrayList<>();
        mWeatherData.add(new WeatherData());
        mWeatherData.add(new WeatherData());
        mWeatherData.add(new WeatherData());
        mWeatherData.add(new WeatherData());
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_data_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(mWeatherData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mWeatherData.size();
    }

    public void replaceSearchResult(SearchResult searchResult) {
        mWeatherData = searchResult.getWeatherData();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView name;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
        }
    }

}
