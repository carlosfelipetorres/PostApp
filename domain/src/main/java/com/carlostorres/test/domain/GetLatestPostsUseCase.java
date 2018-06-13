package com.carlostorres.test.domain;

import com.carlostorres.test.domain.service.PostsServices;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

public class GetLatestPostsUseCase extends UseCase<Map<Integer, Object>,Void> {

    private PostsServices postsServices;

    public GetLatestPostsUseCase(PostsServices postsServices) {
        super();
        this.postsServices = postsServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Map<Integer, Object>> observer, Void aVoid) {
        postsServices.getLatestPosts(observer);
    }
}
