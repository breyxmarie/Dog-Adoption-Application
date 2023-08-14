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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dogprofile.model.Dog;
import com.example.dogprofile.retrofit.DogApi;
import com.example.dogprofile.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDog extends AppCompatActivity {


    int fId, fAge, fWeight;
    String fName, fBreed, fVaccine, imageTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dog);

        EditText eId = (EditText) findViewById(R.id.edit_id);
        EditText eName = (EditText) findViewById(R.id.edit_name);
        EditText eBreed = (EditText) findViewById(R.id.edit_breed);
        EditText eAge = (EditText) findViewById(R.id.edit_age);
        EditText eWeight = (EditText) findViewById(R.id.edit_weight);
        EditText eVaccine = (EditText) findViewById(R.id.edit_vaccine);
        ImageView imageView = (ImageView) findViewById(R.id.imgG);
        Button save = (Button) findViewById(R.id.saveBtn);
        Button del = (Button) findViewById(R.id.deleteBtn);
        Button btn = (Button) findViewById(R.id.returnButton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditDog.this,AdminActivity.class);

                startActivity(i);
                finish();

            }
        });

        imageView.setImageResource(R.drawable.pawprint);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        fId = sharedPref.getInt("key1",0);
        fName = sharedPref.getString("key2","");
        fBreed = sharedPref.getString("key3","");
        fAge = sharedPref.getInt("key4",0);
        fWeight = sharedPref.getInt("key5",0);
        fVaccine = sharedPref.getString("key6","");
      imageTemp = sharedPref.getString("key7", "");

      byte[] byteArray = Base64.decode(imageTemp, Base64.DEFAULT);
      // System.out.println("byte na  " + Arrays.toString(byteArray));
      Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);;


      if (bitmap != null) {
        try {
          imageView.setImageBitmap(bitmap);
        } catch (Throwable t) {
          Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred while setting bitmap", t);

        }
      } else {
        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Bitmap is null or couldn't be decoded");
      }

        eId.setText(String.valueOf(fId));
        eName.setText(fName);
        eBreed.setText(fBreed);
        eAge.setText(String.valueOf(fWeight));
        eWeight.setText(String.valueOf(fAge));
        eVaccine.setText(fVaccine);

        RetrofitService retrofitService=new RetrofitService();
        DogApi dogApi =retrofitService.getRetrofit().create(DogApi.class);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long id = Integer.parseInt(eId.getText().toString());
                String name = eName.getText().toString();
                String breed = eBreed.getText().toString();
                int age = Integer.parseInt(eWeight.getText().toString());
                int weight = Integer.parseInt(eAge.getText().toString());
                String vaccine = eVaccine.getText().toString();

              byte[] sample = null;
                Dog dog = new Dog(id, name, breed, age, weight, vaccine, null);



                Call<Dog> call = dogApi.updateDog(id, dog);
                call.enqueue(new Callback<Dog>() {
                    @Override
                    public void onResponse(Call<Dog> call, Response<Dog> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(EditDog.this, "Dog data saved successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditDog.this, "Error: Failed to save dog data", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Dog> call, Throwable t) {
                        Toast.makeText(EditDog.this, "Error: Failed to save dog data", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Void> call = dogApi.deleteDog(fId);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(EditDog.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditDog.this, AdminActivity.class);

                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EditDog.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
