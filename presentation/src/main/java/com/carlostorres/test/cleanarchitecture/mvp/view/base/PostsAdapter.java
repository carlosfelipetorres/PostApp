package com.carlostorres.test.cleanarchitecture.mvp.view.base;


import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carlostorres.test.cleanarchitecture.R;
import com.carlostorres.test.data.response.Post;
import com.carlostorres.test.data.response.PostDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

    private Context mContext;
    private HashMap<Integer, Object> postsMap;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView id;
        TextView title;
        TextView body;
        ImageView unreadDot;
        ImageView favorite;

        MyViewHolder(View view) {
            super(view);
            card = view.findViewById(R.id.cv_post_info);
            id = view.findViewById(R.id.tv_id);
            title = view.findViewById(R.id.tv_title);
            body = view.findViewById(R.id.tv_body);
            unreadDot = view.findViewById(R.id.unread_dot);
            favorite = view.findViewById(R.id.favorite);
        }
    }


    public PostsAdapter(Context mContext, Map<Integer, Object> postsMap) {
        this.mContext = mContext;
        this.postsMap = (HashMap<Integer, Object>) postsMap;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final List<Object> posts = new ArrayList<>(postsMap.values());
        final Post post = (Post) posts.get(position);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(post.getId(), post.getUserId());
                PostDB.updateReadPostById(post.getId());
            }
        });

        holder.id.setText(post.getId().toString());
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());

        holder.favorite.setVisibility(post.isFavorite() ? View.VISIBLE : View.GONE);
        holder.unreadDot.setVisibility(post.isRead() ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return postsMap.size();
    }

    private void showPopupMenu(Integer id, Integer userId) {
        DialogFragment customDialog = CustomDialog.newInstance(id, userId);
        AppCompatActivity activity = (AppCompatActivity) mContext;
        customDialog.show(activity.getSupportFragmentManager(), "PostsFragment");
    }
}
