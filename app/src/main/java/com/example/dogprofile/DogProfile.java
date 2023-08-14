package com.example.dogprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DogProfile extends AppCompatActivity {

    int fId, fAge, fWeight;
    String fName, fBreed, fVaccine, fBType, imageTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_profile);

        TextView id = (TextView) findViewById(R.id.id_list);
        TextView name = (TextView) findViewById(R.id.name_list);
        TextView breed = (TextView) findViewById(R.id.breed_list);
        TextView age = (TextView) findViewById(R.id.age_list);
        TextView weight = (TextView) findViewById(R.id.weight_list);
        TextView vaccine = (TextView) findViewById(R.id.vaccine_list);
        ImageView imgView = (ImageView) findViewById(R.id.imgG);

        Button btn = (Button) findViewById(R.id.returnButton);
        Button adopt = (Button) findViewById(R.id.btnAdopt);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);


        fId = sharedPref.getInt("key1",0);
        fName = sharedPref.getString("key2","");
        fBreed = sharedPref.getString("key3","");
        fAge = sharedPref.getInt("key4",0);
        fWeight = sharedPref.getInt("key5",0);
        fVaccine = sharedPref.getString("key6","");
      //fBType = sharedPref.getString("key7","");
        imageTemp = sharedPref.getString("key7", "");

      byte[] byteArray = Base64.decode(imageTemp, Base64.DEFAULT);
      // System.out.println("byte na  " + Arrays.toString(byteArray));
      Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);;


      if (bitmap != null) {
        try {
          imgView.setImageBitmap(bitmap);
        } catch (Throwable t) {
          Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred while setting bitmap", t);

        }
      } else {
        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Bitmap is null or couldn't be decoded");
      }

        id.setText("Dog ID: "+fId);
        name.setText("Dog Name: "+fName);
        breed.setText("Dog Breed: "+fBreed);
        age.setText("Dog Age: "+fAge);
        weight.setText("Dog Weight: "+fWeight);
        vaccine.setText("Vaccinated: "+fVaccine);

       // imgView.setImageResource(R.drawable.dog);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DogProfile.this,UserActivity.class);

                startActivity(i);
                finish();

            }
        });



        adopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DogProfile.this, adoption.class);


                startActivity(intent);
                finish();
            }
        });
    }
}
