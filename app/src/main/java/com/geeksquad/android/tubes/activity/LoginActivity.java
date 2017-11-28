package com.geeksquad.android.tubes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.entity.Order;

public class LoginActivity extends Activity {

    private final String LOG_TAG = LoginActivity.class.getName();

    public EditText mEdUser, mEdPass;
    public Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);
        mEdUser = findViewById(R.id.edUsername);
        mEdPass = findViewById(R.id.edPassword);
        mLogin = findViewById(R.id.login);
    }

    public void login(View v) {
        String stuser = mEdUser.getText().toString();
        String stpass = mEdPass.getText().toString();

        if (stuser.equals(Order.USERNAME) && stpass.equals(Order.PASSWORD)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.password_mismatch), Toast.LENGTH_SHORT).show();
        }

    }


}
