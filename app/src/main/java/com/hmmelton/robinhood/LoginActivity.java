package com.hmmelton.robinhood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hmmelton.robinhood.utils.RobinhoodApi;


public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);
        }

        final EditText username = (EditText) findViewById(R.id.login_username);
        final EditText password = (EditText) findViewById(R.id.login_password);
        Button loginButton = (Button) findViewById(R.id.login_button);

        username.requestFocus();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAuthenticatedUser(username, password);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    /**
     * This method authenticates the user, ensuring the correct username and password were entered.
     * @param usernameField
     * @param passwordField
     */
    private void isAuthenticatedUser(EditText usernameField, EditText passwordField) {
        final String username = usernameField
                .getText()
                .toString()
                .trim();
        final String password = passwordField
                .getText()
                .toString()
                .trim();

        RobinhoodApplication.initApi(username, password);
        RobinhoodApplication.getApi().login(new RobinhoodApi.RobinhoodApiCallback<String>() {
            @Override
            public void onSuccess(String result) {
                RobinhoodApplication.getUser().setAuthToken(result);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(LoginActivity.this, R.string.authentication_failure,
                        Toast.LENGTH_SHORT).show();
                Log.e(TAG, getResources().getString(R.string.authentication_failure), throwable);
            }
        });
    }
}
