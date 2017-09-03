package demo.dagger.com.daggermvprxjava.modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.dagger.com.daggermvprxjava.net.DataManager;
import demo.dagger.com.daggermvprxjava.net.DribblerService;
import demo.dagger.com.daggermvprxjava.net.DribblerServiceFactory;
import demo.dagger.com.daggermvprxjava.qualifiers.ApplicationContext;
import demo.dagger.com.daggermvprxjava.scopes.ApplicationScope;

/**
 * Created by abhishek on 03/09/17.
 */

@Module
public class DaggerApplicationModule {
    private Context applicationContext;

    public DaggerApplicationModule(Context applicationContext){
        this.applicationContext = applicationContext;
    }

    @Provides
    @ApplicationScope
    public Context provideApplicationContext(){
        return applicationContext;
    }

    @Provides
    @ApplicationScope
    public DribblerService provideDribblerService(){
        return DribblerServiceFactory.build().makeDribblerService();
    }

    @Provides
    @ApplicationScope
    public DataManager provideDataManager(DribblerService dribblerService){
        return new DataManager(dribblerService);
    }
}
