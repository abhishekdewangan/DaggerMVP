package demo.dagger.com.daggermvprxjava.modules;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import demo.dagger.com.daggermvprxjava.qualifiers.ActivityContext;
import demo.dagger.com.daggermvprxjava.scopes.ActivityScope;

/**
 * Created by abhishek on 03/09/17.
 */

@Module
public class DaggerActivityModule {
    private AppCompatActivity activity;

    public DaggerActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideActivityContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    public FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }
}
