package com.sourcey.materiallogindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;

public class MortgageActivity extends AppCompatActivity {
    private static final String TAG = "SavingsActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage);
        ButterKnife.bind(this);
        boolean show = getIntent().getExtras().getBoolean("show");
        if(!show) {
            TextView titlemortgage = findViewById(R.id.mortgage_total);
            titlemortgage.setText("Recent Transactions");
            findViewById(R.id.mortgage_availablenow).setVisibility(View.INVISIBLE);
            findViewById(R.id.mortgage_ondeposit).setVisibility(View.INVISIBLE);
        }


    }


    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        this.finish();
    }

}
