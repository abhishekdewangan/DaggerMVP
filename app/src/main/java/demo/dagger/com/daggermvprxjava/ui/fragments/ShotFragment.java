package demo.dagger.com.daggermvprxjava.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.dagger.com.daggermvprxjava.R;
import demo.dagger.com.daggermvprxjava.data.Comment;
import demo.dagger.com.daggermvprxjava.data.Shot;
import demo.dagger.com.daggermvprxjava.presenter.CommentMvpView;
import demo.dagger.com.daggermvprxjava.presenter.ShotPresenter;
import demo.dagger.com.daggermvprxjava.ui.activity.BaseActivity;
import demo.dagger.com.daggermvprxjava.ui.adapters.CommentAdapter;
import demo.dagger.com.daggermvprxjava.utils.Statics;

/**
 * Created by abhishek on 07/09/17.
 */

public class ShotFragment extends Fragment implements CommentMvpView {

    @Bind(R.id.image_shot)
    ImageView imgShot;
    @Bind(R.id.toolbar_shot)
    Toolbar toolbar;
    @Bind(R.id.text_title)
    TextView tvTitle;
    @Bind(R.id.image_like)
    ImageView imgLike;
    @Bind(R.id.text_like_count)
    TextView tvLikeCount;
    @Bind(R.id.text_comments_title)
    TextView tvCommentTitle;
    @Bind(R.id.recycler_comments)
    RecyclerView commentsRecycler;
    @Bind(R.id.progress)
    ProgressBar progressBar;
    @Bind(R.id.text_error_message)
    TextView tvErrorMessage;
    @Inject
    CommentAdapter commentAdapter;
    @Inject
    ShotPresenter shotPresentor;
    private Shot shot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity)getActivity()).getActivityComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shot,container,false);
        ButterKnife.bind(this,view);
        shot = getArguments().getParcelable(Statics.BUNDLE_SHOT_OBJECT);
        if (shot == null){
            throw new IllegalArgumentException("ShotFragment requires a shot instance");
        }
        initView();
        shotPresentor.fetchComments(shot.getId(), ShotPresenter.SHOT_COUNT, ShotPresenter.SHOT_PAGE);
        return view;
    }

    private void initView(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        shotPresentor.attachView(this);

        commentsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        commentsRecycler.setHasFixedSize(true);
        commentsRecycler.setAdapter(commentAdapter);
        setupLayout(shot);

    }

    private void setupLayout(Shot shot) {
        if (shot.getImages() != null) {
            Glide.with(this).load(shot.getImages().getNormal())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imgShot);
            tvTitle.setText(shot.getTitle());
            tvLikeCount.setText(shot.getLikes_count());
        }
    }

    @Override
    public void showError(String errorMsg) {
        commentsRecycler.setVisibility(View.GONE);
        tvCommentTitle.setVisibility(View.GONE);
        tvErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showComments(List<Comment> comments) {
        commentsRecycler.setVisibility(View.VISIBLE);
        commentAdapter.setCommentList(comments);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        tvCommentTitle.setVisibility(View.VISIBLE);
        commentsRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showCommentsTitle(boolean hasComments) {
        tvCommentTitle.setText(getString(hasComments ?
                R.string.text_recent_comments : R.string.text_no_recent_comments));
        tvCommentTitle.setVisibility(View.VISIBLE);
    }
}
