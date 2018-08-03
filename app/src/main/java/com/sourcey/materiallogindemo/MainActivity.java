package com.sourcey.materiallogindemo;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    final boolean[] isUp = {false};
    View accountView = null;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayout checking_account = null;
        LinearLayout savings_account = null;
        LinearLayout mortgage_account = null;

        checking_account = findViewById(R.id.checking_account);
        savings_account = findViewById(R.id.savings_account);
        mortgage_account = findViewById(R.id.mortgage_account);
        accountView = findViewById(R.id.sliderFull);

        TextView disable = findViewById(R.id.disable_snapshot);

        boolean noBalance = false;
        if(noBalance) {
            checking_account = findViewById(R.id.checking_account_no_balance);
            savings_account = findViewById(R.id.savings_account_no_balance);
            mortgage_account = findViewById(R.id.mortgage_account_no_balance);
            accountView = findViewById(R.id.sliderNoBalance);
            disable = findViewById(R.id.disable_snapshot_no_balance);
        }




        disable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                hideSnap(accountView);
            }
        });

        checking_account.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), CheckingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        savings_account.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SavingsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        mortgage_account.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), MortgageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        accountView.setVisibility(View.INVISIBLE);
        Button hide = findViewById(R.id.snapshot);


        hide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isUp[0]) {
                    slideUp(accountView);
                } else {
                    slideDown(accountView);
                }
                isUp[0] = !isUp[0];
            }
        });


    }

    // slide the view from below itself to the current position
    public void slideUp(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(250);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        Button hide = findViewById(R.id.snapshot);
        ObjectAnimator mover = ObjectAnimator.ofFloat(hide, "translationY", -view.getHeight());
        mover.start();
        //hide.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(300);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        Button hide = findViewById(R.id.snapshot);
        ObjectAnimator mover = ObjectAnimator.ofFloat(hide, "translationY", 0);
        mover.start();
        //hide.startAnimation(animate);
    }

    public void hideSnap(final View view){
        new AlertDialog.Builder(this)
                .setTitle("Disable Snapshot")
                .setMessage("Are you sure you want to unenroll from Snapshot?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(MainActivity.this, "You have been unenrolled from Snapshot.", Toast.LENGTH_SHORT).show();
                        TranslateAnimation animate = new TranslateAnimation(
                                0,                 // fromXDelta
                                0,                 // toXDelta
                                0,                 // fromYDelta
                                view.getHeight() + 50); // toYDelta
                        animate.setDuration(300);
                        animate.setFillAfter(true);
                        view.startAnimation(animate);
                        Button hide = findViewById(R.id.snapshot);
                        ObjectAnimator mover = ObjectAnimator.ofFloat(hide, "translationY", 300);
                        mover.start();
                    }})
                .setNegativeButton(android.R.string.no, null).show();

        //hide.startAnimation(animate);
    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        if (isUp[0]){
            slideDown(accountView);
        };
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
