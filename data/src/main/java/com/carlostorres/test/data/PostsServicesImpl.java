package com.carlostorres.test.data;

import com.activeandroid.util.Log;
import com.carlostorres.test.data.response.Comment;
import com.carlostorres.test.data.response.Post;
import com.carlostorres.test.data.response.PostDB;
import com.carlostorres.test.data.response.User;
import com.carlostorres.test.data.service.api.PostsApi;
import com.carlostorres.test.domain.service.PostsServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsServicesImpl implements PostsServices {

    private static final String URL = "https://jsonplaceholder.typicode.com/";

    @Override
    public void getLatestPosts(final Observer<Map<Integer, Object>> observer) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();

        PostsApi api = retrofit.create(PostsApi.class);

        Call<List<Post>> call = api.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (observer != null) {
                    Map<Integer, Object> posts = new HashMap<>();
                    for (Post post : response.body()) {
                        posts.put(post.getId(), post);
                        savePost(post);
                    }
                    observer.onNext(posts);
                    observer.onComplete();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //todo: update the UI with a connection error message
            }
        });


    }

    @Override
    public void getSinglePost(final Observer<Object> observer, Integer postId) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();

        PostsApi api = retrofit.create(PostsApi.class);

        Call<Post> call = api.getPostById(postId);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (observer != null && response.isSuccessful()) {
                    observer.onNext(response.body());
                    observer.onComplete();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                observer.onError(t);
            }
        });


    }

    @Override
    public void getPostComments(final Observer<Object> observer, Integer postId) {
        Retrofit retrofit = new Retrofit.Builder().
            baseUrl(URL).
            addConverterFactory(GsonConverterFactory.create())
            .build();

        PostsApi api = retrofit.create(PostsApi.class);

        Call<List<Comment>> call = api.getPostComments(postId);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (observer != null && response.isSuccessful()) {
                    observer.onNext(response.body());
                    observer.onComplete();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                observer.onError(t);
            }
        });


    }

    @Override
    public void getUser(final Observer<Object> observer, Integer userId) {
        Retrofit retrofit = new Retrofit.Builder().
            baseUrl(URL).
            addConverterFactory(GsonConverterFactory.create())
            .build();

        PostsApi api = retrofit.create(PostsApi.class);

        Call<User> call = api.getUser(userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (observer != null && response.isSuccessful()) {
                    observer.onNext(response.body());
                    observer.onComplete();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                observer.onError(t);
            }
        });


    }

    private void savePost(Post post) {
        if(!PostDB.getPostById(post.getId()).isEmpty()){
            return;
        }
        PostDB postToSave = new PostDB();
        postToSave.postId = post.getId();
        postToSave.userId = post.getUserId();
        postToSave.title = post.getTitle();
        postToSave.body = post.getBody();
        try {
            postToSave.save();
        } catch (Exception e) {
            Log.e("savePost()", "Failed saving post");
        }

    }
}
