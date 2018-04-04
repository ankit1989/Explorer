package com.explorer;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ankitpatel on 3/4/18.
 */

public class Binder {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {

        if (TextUtils.isEmpty(imageUrl)) {
            imageView.setImageResource(R.drawable.no_image_available);
            return;
        }

        //used picasso to fetch the image and load in to image view
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.error_image)
                .into(imageView);
    }
}
