package com.sourcey.materiallogindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class SavingsActivity extends AppCompatActivity {
    private static final String TAG = "SavingsActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);
        ButterKnife.bind(this);


    }


    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        this.finish();
    }

}
