package com.carlostorres.test.cleanarchitecture.mvp.view.base;


import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carlostorres.test.cleanarchitecture.R;
import com.carlostorres.test.data.response.Comment;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Comment> commentsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView id;
        TextView title;
        TextView body;
        TextView email;

        MyViewHolder(View view) {
            super(view);
            card = view.findViewById(R.id.cv_post_info);
            id = view.findViewById(R.id.tv_id);
            title = view.findViewById(R.id.tv_title);
            body = view.findViewById(R.id.tv_body);
            email = view.findViewById(R.id.tv_email);
        }
    }


    public CommentsAdapter(Context mContext, List<Comment> comments) {
        this.mContext = mContext;
        this.commentsList = comments;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Comment comment = commentsList.get(position);

        holder.id.setText(comment.getId().toString());
        holder.title.setText(comment.getName());
        holder.body.setText(comment.getBody());
        holder.email.setText(comment.getEmail());
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }
}
