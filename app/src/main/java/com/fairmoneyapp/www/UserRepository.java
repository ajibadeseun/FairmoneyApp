package com.fairmoneyapp.www;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    StateLiveData<Users> usersMutableLiveData = new StateLiveData<>();
    StateLiveData<UserDetails> userDetailsStateLiveData = new StateLiveData<>();

    public StateLiveData<Users> getUsers(int size) {
        usersMutableLiveData.postLoading();
        ApiService apiService = RetrofitInstance.getApiService();
        Call<Users> usersCall = apiService.getUsers(size);
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    usersMutableLiveData.postSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                usersMutableLiveData.postError(t);
            }
        });
        return usersMutableLiveData;
    }

    public StateLiveData<UserDetails> getUserDetail(String id) {
        userDetailsStateLiveData.postLoading();
        ApiService apiService = RetrofitInstance.getApiService();
        Call<UserDetails> userDetailsCall = apiService.getUserDetails(id);
        userDetailsCall.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if (response.isSuccessful()) {
                    userDetailsStateLiveData.postSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                userDetailsStateLiveData.postError(t);
            }
        });
        return userDetailsStateLiveData;
    }
}
