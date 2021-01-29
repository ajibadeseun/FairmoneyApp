package com.fairmoneyapp.www;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailsActivity extends BaseActivity {
    @BindView(R.id.profile_pics)
    CircleImageView profilePics;
    @BindView(R.id.name_text)
    TextView nameText;
    @BindView(R.id.phone_text)
    TextView phoneText;
    @BindView(R.id.location_text)
    TextView locationText;
    @BindView(R.id.dob_text)
    TextView dobText;
    @BindView(R.id.date_joined_text)
    TextView dateJoinedText;

    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        //Binding views
        ButterKnife.bind(this);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        //Getting Extras
        Bundle extras = getIntent().getExtras();
        String picsUrl = extras.getString("picsUrl");
        String id = extras.getString("id");

        //Loading Profile pics
        Glide.with(this)
                .load(picsUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(profilePics);

        userViewModel.getUserDetails(id);
        userViewModel.userDetailsStateLiveData.observe(this, response -> {
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
                    UserDetails userDetails = response.getData();
                    String name = userDetails.firstName + " " + userDetails.lastName;
                    String location = userDetails.location.street + " "
                            + userDetails.location.city + " " + userDetails.location.state + " "
                            + userDetails.location.country;

                    nameText.setText(name);
                    phoneText.setText(userDetails.phone);
                    locationText.setText(location);
                    dateJoinedText.setText(new SimpleDateFormat("dd-MMM-yyyy").format(userDetails.registerDate));
                    dobText.setText(new SimpleDateFormat("dd-MMM-yyyy").format(userDetails.dateOfBirth));

                    //Toast.makeText(this,"Success: "+response.getData().data.size(),Toast.LENGTH_LONG).show();
                    break;
            }
        });


    }
}