package com.example.android.driver;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AfterLogin extends AppCompatActivity {

    private TextView mWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //alertDialog.setTitle("Shamer is a dea");
            }
        });
        Toast.makeText(this,"good job",Toast.LENGTH_LONG).show();
        mWelcome = (TextView) findViewById(R.id.welcome);
        mWelcome.setText("Welcome, " + getSharedPreferences("driverDetails", MODE_PRIVATE).getString("name", "default"));

    }

}
