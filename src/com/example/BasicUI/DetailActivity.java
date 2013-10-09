package com.example.BasicUI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * User: anhnt
 * Date: 10/9/13
 * Time: 4:14 PM
 */
public class DetailActivity extends Activity
{
    private TextView tvNameUser;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        tvNameUser = (TextView) findViewById(R.id.detail_tvUsername);
        Bundle bundle = getIntent().getExtras();
        tvNameUser.setText(bundle.getString("username"));
    }
}