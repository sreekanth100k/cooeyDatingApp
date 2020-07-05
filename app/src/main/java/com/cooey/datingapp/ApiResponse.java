package com.cooey.datingapp;

import androidx.room.Entity;

@Entity(tableName = "apiResponse")
public class ApiResponse {

         String picture;
         String name;
         Geolocation geoLocation;
         String gender;
         Integer age;
         String  favoriteColor;
         String  phone;
         String  lastSeen;
         String  id;
         String  email;
}
