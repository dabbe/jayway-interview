package se.docode.androidweather.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import se.docode.androidweather.R;

/**
 * Created by Daniel on 2016-04-06.
 */
public class DetailActivity extends AppCompatActivity {
    private boolean finished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

    }

    @Override
    public void finish() {
        if (!finished) {
            finished = true;
            supportFinishAfterTransition();
        } else {
            super.finish();
        }
    }
}
