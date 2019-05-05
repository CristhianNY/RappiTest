package com.cristhian.com.rappitest;

import android.app.AlertDialog;
import android.content.Context;

import com.cristhian.com.rappitest.api.MovieApi;
import com.cristhian.com.rappitest.api.MovieClient;

public class Utils {
  //
  public static MovieApi getApi() {
      return MovieClient.getMovieClient().create(MovieApi.class);
  }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }
}
