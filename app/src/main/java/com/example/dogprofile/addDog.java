package com.example.dogprofile;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dogprofile.model.Dog;
import com.example.dogprofile.retrofit.DogApi;
import com.example.dogprofile.retrofit.RetrofitService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addDog extends AppCompatActivity {


  Bitmap bitmap;
  private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
  private String userChoosenTask;
  ActivityResultLauncher<Intent> resultLauncher;
  int SELECT_PICTURE = 200;
  int CAPTURE_PICTURE = 100;
  int CAMERA_REQ_CODE = 100;
  ImageView IVPreviewImage;
  byte[] image;




  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_dog);


    Button btnGallery = findViewById(R.id.btnGallery);
    ImageView imageView = (ImageView) findViewById(R.id.imgG);
    imageView.setImageResource(R.drawable.bingo);

    Button btn = (Button) findViewById(R.id.returnButton);
    IVPreviewImage = (ImageView) findViewById(R.id.imgG);
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(addDog.this, AdminActivity.class);

        startActivity(i);
        finish();

      }
    });


    btnGallery.setOnClickListener(new View.OnClickListener() {
      @RequiresApi(api = Build.VERSION_CODES.M)
      public void onClick(View v){
        boolean pick = true;
        if(pick ==true){
          if(!checkCameraPermission()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
              requestCameraPermission();
            }
          }else pickImage();

        } else {
          if(!checkStoragePermission()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
              requestStoragePermission();
            }
          }else pickImage();
        }
        selectImage();
      }
    });


    initializeComponents();
  }

  @TargetApi(Build.VERSION_CODES.M)
  private void requestStoragePermission(){
    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
  }


  //@TargetApi(Build.VERSION_CODES.M)
  @RequiresApi(api = Build.VERSION_CODES.M)
  private void requestCameraPermission(){
    requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 100);

  }

  private boolean checkCameraPermission() {
    boolean res1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    boolean res2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    return res1 && res2;
  }

  private boolean checkStoragePermission() {
    boolean res2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    return res2;
  }

  private void selectImage() {
    final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
    AlertDialog.Builder builder = new AlertDialog.Builder(addDog.this);
    builder.setTitle("Add Photo!");
    builder.setItems(options, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int item) {
        if (options[item].equals("Take Photo"))
        {
          cameraImage();
        }
        else if (options[item].equals("Choose from Gallery"))
        {
          pickImage();
        }
        else if (options[item].equals("Cancel")) {
          dialog.dismiss();
        }
      }
    });
    builder.show();
  }


  private void pickImage(){


    Intent i = new Intent();
    i.setType("image/*");
    i.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(i,"SELECT PICTURE"), SELECT_PICTURE);

  }

  private void cameraImage(){
    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    startActivityForResult(Intent.createChooser(i,"SELECT PICTURE"), CAPTURE_PICTURE);


  }

  public byte[] convertImageToByte(Uri uri){
    byte[] data = null;
    try {
      ContentResolver cr = getBaseContext().getContentResolver();
      InputStream inputStream = cr.openInputStream(uri);
      Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
      data = baos.toByteArray();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return data;
  }

  public byte[] convertBitmapImage(Bitmap bitmap){
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
    Log.d("myTag", "byte not created oh yeah");
    return stream.toByteArray();


  }

  public void onActivityResult(int requestCode, int resultCode, Intent data){
    super.onActivityResult(requestCode, resultCode, data);

    if(resultCode==RESULT_OK){
      if(requestCode==SELECT_PICTURE){


        Uri img = data.getData();

        image = convertImageToByte(img);
        IVPreviewImage.setImageURI(img);
        Log.d("myTag", "This is my message");
      }
      else{


        Bitmap img =(Bitmap)(data.getExtras().get("data"));
        image = convertBitmapImage(img);
          IVPreviewImage.setImageBitmap(img);
        Log.d("myTag", "This is camera");



      }
    }



  }


    private void initializeComponents() {

      EditText name = findViewById(R.id.dName);
      EditText breed = findViewById(R.id.dBreed);
      EditText age = findViewById(R.id.dAge);
      EditText weight = findViewById(R.id.dWeight);
      EditText vaccine = findViewById(R.id.dVaccine);
      Button addInfo = findViewById(R.id.addDog);

      RetrofitService retrofitService = new RetrofitService();
      DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

      addInfo.setOnClickListener(view -> {

        String dName, dBreed, dVac, dBt;
        int dAge, dWeight;

        dName = String.valueOf(name.getText().toString());
        dBreed = String.valueOf(breed.getText().toString());
        dAge = Integer.parseInt(age.getText().toString());
        dWeight = Integer.parseInt(weight.getText().toString());
        dVac = String.valueOf(vaccine.getText().toString());

        Dog dog = new Dog();

        dog.setName(dName);
        dog.setBreed(dBreed);
        dog.setAge(dAge);
        dog.setWeight(dWeight);
        dog.setVaccine(dVac);
        //dog.setTempImage(image);
        dog.setImage(image);

        dogApi.save(dog)
          .enqueue(new Callback<Dog>() {
                     @Override
                     public void onResponse(Call<Dog> call, Response<Dog> response) {
                       Toast.makeText(addDog.this, "Save Successful!", Toast.LENGTH_SHORT).show();
                     }

                     @Override
                     public void onFailure(Call<Dog> call, Throwable t) {
                       Toast.makeText(addDog.this, "Save Failed!", Toast.LENGTH_SHORT).show();
                       Logger.getLogger(addDog.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                     }
                   }
          );

      });
    }
  }

