package com.fairmoneyapp.www;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @Headers("app-id:"+Configs.APP_ID )
    @GET(Configs.NO_OF_USERS)
    Call<Users> getUsers(@Query("limit") int size);

    @Headers("app-id:"+Configs.APP_ID )
    @GET(Configs.USER_DETAILS)
    Call<UserDetails> getUserDetails(@Path("id") String id);
}
