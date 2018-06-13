package com.carlostorres.test.data.response;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "PostDB")
public class PostDB extends Model{
    @Column(name = "postId")
    public Integer postId;

    @Column(name = "userId")
    public Integer userId;

    @Column(name = "title")
    public String title;

    @Column(name = "body")
    public String body;

    public static List<PostDB> getAll() {
        // This is how you execute a query
        return new Select()
                .from(PostDB.class)
                .execute();
    }

    public static List<PostDB> getPostById(Integer postId) {
        // This is how you execute a query
        return new Select()
                .from(PostDB.class)
                .where("postId = ?", postId)
                .execute();
    }
}
