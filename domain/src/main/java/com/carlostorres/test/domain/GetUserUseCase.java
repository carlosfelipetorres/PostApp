package com.carlostorres.test.domain;


import com.carlostorres.test.domain.service.PostsServices;

import io.reactivex.observers.DisposableObserver;

public class GetUserUseCase extends
    UseCase<Object,Integer> {

    private PostsServices postsServices;

    public GetUserUseCase(PostsServices postsServices) {
        super();
        this.postsServices = postsServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Object> observer, Integer postId) {
        postsServices.getUser(observer, postId);
    }
}
