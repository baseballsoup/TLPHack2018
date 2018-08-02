package com.sourcey.materiallogindemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginPermissionActivity extends AppCompatActivity {
    private static final String TAG = "LoginPermissionActivity";
    private static final int REQUEST_SIGNUP = 0;
      @BindView(R.id.btn_confirm) Button _okButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view1);
        ButterKnife.bind(this);

        _okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
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
    public void confirm() {
        _okButton.setEnabled(true);
        setResult(RESULT_OK, null);

        Intent intent = new Intent(getApplicationContext(),LoginPermissionActivity2.class);
        startActivity(intent); //transition to LoginActivity.class
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

}