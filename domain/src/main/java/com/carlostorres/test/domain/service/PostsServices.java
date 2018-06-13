package com.carlostorres.test.domain.service;

import java.util.Map;

import io.reactivex.Observer;

public interface PostsServices {

    void getLatestPosts(Observer<Map<Integer, Object>> observer);

    void getSinglePost(Observer<Object> observer, Integer imageId);
}
