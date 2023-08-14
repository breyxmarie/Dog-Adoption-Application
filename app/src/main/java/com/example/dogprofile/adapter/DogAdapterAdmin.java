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

public class DogAdapterAdmin extends RecyclerView.Adapter<DogHolderAdmin>{
  private List<Dog> dogList;

  public DogAdapterAdmin(List<Dog> dogList){ this.dogList = dogList;}



  @NonNull
  @Override
  public DogHolderAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext())
      .inflate(R.layout.list_admin_dogs, parent, false);
    return new DogHolderAdmin(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DogHolderAdmin holder, int position) {
    Dog dog = dogList.get(position);

    holder.id.setText(String.valueOf(dog.getId()));
    holder.name.setText(dog.getName());
    holder.breed.setText(dog.getBreed());
    holder.age.setText(String.valueOf(dog.getAge()));
    holder.weight.setText(String.valueOf(dog.getWeight()));
    holder.vaccine.setText(dog.getVaccine());
    //.tempI.setText(dog.getImage());
  //holder.imgD.setImageBitmap();

   // byte[] byteArray = Base64.decode(dog.getImage(), Base64.DEFAULT);
    // System.out.println("byte na  " + Arrays.toString(byteArray));
    Bitmap bitmap = BitmapFactory.decodeByteArray(dog.getImage(), 0, dog.getImage().length);;


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
