package com.carlostorres.test.cleanarchitecture.mvp.presenter;

import android.app.Activity;

import com.carlostorres.test.cleanarchitecture.mvp.view.PostsView;
import com.carlostorres.test.cleanarchitecture.util.bus.RxBus;
import com.carlostorres.test.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.carlostorres.test.data.PostsServicesImpl;
import com.carlostorres.test.data.response.PostDB;
import com.carlostorres.test.domain.GetLatestPostsUseCase;

import java.util.Map;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class PostsPresenter {

    private PostsView view;
    private GetLatestPostsUseCase getLatestPostsUseCase;


    public PostsPresenter(PostsView view, GetLatestPostsUseCase getLatestPostsUseCase) {
        this.view = view;
        this.getLatestPostsUseCase = getLatestPostsUseCase;
    }

    private void onCallServiceButtonPressed() {

        getLatestPostsUseCase.execute(new DisposableObserver<Map<Integer, Object>>() {
            @Override
            public void onNext(@NonNull Map<Integer, Object> posts) {
                if(PostDB.getAll().isEmpty()){
                    view.showPostsList(posts);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
               view.showError();
            }

            @Override
            public void onComplete() {
                new PostsServicesImpl().getLatestPosts(null);
            }
        },null);
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new CallServiceButtonObserver() {
            @Override
            public void onEvent(CallServiceButtonPressed event) {
                onCallServiceButtonPressed();
            }
        });

    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }
}
