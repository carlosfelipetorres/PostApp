package com.carlostorres.test.domain.service;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;

public interface PostsServices {

    void getLatestPosts(Observer<Map<Integer, Object>> observer);

    void getSinglePost(Observer<Object> observer, Integer postId);

    void getPostComments(Observer<Object> observer, Integer postId);

    void getUser(Observer<Object> observer, Integer userId);
}
