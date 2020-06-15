package com.example.alphanetwork.Retrofit;


//import com.example.alphanetwork.Model.CommentFeed;
import com.example.alphanetwork.Model.CommentFeed;
import com.example.alphanetwork.Model.ModelAnonymousWall;
import com.example.alphanetwork.Model.ModelHomeWall;
import com.example.alphanetwork.Model.ModelViewProfile;
import com.example.alphanetwork.Model.ViewProfile;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface Api {




    @FormUrlEncoded
    @POST("users/register")
    Call<ResponseBody> createUser(
        @Field("username") String username,
//        @Field("email") String email,
        @Field("email") String email,
//        @Field("password2") String password2
        @Field("password1") String password1,
        @Field("password2") String password2
    );

    @FormUrlEncoded
    @POST("users/login")
    Call<ResponseBody> userLogin(
//            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("users/login/google")
    Call<ResponseBody> googleLogin(

            @Field("email") String email,
            @Field("id") String id
    );


    @Multipart
    @POST("articles/addpost")
    Call<ResponseBody> addPost(
            @Part("title") RequestBody title,
            @Part("longitude") RequestBody longitude,
            @Part("latitude") RequestBody latitude,
//            @Part("description") RequestBody description,
            @Part List<MultipartBody.Part> image

    );

    @Multipart
    @POST("articles/addanonymouspost")
    Call<ResponseBody> addAnonymousPost(
            @Part("title") RequestBody title,
            @Part("longitude") RequestBody longitude,
            @Part("latitude") RequestBody latitude,
//            @Part("description") RequestBody description,
            @Part List<MultipartBody.Part> image
    );



    @Multipart
    @PATCH("user")
    Call<ResponseBody> updateProfile(
        @Part List<MultipartBody.Part> photo,
        @Part ("username") RequestBody username
    );


//    @PATCH("feed/profile/")
//    Call<ResponseBody> updateProfileWithoutPic(
//            @Field ("username") RequestBody username,
//            @Field ("phone") RequestBody phone,
//            @Field ("email") RequestBody email
//    );

    @GET("profiles/")
    Call<ModelViewProfile> getProfile();

    @GET("profiles/view")
    Call<ModelViewProfile> getViewProfile(
            @Query("id") String id
    );


//    @GET("feed/profile2/")
//    Call<ModelViewProfile> getProfile();


    @GET("articles/feed")
    Call<ModelHomeWall> feed(
            @Query("longitude") String longitude,
            @Query("latitude") String latitude
    );

    @GET("articles/anonymousfeed")
    Call<ModelAnonymousWall> anonymousfeed(
            @Query("longitude") String longitude,
            @Query("latitude") String latitude
    );

    @GET("articles/feedgrid")
    Call<ModelHomeWall> feedgrid(
            @Query("id") String id
    );

    @GET("articles/myfeedgrid")
    Call<ModelHomeWall> myfeedgrid();




    @GET("feed/comments/1/")
    Call<CommentFeed> comments();


}