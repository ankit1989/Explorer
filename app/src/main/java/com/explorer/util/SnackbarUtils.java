package com.explorer.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.explorer.R;

/**
 * Created by ankitpatel on 3/4/18.
 */

public class SnackbarUtils {

    private SnackbarUtils() {

    }

    public static Snackbar prepareSnackBar (View view, String message) {
        return Snackbar.make(view, message, Snackbar.LENGTH_LONG);
    }

    public static void showSnackBar (View view, String message, String action,
                                     View.OnClickListener listener) {
        Snackbar snackbar = prepareSnackBar(view, message);
        if (listener != null) {
            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(action, listener);
        }
        snackbar.show();
    }

    public static void showSnackBar (View view, String message) {
        showSnackBar(view, message, null, null);
    }
}
