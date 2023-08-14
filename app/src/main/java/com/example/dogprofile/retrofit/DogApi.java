package com.example.dogprofile.retrofit;

import com.example.dogprofile.model.Dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DogApi {

    @GET("/api/dogs")
   //@GET("/")
    Call<List<Dog>> getAllDogs();

    @POST("/api/add-dog")
    Call <Dog> save (@Body Dog dog);

    @PUT("/api/update-dog/{id}")
    Call<Dog> updateDog(@Path("id") long id, @Body Dog dog);

    @DELETE("/api/delete-dog/{id}")
    Call<Void> deleteDog(@Path("id") long id);

}
