package com.carlostorres.test.data.response;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

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

    @Column(name = "favorite")
    public boolean favorite;

    @Column(name = "read")
    public boolean read;

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

    public static List<PostDB> getFavoritePost() {
        // This is how you execute a query
        return new Select()
            .from(PostDB.class)
            .where("favorite = ?", true)
            .execute();
    }

    public static void updateFavoritePostById(Integer postId) {
        new Update(PostDB.class)
            .set("favorite = 1")
            .where("postId = ?", postId)
            .execute();
    }

    public static void updateReadPostById(Integer postId) {
        new Update(PostDB.class)
            .set("read = 1")
            .where("postId = ?", postId)
            .execute();
    }

    public static void deleteAll() {
        new Delete()
            .from(PostDB.class)
            .where("postId > ?",1)
            .execute();
    }
}
