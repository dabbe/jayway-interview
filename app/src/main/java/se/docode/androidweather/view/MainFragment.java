package se.docode.androidweather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import se.docode.androidweather.MyAdapter;
import se.docode.androidweather.R;
import se.docode.androidweather.model.SearchResult;
import se.docode.androidweather.presenter.Presenter;
import se.docode.androidweather.presenter.PresenterImpl;

/**
 * Created by Daniel on 2016-04-06.
 */
public class MainFragment extends Fragment implements MvpView {

    /**
     * Presenter
     */
    private final Presenter mPresenter;

    /**
     * Views
     */
    private EditText mSearchInput;
    private Button mSearchButton;
    private RecyclerView mRecyclerView;

    /**
     * Components
     */
    private MyAdapter mAdapter;


    public MainFragment() {
        mPresenter = new PresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mSearchInput = (EditText) view.findViewById(R.id.search_input);
        mSearchButton = (Button) view.findViewById(R.id.search_button);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.search(mSearchInput.getText().toString());
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showSearchResult(SearchResult searchResult) {
        mAdapter.replaceSearchResult(searchResult);
    }
}
