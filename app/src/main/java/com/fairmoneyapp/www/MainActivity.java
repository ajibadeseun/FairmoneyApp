package com.fairmoneyapp.www;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    UserViewModel userViewModel;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers(100);
        userViewModel.usersStateLiveData.observe(this, response -> {
            switch (response.getStatus()) {
                case LOADING:
                    //Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show();
                    showProgress(getString(R.string.loading));
                    break;
                case ERROR:
                    hideProgress();
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                    break;
                case SUCCESS:
                    hideProgress();
                    UserAdapter userAdapter = new UserAdapter(response.getData().data, this);
                    recyclerView.setAdapter(userAdapter);
                    //Toast.makeText(this,"Success: "+response.getData().data.size(),Toast.LENGTH_LONG).show();
                    break;
            }
        });
    }
}