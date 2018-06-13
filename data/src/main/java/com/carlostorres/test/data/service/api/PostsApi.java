package com.carlostorres.test.data.service.api;

import com.carlostorres.test.data.response.Comment;
import com.carlostorres.test.data.response.Post;
import com.carlostorres.test.data.response.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostsApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") Integer postId);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getPostComments(@Path("id") Integer postId);

    @GET("users/{id}")
    Call<User> getUser(@Path("id") Integer userId);
}
