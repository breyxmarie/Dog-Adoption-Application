package com.example.dogprofile;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by Anil on 31/08/16.
 */
public class Utility {
  public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
  public static final int MY_PERMISSIONS_REQUEST_CAMERA = 124;

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  public static boolean checkPermission(final Context context) {
    int currentAPIVersion = Build.VERSION.SDK_INT;
    if (currentAPIVersion >= Build.VERSION_CODES.M) {
      if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
          ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);


        } else {
          ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
        return false;
      } else if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
          ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);

        } else {
          ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);

        }
        return false;
      } else {
        return true;
      }
    } else {
      return true;
    }
  }
}
