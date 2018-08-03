package com.sourcey.materiallogindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginPermissionActivity3 extends AppCompatActivity {
    private static final String TAG = "LoginPermissionActivity3";

      @BindView(R.id.btn_confirm) Button _okButton;
//    @BindView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_permissions3);
        ButterKnife.bind(this);

        _okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
    }

    public void confirm() {
        _okButton.setEnabled(true);
        setResult(RESULT_OK, null);

        Intent intent = new Intent(getApplicationContext(),LoginPermissionActivity4.class);
        startActivity(intent); //transition to LoginActivity.class
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

}