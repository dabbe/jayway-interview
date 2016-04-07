package se.docode.androidweather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.docode.androidweather.R;

/**
 * Created by Daniel on 2016-04-06.
 */
public class CityFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String cityName = getActivity().getIntent().getStringExtra(getString(R.string.city_name));
        TextView city = ((TextView) view.findViewById(R.id.city_name));
        city.setText(getString(R.string.in_city, cityName));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city, container, false);
    }
}
