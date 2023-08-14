package com.example.dogprofile.retrofit;

import android.os.Build;
import android.util.Base64;

import com.example.dogprofile.model.Dog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
  private Retrofit retrofit;

  public RetrofitService() {
    initializeRetrofit();
  }

  private void initializeRetrofit() {
    OkHttpClient httpClient = new OkHttpClient.Builder().build();

    Gson gson = new GsonBuilder()
      .registerTypeAdapter(Dog.class, new DogTypeAdapter())
      .create();

    retrofit = new Retrofit.Builder()
      .baseUrl("http://192.168.1.5:8080")
      .client(httpClient)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build();
  }

  public Retrofit getRetrofit() {
    return retrofit;
  }

  // Custom TypeAdapter for Dog class to handle byte[] serialization and deserialization
  private static class DogTypeAdapter extends TypeAdapter<Dog> {
    @Override
    public void write(JsonWriter out, Dog dog) throws IOException {
      out.beginObject();
      out.name("id").value(dog.getId());
      out.name("name").value(dog.getName());
      out.name("breed").value(dog.getBreed());
      out.name("age").value(dog.getAge());
      out.name("weight").value(dog.getWeight());
      out.name("vaccine").value(dog.getVaccine());
      if (dog.getImage() != null) {
        String base64Image = Base64.encodeToString(dog.getImage(), Base64.NO_WRAP);
        out.name("image").value(base64Image);
      } else {
        out.name("image").nullValue();
      }
      out.endObject();
    }

    @Override
    public Dog read(JsonReader in) throws IOException {
      Dog dog = new Dog();
      in.beginObject();
      while (in.hasNext()) {
        String name = in.nextName();
        if (name.equals("id")) {
          dog.setId(in.nextLong());
        } else if (name.equals("name")) {
          dog.setName(in.nextString());
        } else if (name.equals("breed")) {
          dog.setBreed(in.nextString());
        } else if (name.equals("age")) {
          dog.setAge(in.nextInt());
        } else if (name.equals("weight")) {
          dog.setWeight(in.nextInt());
        } else if (name.equals("vaccine")) {
          dog.setVaccine(in.nextString());
        } else if (name.equals("image")) {
          if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            dog.setImage(null);
          } else {
            String base64Image = in.nextString();
            dog.setImage(Base64.decode(base64Image, Base64.NO_WRAP));
          }
        } else {
          in.skipValue();
        }
      }
      in.endObject();
      return dog;
    }
  }
}
