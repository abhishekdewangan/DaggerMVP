package demo.dagger.com.daggermvprxjava.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.dagger.com.daggermvprxjava.R;
import demo.dagger.com.daggermvprxjava.data.Shot;
import demo.dagger.com.daggermvprxjava.presenter.BrowseMVPView;
import demo.dagger.com.daggermvprxjava.presenter.BrowsePresenter;
import demo.dagger.com.daggermvprxjava.ui.activity.BaseActivity;
import demo.dagger.com.daggermvprxjava.ui.activity.ShotActivity;
import demo.dagger.com.daggermvprxjava.ui.adapters.BrowseAdapter;
import demo.dagger.com.daggermvprxjava.utils.Statics;

/**
 * Created by abhishek on 03/09/17.
 */

public class BrowseFragment extends Fragment implements BrowseMVPView  {

    @Inject
    BrowsePresenter browsePresenter;
    @Inject
    BrowseAdapter browseAdapter;

    @Bind(R.id.button_message)
    Button mMessageButton;
    @Bind(R.id.image_message)
    ImageView mMessageImage;
    @Bind(R.id.progress)
    ProgressBar mRecyclerProgress;
    @Bind(R.id.recycler_shots)
    RecyclerView mShotRecyclerView;
    @Bind(R.id.swipe_to_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.text_message)
    TextView mMessageText;
    @Bind(R.id.toolbar_browse)
    Toolbar mToolbar;
    @Bind(R.id.layout_message)
    View mMessageLayout;

    private boolean mIsTabletLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivity baseActivity = (BaseActivity) getActivity();
        baseActivity.getActivityComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse, container, false);
        ButterKnife.bind(this, view);
        browsePresenter.attachView(this);
        browsePresenter.getShots(BrowsePresenter.SHOT_COUNT,BrowsePresenter.SHOT_PAGE);
        setupViews();
        return view;
    }

    private void setupViews() {
        browseAdapter.setClickListener(clickListener);

        mShotRecyclerView.setLayoutManager(setLayoutManager());
        mShotRecyclerView.setHasFixedSize(true);
        mShotRecyclerView.setAdapter(browseAdapter);

        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                browsePresenter.getShots(BrowsePresenter.SHOT_COUNT, BrowsePresenter.SHOT_PAGE);
            }
        });
    }


    private RecyclerView.LayoutManager setLayoutManager() {
        RecyclerView.LayoutManager layoutManager;
        if (!mIsTabletLayout) {
            layoutManager = new LinearLayoutManager(getActivity());
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 6);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 3;
                }
            });
            layoutManager = gridLayoutManager;
        }
        return layoutManager;
    }

    @Override
    public void showProgress() {
        mRecyclerProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mRecyclerProgress.setVisibility(View.GONE);
    }

    @Override
    public void showShots(List<Shot> shots) {
        browseAdapter.setShotList(shots);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(),errorMessage,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty() {

    }

    private BrowseAdapter.ClickListener clickListener = new BrowseAdapter.ClickListener() {
        @Override
        public void onClick(Shot shot) {
            Intent intent = new Intent(getActivity(), ShotActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(Statics.BUNDLE_SHOT_OBJECT, shot);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    @Override
    public void showMessageLayout(boolean show) {
        if (show){
            mMessageLayout.setVisibility(View.VISIBLE);
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }else {
            mMessageLayout.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }

    }

}
