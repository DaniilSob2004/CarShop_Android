package com.example.carshop_hw.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class CarModel implements Parcelable {

    private int idPhoto;
    private String brand;
    private String model;
    private int year;
    private String description;
    private float cost;


    public CarModel() {}

    public CarModel(int idPhoto, String brand, String model, int year, String description, float cost) {
        this.idPhoto = idPhoto;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.description = description;
        this.cost = cost;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeInt(idPhoto);
        parcel.writeString(brand);
        parcel.writeString(model);
        parcel.writeInt(year);
        parcel.writeString(description);
        parcel.writeFloat(cost);
    }

    // CREATOR для воссоздания объекта из Parcel
    public static final Parcelable.Creator<CarModel> CREATOR = new Parcelable.Creator<CarModel>() {
        @Override
        public CarModel createFromParcel(Parcel parcel) {
            int idPhoto = parcel.readInt();
            String brand = parcel.readString();
            String model = parcel.readString();
            int year = parcel.readInt();
            String description = parcel.readString();
            float cost = parcel.readFloat();
            return new CarModel(idPhoto, brand, model, year, description, cost);
        }

        @Override
        public CarModel[] newArray(int size) {
            return new CarModel[size];
        }
    };


    public int getIdPhoto() {
        return idPhoto;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public float getCost() {
        return cost;
    }


    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
