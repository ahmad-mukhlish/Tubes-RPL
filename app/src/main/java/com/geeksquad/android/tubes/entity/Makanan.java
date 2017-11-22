package com.geeksquad.android.tubes.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by GOODWARE1 on 11/18/2017.
 */

public class Makanan implements Parcelable {

    private String produk;
    private int qty;
    private boolean done = false;
    private List<Bahan> bahans;


    protected Makanan(Parcel in) {
        produk = in.readString();
        qty = in.readInt();
        done = in.readByte() != 0;
        bahans = in.createTypedArrayList(Bahan.CREATOR);
    }

    public static final Creator<Makanan> CREATOR = new Creator<Makanan>() {
        @Override
        public Makanan createFromParcel(Parcel in) {
            return new Makanan(in);
        }

        @Override
        public Makanan[] newArray(int size) {
            return new Makanan[size];
        }
    };

    public List<Bahan> getBahans() {
        return bahans;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Makanan(String produk, int qty, List<Bahan> bahans) {
        this.produk = produk;
        this.qty = qty;
        this.bahans = bahans;
    }



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
        parcel.writeByte((byte) (done ? 1 : 0));
        parcel.writeTypedList(bahans);
    }
}
