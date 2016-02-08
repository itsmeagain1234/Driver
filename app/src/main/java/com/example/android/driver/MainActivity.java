package com.example.android.driver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mUsername, mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mUsername = (EditText) findViewById(R.id.driverUsername);
        mPassword = (EditText) findViewById(R.id.driverPassword);

//        if(getSharedPreferences("driverDetails", MODE_PRIVATE).getString("username", "default") != "default" && getSharedPreferences("driverDetails", MODE_PRIVATE).getString("password", "default") != "default") {
//            Intent intent = new Intent(this, AfterLogin.class);
//            startActivity(intent);
//            finish();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signIn(View v) {
        String driverUsername = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("login", driverUsername, password);
        try {
            String name=(String)backgroundTask.vector.get(0);
            String id=(String)backgroundTask.vector.get(1);
            Log.d("sent",id+" "+name);
            Log.d("test", "test");
            if(id.equals("Fails"))
            {
                Toast.makeText(this,"Fuck off",Toast.LENGTH_LONG).show();
            }
            else
            {
                SharedPreferences sharedPreferences = getSharedPreferences("driverDetails", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("name", name);
                edit.putString("id", id);
                edit.putString("username", driverUsername);
                edit.putString("password", password);
                edit.commit();
                Intent intent=new Intent(this,AfterLogin.class);
                startActivity(intent);

            }

            //Log.d("DB", BackgroundTask.vector.get(0).toString());

        } catch (NullPointerException e) {


        }
        catch(IndexOutOfBoundsException f){}
    }
}
