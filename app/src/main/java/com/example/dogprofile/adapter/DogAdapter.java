package com.example.dogprofile.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogprofile.MainActivity;
import com.example.dogprofile.R;
import com.example.dogprofile.model.Dog;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DogAdapter extends RecyclerView.Adapter<DogHolder>{

  private List<Dog> dogList;

  public DogAdapter(List<Dog> dogList){ this.dogList = dogList;}



  @NonNull
  @Override
  public DogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext())
          .inflate(R.layout.list_dogs, parent, false);
    return new DogHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DogHolder holder, int position) {
        Dog dog = dogList.get(position);
        holder.id.setText(String.valueOf(dog.getId()));
        holder.name.setText(dog.getName());
        holder.breed.setText(dog.getBreed());
        holder.age.setText(String.valueOf(dog.getAge()));
        holder.weight.setText(String.valueOf(dog.getWeight()));
        holder.vaccine.setText(dog.getVaccine());
       // holder.tempI.setText(dog.getImage());


    byte[] byteArray = Base64.decode(dog.getImage(), Base64.DEFAULT);
    // System.out.println("byte na  " + Arrays.toString(byteArray));
    Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);;


    if (bitmap != null) {
      try {
        holder.imgD.setImageBitmap(bitmap);
      } catch (Throwable t) {
        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred while setting bitmap", t);

      }
    } else {
      Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Bitmap is null or couldn't be decoded");
    }

  }

  @Override
  public int getItemCount() { return dogList.size(); }
}
