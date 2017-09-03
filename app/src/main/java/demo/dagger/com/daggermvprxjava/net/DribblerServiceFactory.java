package demo.dagger.com.daggermvprxjava.net;

import com.google.gson.Gson;

import demo.dagger.com.daggermvprxjava.BuildConfig;
import demo.dagger.com.daggermvprxjava.utils.Statics;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DribblerServiceFactory {

    private static DribblerServiceFactory dribblerServiceFactory;

    private DribblerServiceFactory() {
    }

    public static DribblerServiceFactory build() {
        if (dribblerServiceFactory == null)
            dribblerServiceFactory = new DribblerServiceFactory();
        return dribblerServiceFactory;
    }

    public DribblerService makeDribblerService() {
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor());
        return makeDribblerService(okHttpClient);
    }

    private DribblerService makeDribblerService(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Statics.DRIBBBLE_API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        return retrofit.create(DribblerService.class);
    }

    private OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    private HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }
}
