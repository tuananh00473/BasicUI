package com.example.BasicUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity
{
    private Button btLogin;
    private EditText etUsername;
    private EditText etPassword;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        etUsername = (EditText) findViewById(R.id.login_etUsername);
        etPassword = (EditText) findViewById(R.id.login_etPassword);
        btLogin = (Button) findViewById(R.id.login_btLogin);

        btLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                doLogin();
            }
        });
    }

    private void doLogin()
    {
        String username = etUsername.getText().toString();
        if (etUsername.getText().toString().equals("android") && etPassword.getText().toString().equals("1234"))
        {
            Intent intent = new Intent(LoginActivity.this, DetailActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Username or/and Password error.", 3000).show();
        }
    }
}
