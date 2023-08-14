package com.example.dogprofile.retrofit;



import com.example.dogprofile.model.Adoptee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdopteeApi {

  @GET("/api/adoptees")
    //@GET("/")
  Call<List<Adoptee>> getAllAdoptees();

  @POST("/api/add-adoptee")
  Call <Adoptee> save (@Body Adoptee dog);

  @PUT("/api/update-adoptee/{id}")
  Call<Adoptee> updateAdoptee(@Path("id") long id, @Body Adoptee dog);

  @DELETE("/api/delete-adoptee/{id}")
  Call<Void> deleteAdoptee(@Path("id") long id);
}
