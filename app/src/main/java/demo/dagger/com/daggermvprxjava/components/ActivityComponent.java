package demo.dagger.com.daggermvprxjava.components;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import javax.inject.Singleton;

import dagger.Component;
import demo.dagger.com.daggermvprxjava.modules.DaggerActivityModule;
import demo.dagger.com.daggermvprxjava.modules.DaggerApplicationModule;
import demo.dagger.com.daggermvprxjava.scopes.ActivityScope;
import demo.dagger.com.daggermvprxjava.ui.activity.DribblerHomeActivity;
import demo.dagger.com.daggermvprxjava.ui.fragments.BrowseFragment;

/**
 * Created by abhishek on 03/09/17.
 */

@ActivityScope
@Component(dependencies = DaggerApplicationComponent.class ,modules =  DaggerActivityModule.class)
public interface ActivityComponent {

    Context activityContext();

    FragmentManager fragmentMangager();

    void inject(DribblerHomeActivity dribblerHomeActivity);

    void inject(BrowseFragment browseFragment);

}
