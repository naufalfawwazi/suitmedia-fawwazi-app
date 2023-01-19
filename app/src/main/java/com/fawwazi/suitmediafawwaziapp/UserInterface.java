package com.fawwazi.suitmediafawwaziapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserInterface {
    @GET("api/users")
    Call<UserResponse> showUser(
            @Query("page") String page
    );
}
