package se.docode.androidweather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.docode.androidweather.MyAdapter;
import se.docode.androidweather.R;

/**
 * Created by Daniel on 2016-04-06.
 */
public class MainFragment  extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(",,,", "hellu");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycler_view);

        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        rv.setAdapter(new MyAdapter());

    }
}
