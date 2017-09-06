package demo.dagger.com.daggermvprxjava.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.dagger.com.daggermvprxjava.R;
import demo.dagger.com.daggermvprxjava.data.Shot;
import demo.dagger.com.daggermvprxjava.qualifiers.ActivityContext;

/**
 * Created by abhishek on 06/09/17.
 */

public class BrowseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Shot> mShots;

    @Inject
    @ActivityContext
    public Context context;

    private ClickListener mClickListener;

    @Inject
    public BrowseAdapter() {
        mShots = Collections.emptyList();
    }

    public void setClickListener(ClickListener clickListener){
        this.mClickListener = clickListener;
    }

    public void setShotList(List<Shot> shotList){
        this.mShots = shotList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shot, parent, false);
        return new ShotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ShotViewHolder) {
            Shot shot = mShots.get(position);
            ShotViewHolder viewholder = (ShotViewHolder) holder;
            viewholder.tvLikeCount.setText(shot.getLikes_count());
            viewholder.tvTitle.setText(shot.getTitle());
            Glide.with(context).load(shot.getImages().getNormal())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(viewholder.imageShot);
        }
    }

    @Override
    public int getItemCount() {
        return mShots != null ? mShots.size() : 0;
    }

    public class ShotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.image_shot)
        ImageView imageShot;
        @Bind(R.id.layout_header)
        View headerLayout;
        @Bind(R.id.text_title)
        TextView tvTitle;
        @Bind(R.id.layout_stats)
        View viewStatus;
        @Bind(R.id.image_like)
        ImageView imgLike;
        @Bind(R.id.text_like_count)
        TextView tvLikeCount;

        public ShotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                mClickListener.onClick(mShots.get(getAdapterPosition()));
            }
        }
    }

    public interface ClickListener {
        public void onClick(Shot shot);
    }
}
