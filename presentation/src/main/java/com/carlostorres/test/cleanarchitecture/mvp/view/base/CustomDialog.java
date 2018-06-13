package com.carlostorres.test.cleanarchitecture.mvp.view.base;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlostorres.test.cleanarchitecture.R;
import com.carlostorres.test.data.PostsServicesImpl;
import com.carlostorres.test.data.response.Comment;
import com.carlostorres.test.data.response.Post;
import com.carlostorres.test.data.response.PostDB;
import com.carlostorres.test.data.response.User;
import com.carlostorres.test.domain.GetPostCommentsUseCase;
import com.carlostorres.test.domain.GetSinglePostUseCase;
import com.carlostorres.test.domain.GetUserUseCase;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class CustomDialog extends DialogFragment {

    TextView tv_id ;
    TextView tv_title;
    TextView tv_body;
    RecyclerView rv_comments;

    TextView tv_name ;
    TextView tv_email;
    TextView tv_phone;
    TextView tv_website;

    ImageView favoriteBtn;

    private CommentsAdapter adapter;

    int mId;
    int mUserId;

    static CustomDialog newInstance(int id, int userId) {
        CustomDialog f = new CustomDialog();

        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putInt("userId", id);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getInt("id");
        mUserId = getArguments().getInt("userId");
        GetSinglePostUseCase getSinglePostUseCase = new GetSinglePostUseCase(new PostsServicesImpl());
        GetPostCommentsUseCase getPostCommentsUseCase = new GetPostCommentsUseCase(new PostsServicesImpl());
        GetUserUseCase getUserUseCase = new GetUserUseCase(new PostsServicesImpl());
        getSinglePostUseCase.execute(new DisposableObserver<Object>() {
            @Override
            public void onNext(@NonNull Object post) {
                tv_id.setText(String.valueOf(mId));
                tv_title.setText(((Post) post).getTitle());
                tv_body.setText(((Post) post).getBody());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.toString();
            }

            @Override
            public void onComplete() {
            }
        },mId);

        getPostCommentsUseCase.execute(new DisposableObserver<Object>() {
            @Override
            public void onNext(@NonNull Object comments) {
                adapter = new CommentsAdapter(getContext(), (List<Comment>) comments);

                rv_comments.setLayoutManager(new LinearLayoutManager(getContext()));
                rv_comments.setItemAnimator(new DefaultItemAnimator());
                rv_comments.setAdapter(adapter);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.toString();
            }

            @Override
            public void onComplete() {
            }
        },mId);

        getUserUseCase.execute(new DisposableObserver<Object>() {
            @Override
            public void onNext(@NonNull Object user) {
                tv_name.setText(((User) user).getName());
                tv_email.setText(((User) user).getEmail());
                tv_phone.setText(((User) user).getPhone());
                tv_website.setText(((User) user).getWebsite());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.toString();
            }

            @Override
            public void onComplete() {
            }
        },mUserId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        tv_id = v.findViewById(R.id.tv_id);
        tv_title = v.findViewById(R.id.tv_title);
        tv_body = v.findViewById(R.id.tv_body);
        rv_comments = v.findViewById(R.id.rv_comments);
        tv_name = v.findViewById(R.id.tv_name);
        tv_email = v.findViewById(R.id.tv_email);
        tv_phone = v.findViewById(R.id.tv_phone);
        tv_website = v.findViewById(R.id.tv_website);
        favoriteBtn = v.findViewById(R.id.favorite_btn);

        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostDB.updateFavoritePostById(mId);
                dismiss();
            }
        });

        return v;
    }
}

