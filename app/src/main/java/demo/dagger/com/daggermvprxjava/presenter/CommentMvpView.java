package demo.dagger.com.daggermvprxjava.presenter;

import java.util.List;

import demo.dagger.com.daggermvprxjava.data.Comment;

/**
 * Created by abhishek on 07/09/17.
 */

public interface CommentMvpView extends MVPView {

    public void showError(String errorMsg);

    public void showComments(List<Comment> comments);

    public void showProgress();

    public void hideProgress();

    public void showEmptyView();

    void showCommentsTitle(boolean hasComments);

}
