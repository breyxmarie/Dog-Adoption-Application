package com.example.dogprofile.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogprofile.EditDog;
import com.example.dogprofile.R;

public class DogHolderAdmin extends RecyclerView.ViewHolder{

  TextView id,name,breed,age,weight,vaccine,blood_type, tempI;
  Button btn;
  ImageView imgD;


  String dName,dBreed,dVaccine,dBType, dimgD;
  int dId,dAge,dWeight;

  public DogHolderAdmin(@NonNull View itemView){
    super(itemView);
    id = itemView.findViewById(R.id.admin_id);
    name = itemView.findViewById(R.id.admin_Name);
    breed = itemView.findViewById(R.id.admin_breed);
    age = itemView.findViewById(R.id.admin_age);
    weight = itemView.findViewById(R.id.admin_weight);
    vaccine = itemView.findViewById(R.id.admin_vaccine);
    imgD = itemView.findViewById(R.id.imgDog);
    tempI = itemView.findViewById(R.id.tempImages);

    btn = itemView.findViewById(R.id.editDetailsBtn);
    final SharedPreferences sharedPref= PreferenceManager.getDefaultSharedPreferences(itemView.getContext());

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(itemView.getContext(), EditDog.class);

        dId = Integer.parseInt(id.getText().toString());
        dName = String.valueOf(name.getText());
        dBreed = String.valueOf(breed.getText());
        dAge = Integer.parseInt(age.getText().toString());
        dWeight = Integer.parseInt(weight.getText().toString());
        dVaccine = String.valueOf(vaccine.getText());
        dimgD = String.valueOf(tempI.getText());

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("key1", dId);
        editor.putString("key2", dName);
        editor.putString("key3", dBreed);
        editor.putInt("key4", dAge);
        editor.putInt("key5",dWeight);
        editor.putString("key6", dVaccine);
        editor.putString("key7", dimgD);


        editor.apply();

        itemView.getContext().startActivity(i);
      }
    });

  }

}
