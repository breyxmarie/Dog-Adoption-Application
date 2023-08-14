package com.example.dogprofile.model;

import android.os.Build;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class Dog {


    private long id;
    private String name;
    private String breed;
    private int age;
    private int weight;
    private String vaccine;
    private byte[] image;
    //private byte[] tempImage;



    public Dog(long id, String name, String breed, int age, int weight, String vaccine, byte[] image) {

        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.vaccine = vaccine;
        this.image = image;
    }



    public Dog() {

        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.vaccine = vaccine;
      this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public String convertByteToBase64() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      return Base64.getEncoder().encodeToString(image);
    }
    return null;
  }

  // Convert a base64-encoded string back to a byte[]
  public void convertBase64ToByte(String base64Image) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      this.image = Base64.getDecoder().decode(base64Image);
    }
  }


//  public byte[] getTempImage() {
//    return tempImage;
//  }
//
//  public void setTempImage(byte[] tempImage) {
//    this.tempImage = tempImage;
//  }

  @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.breed);
        hash = 79 * hash + Objects.hashCode(this.age);
        hash = 79 * hash + Objects.hashCode(this.weight);
        hash = 79 * hash + Objects.hashCode(this.vaccine);
        hash = 79 * hash + Objects.hashCode(this.image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dog other = (Dog) obj;
        if (!Objects.equals(this.breed, other.breed)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        if (!Objects.equals(this.weight, other.weight)) {
            return false;
        }
        if (!Objects.equals(this.vaccine, other.vaccine)) {
            return false;
        }
    if (!Objects.equals(this.image, other.image)) {
        return false;
      }
        return Objects.equals(this.id, other.id);
    }


    public static String convertByteToString(byte[] byteArray) {
      return new String(byteArray, StandardCharsets.UTF_8);
    }


    @Override
    public String toString() {

      String base64ImageString = convertByteToString(image);
        final StringBuilder sb = new StringBuilder("Dog{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", breed=").append(breed);
        sb.append(", age=").append(age);
        sb.append(", weight=").append(weight);
        sb.append(", is vaccinated?=").append(vaccine);
        sb.append(", image?=").append(base64ImageString);
        sb.append('}');
        return sb.toString();
    }
}
