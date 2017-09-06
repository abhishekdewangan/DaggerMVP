package demo.dagger.com.daggermvprxjava.presenter;

import java.util.List;

import javax.inject.Inject;

import demo.dagger.com.daggermvprxjava.data.Comment;
import demo.dagger.com.daggermvprxjava.net.DataManager;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by abhishek on 07/09/17.
 */

public class ShotPresenter extends BasePresenter<CommentMvpView> {
    private DataManager dataManager;
    private Subscription subscription;
    List<Comment> mComments;
    public static final int SHOT_COUNT = 10;
    public static final int SHOT_PAGE = 0;

    @Inject
    public ShotPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void fetchComments(int shotId, int perPage, int page) {
        Single<List<Comment>> single;
        getMvpView().showProgress();
        if (mComments == null) {
            single = dataManager.getComments(shotId, perPage, page);
        } else {
            single = Single.just(mComments);
        }
        subscription = single.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<Comment>>() {
                    @Override
                    public void onSuccess(List<Comment> value) {
                        getMvpView().hideProgress();
                        if (!value.isEmpty()) {
                            mComments = value;
                            getMvpView().showComments(mComments);
                        } else {
                            getMvpView().showEmptyView();
                        }
                        getMvpView().showCommentsTitle(!mComments.isEmpty());
                    }

                    @Override
                    public void onError(Throwable error) {
                        getMvpView().hideProgress();
                        getMvpView().showError(error.getMessage());
                    }
                });

    }

    @Override
    public void detachView() {
        super.detachView();
        subscription.unsubscribe();
    }
}
