package com.carlostorres.test.data.service.api;

import com.carlostorres.test.data.response.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostsApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("api/v1/images/{id}")
    Call<Post> getPostById(@Path("id") Integer postId);
}
