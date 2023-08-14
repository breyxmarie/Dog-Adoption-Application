package com.example.dogprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText user;
    EditText pass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.signIn);

        Button ret = (Button) findViewById(R.id.returnButton);

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,MainActivity.class);

                startActivity(i);
                finish();

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();

                if (username.equals("admin") && password.equals("admin")) {
                    // admin privilege
                    Intent adminIntent = new Intent(Login.this, AdminActivity.class);
                    startActivity(adminIntent);
                } else if (username.equals("user") && password.equals("user")) {
                    // user privilege
                    Intent userIntent = new Intent(Login.this, UserActivity.class);
                    startActivity(userIntent);
                } else {
                    // incorrect login
                    Toast.makeText(Login.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}