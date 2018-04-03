package com.explorer;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ankitpatel on 3/4/18.
 */

public class Binder {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        //used picasso to fetch the image and load in to image view
        Picasso.get()
                .load(imageUrl)
                .into(imageView);

    }
}
