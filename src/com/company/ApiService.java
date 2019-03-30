package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import javafx.geometry.Pos;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ApiService {
    private static ApiService instance;

    public static synchronized ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public Post getPostById(int id) {
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/posts/" + id, null, null);
        Gson gson=new Gson();
        Post post=gson.fromJson(response,Post.class);
        return post;
    }
    public void deletePostById(int id){
        String response=HttpUtil.sendRequestDelete("https://jsonplaceholder.typicode.com/posts/"+id,null,null);
    }
    public void post(List<Post> posts){
        Gson gson=new Gson();
        String json=gson.toJson(posts);
        String response = HttpUtil.sendRequestPost("https://jsonplaceholder.typicode.com/posts", null, json);
    }
    public void putPost(Post post){
        Gson gson=new Gson();
        String json=gson.toJson(post);
        String response = HttpUtil.sendRequestPut("https://jsonplaceholder.typicode.com/posts",null,json);
    }
    public Comment getCommentById(int id){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/comments/" + id, null, null);
        Gson gson=new Gson();
        Comment comment=gson.fromJson(response,Comment.class) ;
        return comment;
    }
    public Album getAlbumByid(int id){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/albums/" + id, null, null);
        Gson gson=new Gson();
        Album album=gson.fromJson(response,Album.class) ;
        return album;
    }
    public Photo getPhotoByid(int id){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/photos/" + id, null, null);
        Gson gson=new Gson();
        Photo photo=gson.fromJson(response,Photo.class) ;
        return photo;
    }
    public User getUserByid(int id){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/users/" + id, null, null);
        Gson gson=new Gson();
        User user=gson.fromJson(response,User.class) ;
        return user;
    }
    public Todos getTodoByid(int id){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/todos/" + id, null, null);
        Gson gson=new Gson();
        Todos todos=gson.fromJson(response,Todos.class) ;
        return todos;
    }
    public List<Photo> getPhotos(){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/photos" , null, null);
        Type listType = new TypeToken<List<Photo>>() {}.getType();
        Gson gson=new Gson();
        List<Photo> photos=gson.fromJson(response,listType);
        return photos;
    }
    public List<Post> getPosts(){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/posts" , null, null);
        Type listType = new TypeToken<List<Post>>() {}.getType();
        Gson gson=new Gson();
        List<Post> posts=gson.fromJson(response,listType);
        return posts;
    }
    public List<Comment> getComments(){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/comments" , null, null);
        Type listType = new TypeToken<List<Comment>>() {}.getType();
        Gson gson=new Gson();
        List<Comment> comments=gson.fromJson(response,listType);
        return comments;
    }
    public List<Album> getAlbums(){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/albums" , null, null);
        Type listType = new TypeToken<List<Album>>() {}.getType();
        Gson gson=new Gson();
        List<Album> albums=gson.fromJson(response,listType);
        return albums;
    }
    public List<User> getUsers(){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/users" , null, null);
        Type listType = new TypeToken<List<User>>() {}.getType();
        Gson gson=new Gson();
        List<User> users=gson.fromJson(response,listType);
        return users;
    }
    public List<Todos> getTodos(){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/todos" , null, null);
        Type listType = new TypeToken<List<Todos>>() {}.getType();
        Gson gson=new Gson();
        List<Todos> todos=gson.fromJson(response,listType);
        return todos;
    }
    public List<Comment> getCommentsForPost(int postId){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/comments?postId="+postId , null, null);
        Type listType = new TypeToken<List<Comment>>() {}.getType();
        Gson gson=new Gson();
        List<Comment> comments=gson.fromJson(response,listType);
        return comments;
    }
    public List<Post> getPostsOfUser(int userId){
        String response = HttpUtil.sendRequest("https://jsonplaceholder.typicode.com/posts?userId="+userId , null, null);
        Type listType = new TypeToken<List<Post>>() {}.getType();
        Gson gson=new Gson();
        List<Post> posts=gson.fromJson(response,listType);
        return posts;
    }
    public static class HttpUtil {
        public static String sendRequestPut(@NotNull String url, @Nullable Map<String, String> headers, @Nullable String body) {

            String result = null;

            HttpURLConnection urlConnection = null;

            try {

                URL requestUrl = new URL(url);

                urlConnection = (HttpURLConnection) requestUrl.openConnection();

                urlConnection.setReadTimeout(20000);

                urlConnection.setConnectTimeout(20000);

                urlConnection.setRequestMethod("PUT"); // optional, GET already by default


                if (body != null && !body.isEmpty()) {

                    urlConnection.setDoOutput(true);

                    urlConnection.setRequestMethod("POST"); // optional, setDoOutput(true) set value to POST

                    DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                    outputStream.writeBytes(body);

                    outputStream.flush();

                    outputStream.close();

                }


                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {

                        urlConnection.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                int status = urlConnection.getResponseCode();

                System.out.println("Status code:" + status);


                if (status == HttpURLConnection.HTTP_OK) {

                    result = getStringFromStream(urlConnection.getInputStream());

                    Map<String, List<String>> responseHeaders = urlConnection.getHeaderFields();

                    System.out.println("Headers: " + responseHeaders);

                }


            } catch (Exception e) {

                System.out.println("sendRequest failed" + e);

            } finally {

                if (urlConnection != null) {

                    urlConnection.disconnect();

                }

            }

            return result;

        }
        public static String sendRequestDelete(@NotNull String url, @Nullable Map<String, String> headers, @Nullable String body) {

            String result = null;

            HttpURLConnection urlConnection = null;

            try {

                URL requestUrl = new URL(url);

                urlConnection = (HttpURLConnection) requestUrl.openConnection();

                urlConnection.setReadTimeout(20000);

                urlConnection.setConnectTimeout(20000);

                urlConnection.setRequestMethod("DELETE"); // optional, GET already by default


                if (body != null && !body.isEmpty()) {

                    urlConnection.setDoOutput(true);

                    urlConnection.setRequestMethod("POST"); // optional, setDoOutput(true) set value to POST

                    DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                    outputStream.writeBytes(body);

                    outputStream.flush();

                    outputStream.close();

                }


                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {

                        urlConnection.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                int status = urlConnection.getResponseCode();

                 System.out.println("Status code:" + status);


                if (status == HttpURLConnection.HTTP_OK) {

                    result = getStringFromStream(urlConnection.getInputStream());

                    Map<String, List<String>> responseHeaders = urlConnection.getHeaderFields();

                    System.out.println("Headers: " + responseHeaders);

                }
                if (status != HttpURLConnection.HTTP_OK) {
                    throw new Exception();
                }

            } catch (Exception e) {

                System.out.println("sendRequest failed" + e);

            } finally {

                if (urlConnection != null) {

                    urlConnection.disconnect();

                }

            }

            return result;

        }
        public static String sendRequestPost(@NotNull String url, @Nullable Map<String, String> headers, @Nullable String body) {

            String result = null;

            HttpURLConnection urlConnection = null;

            try {

                URL requestUrl = new URL(url);

                urlConnection = (HttpURLConnection) requestUrl.openConnection();

                urlConnection.setReadTimeout(20000);

                urlConnection.setConnectTimeout(20000);

                urlConnection.setRequestMethod("POST"); // optional, GET already by default


                if (body != null && !body.isEmpty()) {

                    urlConnection.setDoOutput(true);

                    urlConnection.setRequestMethod("POST"); // optional, setDoOutput(true) set value to POST

                    DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                    outputStream.writeBytes(body);

                    outputStream.flush();

                    outputStream.close();

                }


                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {

                        urlConnection.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                int status = urlConnection.getResponseCode();

                System.out.println("Status code:" + status);


                if (status == HttpURLConnection.HTTP_OK) {

                    result = getStringFromStream(urlConnection.getInputStream());

                    Map<String, List<String>> responseHeaders = urlConnection.getHeaderFields();

                    System.out.println("Headers: " + responseHeaders);

                }


            } catch (Exception e) {

                System.out.println("sendRequest failed" + e);

            } finally {

                if (urlConnection != null) {

                    urlConnection.disconnect();

                }

            }

            return result;

        }
        public static String sendRequest(@NotNull String url, @Nullable Map<String, String> headers, @Nullable String body) {

            String result = null;

            HttpURLConnection urlConnection = null;

            try {

                URL requestUrl = new URL(url);

                urlConnection = (HttpURLConnection) requestUrl.openConnection();

                urlConnection.setReadTimeout(20000);

                urlConnection.setConnectTimeout(20000);

                urlConnection.setRequestMethod("GET"); // optional, GET already by default


                if (body != null && !body.isEmpty()) {

                    urlConnection.setDoOutput(true);

                    urlConnection.setRequestMethod("POST"); // optional, setDoOutput(true) set value to POST

                    DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                    outputStream.writeBytes(body);

                    outputStream.flush();

                    outputStream.close();

                }


                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {

                        urlConnection.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                int status = urlConnection.getResponseCode();

               // System.out.println("Status code:" + status);


                if (status == HttpURLConnection.HTTP_OK) {

                    result = getStringFromStream(urlConnection.getInputStream());

                    Map<String, List<String>> responseHeaders = urlConnection.getHeaderFields();

                    //System.out.println("Headers: " + responseHeaders);

                }
                if (status != HttpURLConnection.HTTP_OK) {
                    throw new Exception();
                }

            } catch (Exception e) {

                System.out.println("sendRequest failed" + e);

            } finally {

                if (urlConnection != null) {

                    urlConnection.disconnect();

                }

            }

            return result;

        }

        public static String getStringFromStream(InputStream inputStream) throws IOException {

            final int BUFFER_SIZE = 4096;

            ByteArrayOutputStream resultStream = new ByteArrayOutputStream(BUFFER_SIZE);

            byte[] buffer = new byte[BUFFER_SIZE];

            int length;

            while ((length = inputStream.read(buffer)) != -1) {

                resultStream.write(buffer, 0, length);

            }

            return resultStream.toString("UTF-8");

        }
    }
}
