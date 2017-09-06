package demo.dagger.com.daggermvprxjava.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import demo.dagger.com.daggermvprxjava.R;
import demo.dagger.com.daggermvprxjava.ui.fragments.ShotFragment;

/**
 * Created by abhishek on 07/09/17.
 */

public class ShotActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();

        ShotFragment shotFragment = new ShotFragment();
        shotFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, shotFragment)
                .commit();

    }
}
