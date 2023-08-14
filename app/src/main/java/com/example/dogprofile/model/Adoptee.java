package com.example.dogprofile.model;

import android.os.Build;

import java.util.Base64;
import java.util.Objects;

public class Adoptee {
  private long adoption_id;
  private String date;
  private String name;
  private String email;
  private String address;
  private String contact_number;
  private long dog_id;
  private byte[] image;
  private Dog dog = new Dog();

  public Adoptee(){

  }

  public long getAdoption_id() {
    return adoption_id;
  }

  public void setAdoption_id(long adoption_id) {
    this.adoption_id = adoption_id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Dog getDog() {
    return dog;
  }

  public void setDog(Dog dog) {
    this.dog = dog;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContact_number() {
    return contact_number;
  }

  public void setContact_number(String contact_number) {
    this.contact_number = contact_number;
  }

  public long getDog_id() {
    return dog_id;
  }

  public void setDog_id(long dog_id) {
    this.dog_id = dog_id;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  @Override
  public int hashCode() {
    int hash = 8;
    hash = 79 * hash + Objects.hashCode(this.adoption_id);
    hash = 79 * hash + Objects.hashCode(this.date);
    hash = 79 * hash + Objects.hashCode(this.name);
    hash = 79 * hash + Objects.hashCode(this.email);
    hash = 79 * hash + Objects.hashCode(this.address);
    hash = 79 * hash + Objects.hashCode(this.contact_number);
    hash = 79 * hash + Objects.hashCode(this.dog_id);
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
    final Adoptee other = (Adoptee) obj;
    if (!Objects.equals(this.adoption_id, other.adoption_id)) {
      return false;
    }
    if (!Objects.equals(this.date, other.date)) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(this.email, other.email)) {
      return false;
    }
    if (!Objects.equals(this.address, other.address)) {
      return false;
    }
    if (!Objects.equals(this.contact_number, other.contact_number)) {
      return false;
    }
    if (!Objects.equals(this.dog_id, other.dog_id)) {
      return false;
    }
    if (!Objects.equals(this.image, other.image)) {
      return false;
    }
    return Objects.equals(this.adoption_id, other.adoption_id);
  }

  public static String convertByteToString(byte[] byteArray) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      return Base64.getEncoder().encodeToString(byteArray);
    }
    return null;
  }

  @Override
  public String toString() {
    String base64ImageString = convertByteToString(image);
    final StringBuilder sb = new StringBuilder("Adoptee{");
    sb.append("id=").append(adoption_id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", date=").append(date);
    sb.append(", email=").append(email);
    sb.append(", address=").append(address);
    sb.append(", contact_number=").append(contact_number);
    sb.append(", dog_id=").append(dog_id);
    sb.append(", image?=").append(base64ImageString);
    sb.append('}');
    return sb.toString();
  }
}
