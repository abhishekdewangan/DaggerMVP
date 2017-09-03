package demo.dagger.com.daggermvprxjava;

import android.app.Application;

import demo.dagger.com.daggermvprxjava.components.DaggerApplicationComponent;
import demo.dagger.com.daggermvprxjava.components.DaggerDaggerApplicationComponent;
import demo.dagger.com.daggermvprxjava.modules.DaggerApplicationModule;

/**
 * Created by abhishek on 03/09/17.
 */

public class DaggerApplication extends Application {
    private DaggerApplicationComponent daggerApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        daggerApplicationComponent = DaggerDaggerApplicationComponent.builder()
                .daggerApplicationModule(new DaggerApplicationModule(getApplicationContext())).build();
    }

    public DaggerApplicationComponent getDaggerApplicationComponent() {
        return daggerApplicationComponent;
    }
}
