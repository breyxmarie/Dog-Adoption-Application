package com.example.dogprofile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dogprofile.R;
import com.example.dogprofile.model.Adoptee;

import java.util.List;

public class AdopteeAdapter extends RecyclerView.Adapter<AdopteeHolder>{

  private List<Adoptee> adopteeList;

  public AdopteeAdapter(List<Adoptee> adopteeList) {
    this.adopteeList = adopteeList;
  }

  @NonNull
  @Override
  public AdopteeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.list_adoptee_transactions, parent, false);
    return new AdopteeHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull AdopteeHolder holder, int position) {
    Adoptee adoptee = adopteeList.get(position);
    holder.name.setText(adoptee.getName());
    holder.date.setText(adoptee.getDate());
    holder.dogName.setText(adoptee.getDate());
    //holder.adoptee_image.
    holder.dog_id.setText(String.valueOf(adoptee.getDog_id()));
    holder.email.setText(adoptee.getEmail());
    holder.contactNumber.setText(adoptee.getContact_number());
  }

  @Override
  public int getItemCount( ) {
    return adopteeList.size();
  }
  }


