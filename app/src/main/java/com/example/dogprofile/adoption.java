package com.example.dogprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class adoption extends AppCompatActivity {

    int dogId, fId;
    String fName, fEmail, fAddress, fContNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adoption);

        EditText id = (EditText) findViewById(R.id.dog_id);
        EditText name = (EditText) findViewById(R.id.owner_name);
        EditText email = (EditText) findViewById(R.id.owner_email);
        EditText address = (EditText) findViewById(R.id.owner_address);
        EditText contNum = (EditText) findViewById(R.id.owner_num);
        Button btn = (Button) findViewById(R.id.confirm);
        Button ret = (Button) findViewById(R.id.returnButton);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        dogId = sharedPref.getInt("key1",0);

        id.setText(""+dogId);

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(adoption.this,DogProfile.class);

                startActivity(i);
                finish();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(adoption.this, adopt.class);

                fId = Integer.parseInt(id.getText().toString());
                fName = String.valueOf(name.getText());
                fEmail = String.valueOf(email.getText());
                fAddress = String.valueOf(address.getText());
                fContNum = String.valueOf(contNum.getText());

                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putInt("key1", fId);
                editor.putString("key2", fName);
                editor.putString("key3", fEmail);
                editor.putString("key4", fAddress);
                editor.putString("key5",fContNum);

                editor.commit();

                startActivity(i);
                finish();

            }
        });
    }
}