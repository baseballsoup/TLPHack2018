package com.sourcey.materiallogindemo;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckingActivity extends AppCompatActivity {
    private static final String TAG = "CheckingActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);
        ButterKnife.bind(this);
        boolean show = getIntent().getExtras().getBoolean("show");
        if (!show) {
            TextView titlecheck = findViewById(R.id.checkingtotal);
            titlecheck.setText("Recent Transactions");
            findViewById(R.id.checking_availablenow).setVisibility(View.INVISIBLE);
            findViewById(R.id.checking_ondeposit).setVisibility(View.INVISIBLE);
        }



    }


    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        this.finish();
    }

}
