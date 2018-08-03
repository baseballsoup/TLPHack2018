package com.sourcey.materiallogindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginPermissionActivity4 extends AppCompatActivity {
    private static final String TAG = "LoginPermissionActivity4";

      @BindView(R.id.btn_confirm) Button _okButton;
    @BindView(R.id.btn_decline) Button _declineButton;
//    @BindView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_permissions4);
        ButterKnife.bind(this);

        _okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
        _declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decline();
            }
        });
    }

    public void confirm() {
        _okButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);


        final Switch balanceSwitch = findViewById(R.id.switch3);
        final Switch transactionsSwitch = findViewById(R.id.switch4);
        intent.putExtra("balance",balanceSwitch.isChecked());
        intent.putExtra("transaction",transactionsSwitch.isChecked());
        startActivity(intent); //transition to LoginActivity.class
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void decline() {
        _declineButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

        final Switch balanceSwitch = findViewById(R.id.switch3);
        final Switch transactionsSwitch = findViewById(R.id.switch4);
        intent.putExtra("balance",false);
        intent.putExtra("transaction",false);
        startActivity(intent); //transition to LoginActivity.class
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

}