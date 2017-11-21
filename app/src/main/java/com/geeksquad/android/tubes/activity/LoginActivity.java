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
    public EditText edUser;
    public EditText edPass;
    public Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);
        edUser = findViewById(R.id.edUsername);
        edPass = findViewById(R.id.edPassword);
        login = findViewById(R.id.login);

    }

    public void login(View v) {
        String stuser = edUser.getText().toString();
        String stpass = edPass.getText().toString();

        if (stuser.equals(Order.USERNAME) && stpass.equals(Order.PASSWORD)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Username atau password tidak sesuai", Toast.LENGTH_SHORT).show();
        }

    }


}
