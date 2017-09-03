package demo.dagger.com.daggermvprxjava.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import demo.dagger.com.daggermvprxjava.DaggerApplication;
import demo.dagger.com.daggermvprxjava.components.ActivityComponent;

import demo.dagger.com.daggermvprxjava.components.DaggerActivityComponent;
import demo.dagger.com.daggermvprxjava.modules.DaggerActivityModule;

/**
 * Created by abhishek on 03/09/17.
 */

public class BaseActivity extends AppCompatActivity {
    private ActivityComponent activityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public ActivityComponent getActivityComponent(){
        if (activityComponent == null){
            DaggerApplication daggerApplication = (DaggerApplication) getApplication();
            activityComponent = DaggerActivityComponent.builder().
                    daggerApplicationComponent(daggerApplication.getDaggerApplicationComponent())
                    .daggerActivityModule(new DaggerActivityModule(this)).build();
        }
        return activityComponent;
    }
}
