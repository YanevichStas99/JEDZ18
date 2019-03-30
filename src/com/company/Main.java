package com.company;

import com.google.gson.reflect.TypeToken;
import javafx.geometry.Pos;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApiService service=ApiService.getInstance();
        Post post=service.getPostById(5);
        System.out.println(post);
        Comment comment=service.getCommentById(4);
        System.out.println(comment);
        User user=service.getUserByid(1);
        Album album=service.getAlbumByid(5);
        Photo photo=service.getPhotoByid(6);
        Todos todos=service.getTodoByid(100);
        List<Photo> photos=service.getPhotos();
        List<Post> posts=service.getPosts();
        List<User> users=service.getUsers();
        List<Comment> comments=service.getComments();
        List<Album> albums=service.getAlbums();
        List<Todos> todoses=service.getTodos();
        List<Comment> commentsOfPost=service.getCommentsForPost(6);
        List<Post> posts1=service.getPostsOfUser(1);
        service.deletePostById(1);
        Post post1=new Post(8,3,"rgsrggs","gewgwegsdfgs");
        Post post2=new Post(2,5,"rgsrggsdvs","gewgsdwegsdfgs");
        List<Post> posts2=new ArrayList<>();
        posts2.add(post1);
        posts2.add(post2);
        service.post(posts2);
        service.putPost(post1);
    }
}
