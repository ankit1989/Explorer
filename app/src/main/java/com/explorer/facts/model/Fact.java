package com.explorer.facts.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ankitpatel on 3/4/18.
 */

public class Fact extends BaseObservable implements Parcelable {

    private String title;
    private String description;
    @SerializedName("imageHref")
    private String imageUrl;

    public Fact (String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Fact(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Fact> CREATOR = new Parcelable.Creator<Fact>() {
        @Override
        public Fact createFromParcel(Parcel in) {
            return new Fact(in);
        }

        @Override
        public Fact[] newArray(int size) {
            return new Fact[size];
        }
    };

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
