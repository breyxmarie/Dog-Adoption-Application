package com.example.dogprofile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.prefs.Preferences;

public class AdopteeProfile extends AppCompatActivity {

    String aDate, aAdopteeName, aDogName, aContactNumber, aEmail, aAddressl;

    @Override
    protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_adoptee_profile);

      TextView date = (TextView) findViewById(R.id.aLDate);
      TextView adopteeName = (TextView) findViewById(R.id.aLAdopteeName);
      TextView dogName = (TextView) findViewById(R.id.aLDogName);
      TextView contactNumber = (TextView) findViewById(R.id.aLContactNumber);
      TextView email = (TextView) findViewById(R.id.aLEmail);
      TextView address = (TextView) findViewById(R.id.aLAddress);
      ImageView imageA = (ImageView) findViewById(R.id.adopteeImage);

      Button btn = (Button) findViewById(R.id.backButton);

      SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

      aDate = sharedPref.getString("key2", "");
      aAdopteeName = sharedPref.getString("key1" , "");
      aDogName = sharedPref.getString("key3", "");
      aContactNumber = sharedPref.getString("key6", "");
      aEmail = sharedPref.getString("key5", "");



    }
}
