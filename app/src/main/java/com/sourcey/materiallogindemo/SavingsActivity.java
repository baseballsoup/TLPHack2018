package com.sourcey.materiallogindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;

public class SavingsActivity extends AppCompatActivity {
    private static final String TAG = "SavingsActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);
        ButterKnife.bind(this);
        boolean show = getIntent().getExtras().getBoolean("show");
        if(!show) {
            TextView titlesave = findViewById(R.id.savings_total);
            titlesave.setText("Recent Transactions");
            findViewById(R.id.savings_availablenow).setVisibility(View.INVISIBLE);
            findViewById(R.id.savings_ondeposit).setVisibility(View.INVISIBLE);
        }

    }


    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        this.finish();
    }

}
