package demo.dagger.com.daggermvprxjava.presenter;

import java.util.List;

import demo.dagger.com.daggermvprxjava.data.Shot;

/**
 * Created by abhishek on 04/09/17.
 */

public interface BrowseMVPView extends MVPView {

    void showProgress();

    void hideProgress();

    void showShots(List<Shot> shots);

    void showError();

    void showEmpty();

    void showMessageLayout(boolean show);
}
