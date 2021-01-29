package com.fairmoneyapp.www;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class BaseActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    protected void showProgress(String message) {
        try {
            this.runOnUiThread(() -> {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(this);
                }
                progressDialog.show(message);
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void hideProgress() {

        try {
            this.runOnUiThread(() -> {
                if (progressDialog != null) {
                    progressDialog.cancel();
                }


            });


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
