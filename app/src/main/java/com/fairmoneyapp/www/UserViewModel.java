package com.fairmoneyapp.www;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class UserViewModel extends AndroidViewModel {
    public UserRepository userRepository;
    public StateLiveData<Users> usersStateLiveData = new StateLiveData<>();
    public StateLiveData<UserDetails> userDetailsStateLiveData = new StateLiveData<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository();
    }

    public void getAllUsers(int size){
        usersStateLiveData = userRepository.getUsers(size);
    }
    public void getUserDetails(String id){
          userDetailsStateLiveData = userRepository.getUserDetail(id);
    }
}
