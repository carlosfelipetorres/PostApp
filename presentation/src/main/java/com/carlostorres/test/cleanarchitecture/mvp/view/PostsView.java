package com.carlostorres.test.cleanarchitecture.mvp.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.widget.TextView;

import com.carlostorres.test.cleanarchitecture.R;
import com.carlostorres.test.cleanarchitecture.mvp.view.base.PostsAdapter;
import com.carlostorres.test.cleanarchitecture.util.bus.RxBus;
import com.carlostorres.test.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.carlostorres.test.data.response.Post;
import com.carlostorres.test.data.response.PostDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostsView extends ActivityView implements  LoaderManager.LoaderCallbacks<List<PostDB>> {

    @BindView(R.id.tv_incoming_json)
    TextView tvlabel;
    @BindView(R.id.rv_posts_list)
    RecyclerView rvPostsList;
    @BindView(R.id.fab_refresh)
    FloatingActionButton fabRefresh;
    RecyclerView.LayoutManager mLayoutManager;

    private PostsAdapter adapter;

    public PostsView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        activity.getSupportLoaderManager().initLoader(0, null, this).forceLoad();

        int orientation = activity.getResources().getConfiguration().orientation;
        if(orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            mLayoutManager = new LinearLayoutManager(getContext());
        } else {
            mLayoutManager = new GridLayoutManager(getContext(), 3);
        }
    }

    public void showText(String text) {
        tvlabel.setText(text);
    }

    public void showPostsList(Map<Integer, Object> postsList) {
        adapter = new PostsAdapter(getActivity(), postsList);

        rvPostsList.setLayoutManager(mLayoutManager);
        rvPostsList.setItemAnimator(new DefaultItemAnimator());
        rvPostsList.setAdapter(adapter);
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        getActivity().getSupportLoaderManager().initLoader(0, null, this).forceLoad();
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    @OnClick(R.id.fab_refresh)
    public void callRefreshBtnPressed() {
        adapter = new PostsAdapter(getActivity(), new HashMap<Integer, Object> ());
        rvPostsList.setAdapter(adapter);
    }

    public void showError() {
        tvlabel.setText(R.string.connection_error);
    }

    @Override
    public Loader<List<PostDB>> onCreateLoader(int id, Bundle args) {
        return new FetchData(getContext());
    }

    @Override
    public void onLoadFinished(Loader<List<PostDB>> loader, List<PostDB> data) {
        Map<Integer, Object> postsList = new HashMap<>();
        for(PostDB post : data){
            Post normalPost = new Post();
            normalPost.setId(post.postId);
            normalPost.setUserId(post.userId);
            normalPost.setTitle(post.title);
            normalPost.setBody(post.body);
            normalPost.setFavorite(post.favorite);
            normalPost.setRead(post.read);

            postsList.put(post.postId, normalPost);
        }
        showPostsList(postsList);
    }

    @Override
    public void onLoaderReset(Loader<List<PostDB>> loader) {
    }

    private static class FetchData extends AsyncTaskLoader<List<PostDB>> {

        public FetchData(Context context) {
            super(context);
        }

        @Override
        public List<PostDB> loadInBackground() {
            List<PostDB> imagesList = PostDB.getAll();
            return imagesList;
        }

        @Override
        public void deliverResult(List<PostDB> data) {
            super.deliverResult(data);
        }

    }
}
