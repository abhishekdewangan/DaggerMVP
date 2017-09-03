package demo.dagger.com.daggermvprxjava.components;

import android.content.Context;

import dagger.Component;
import demo.dagger.com.daggermvprxjava.modules.DaggerApplicationModule;
import demo.dagger.com.daggermvprxjava.net.DataManager;
import demo.dagger.com.daggermvprxjava.net.DribblerService;
import demo.dagger.com.daggermvprxjava.scopes.ApplicationScope;

@ApplicationScope
@Component(modules = {DaggerApplicationModule.class})
public interface DaggerApplicationComponent {
    DribblerService dribblerService();

    DataManager dataManager();

    Context applicationContext();
}
