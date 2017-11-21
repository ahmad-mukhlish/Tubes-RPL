package com.geeksquad.android.tubes.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GOODWARE1 on 11/18/2017.
 */

public class Detail implements Parcelable {
    private String produk;
    private int qty;

    public Detail(String produk, int qty) {
        this.produk = produk;
        this.qty = qty;
    }

    protected Detail(Parcel in) {
        produk = in.readString();
        qty = in.readInt();
    }

    public static final Creator<Detail> CREATOR = new Creator<Detail>() {
        @Override
        public Detail createFromParcel(Parcel in) {
            return new Detail(in);
        }

        @Override
        public Detail[] newArray(int size) {
            return new Detail[size];
        }
    };

    public String getProduk() {
        return produk;
    }

    public int getQty() {
        return qty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(produk);
        parcel.writeInt(qty);
    }
}
