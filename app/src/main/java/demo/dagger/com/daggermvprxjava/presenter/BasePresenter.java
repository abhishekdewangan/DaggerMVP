package demo.dagger.com.daggermvprxjava.presenter;

/**
 * Created by abhishek on 04/09/17.
 */

public class BasePresenter<T extends MVPView> {

    T mvpView;

    public void attachView(T mvpView){
        this.mvpView = mvpView;
    }

    public void detachView(){
        mvpView = null;
    }

    public boolean isViewAttached(){
        return mvpView != null;
    }

    public T getMvpView(){
        return mvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
