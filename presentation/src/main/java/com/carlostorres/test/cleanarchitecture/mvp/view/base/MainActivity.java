package com.carlostorres.test.cleanarchitecture.mvp.view.base;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.carlostorres.test.cleanarchitecture.R;
import com.carlostorres.test.cleanarchitecture.mvp.presenter.PostsPresenter;
import com.carlostorres.test.cleanarchitecture.mvp.view.PostsView;
import com.carlostorres.test.data.PostsServicesImpl;
import com.carlostorres.test.data.response.PostDB;
import com.carlostorres.test.domain.GetLatestPostsUseCase;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private PostsPresenter presenter;
    private GetLatestPostsUseCase getLatestPostsUseCase;
    @BindView(R.id.tv_incoming_json) TextView tv;
    @BindView(R.id.rv_posts_list) RecyclerView rvPostsList;
    @BindView(R.id.fab_refresh) FloatingActionButton fabRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLatestPostsUseCase = new GetLatestPostsUseCase(new PostsServicesImpl());
        presenter = new PostsPresenter(new PostsView(this), getLatestPostsUseCase);

        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClass(PostDB.class);
        ActiveAndroid.initialize(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
    }
}