package demo.dagger.com.daggermvprxjava.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.dagger.com.daggermvprxjava.R;
import demo.dagger.com.daggermvprxjava.data.Comment;
import demo.dagger.com.daggermvprxjava.qualifiers.ActivityContext;

/**
 * Created by abhishek on 07/09/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Inject
    @ActivityContext
    public Context context;

    public List<Comment> commentList;

    @Inject
    public CommentAdapter(){
        commentList = Collections.emptyList();
    }

    public void setCommentList(List<Comment> commentList){
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  CommentViewHolder){
            CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
            Comment comment = commentList.get(position);
            commentViewHolder.tvUserName.setText(comment.getUser().getUsername());
            commentViewHolder.tvComment.setText(Html.fromHtml(comment.getBody()));
            Glide.with(context)
                    .load(comment.getUser().getAvatarUrl()).into(commentViewHolder.imgUserProfile);
        }

    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.image_avatar)
        ImageView imgUserProfile;
        @Bind(R.id.text_user_name)
        TextView tvUserName;
        @Bind(R.id.text_time)
        TextView tvTextTime;
        @Bind(R.id.text_comment)
        TextView tvComment;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public int getItemCount() {
        return commentList != null ? commentList.size() : 0 ;
    }

}
