package demo.dagger.com.daggermvprxjava.presenter;

import java.util.List;

import javax.inject.Inject;

import demo.dagger.com.daggermvprxjava.data.Shot;
import demo.dagger.com.daggermvprxjava.net.DataManager;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by abhishek on 04/09/17.
 */

public class BrowsePresenter extends BasePresenter<BrowseMVPView> {
    private DataManager dataManager;
    private Subscription subscriptions;
    public static final int SHOT_COUNT = 20;
    public static final int SHOT_PAGE = 0;

    @Inject
    public BrowsePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (subscriptions != null) subscriptions.unsubscribe();
    }



    public void getShots(int perPage, int page) {
        checkViewAttached();
        getMvpView().showMessageLayout(false);
        getMvpView().showProgress();
        subscriptions = dataManager.getShots(perPage, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<Shot>>() {
                    @Override
                    public void onSuccess(List<Shot> shots) {
                        getMvpView().hideProgress();
                        if (!shots.isEmpty()) {
                            getMvpView().showShots(shots);
                        } else {
                            getMvpView().showEmpty();
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Timber.e(error, "There was an error retrieving the shots");
                        getMvpView().hideProgress();
                        getMvpView().showError(error.getMessage());
                    }
                });

    }
}
