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

import com.example.dogprofile.AdopteeProfile;
import com.example.dogprofile.R;

import java.util.prefs.PreferenceChangeEvent;

public class AdopteeHolder extends RecyclerView.ViewHolder {

  TextView name, date, dogName, dog_id, email, contactNumber;
  ImageView adoptee_image;
  Button btn;

  String aName, aDate, aDogName, aDogId, aEmail, aContactNumber;

  public AdopteeHolder(@NonNull View itemView) {

    super(itemView);
    name = itemView.findViewById(R.id.name);
    date = itemView.findViewById(R.id.date);
    dogName = itemView.findViewById(R.id.dog_name);
    adoptee_image = itemView.findViewById(R.id.adoptee_image);
    dog_id = itemView.findViewById(R.id.Adog_id);
    email = itemView.findViewById(R.id.AEmail);
    contactNumber = itemView.findViewById(R.id.AContactNumber);

    btn = itemView.findViewById(R.id.viewADetails);
    final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());

    btn.setOnClickListener(new View.OnClickListener(){
      @Override
        public void onClick(View view){
        Intent i = new Intent(itemView.getContext(), AdopteeProfile.class);

          aName = String.valueOf(name);
          aDate = String.valueOf(date);
          aDogName = String.valueOf(dogName);
          aDogId = String.valueOf(dog_id);
          aEmail = String.valueOf(email);
          aContactNumber = String.valueOf(contactNumber);

          SharedPreferences.Editor editor = sharedPref.edit();

          editor.putString("key1",aName);
          editor.putString("key2", aDate);
          editor.putString("key3", aDogName);
          editor.putString("key4", aDogId);
          editor.putString("key5", aEmail);
          editor.putString("key6", aContactNumber);

          editor.apply();

          itemView.getContext().startActivity(i);
      }
    });
  }
}
