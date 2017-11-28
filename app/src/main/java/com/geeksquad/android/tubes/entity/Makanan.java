package com.geeksquad.android.tubes.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Makanan implements Parcelable {

    private final String LOG_TAG = Makanan.class.getName();

    private String mProduk;
    private int mQty;
    private boolean mDone = false;
    private List<Bahan> mBahans;


    protected Makanan(Parcel in) {
        mProduk = in.readString();
        mQty = in.readInt();
        mDone = in.readByte() != 0;
        mBahans = in.createTypedArrayList(Bahan.CREATOR);
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

    public List<Bahan> getmBahans() {
        return mBahans;
    }

    public boolean ismDone() {
        return mDone;
    }

    public void setmDone(boolean mDone) {
        this.mDone = mDone;
    }

    public Makanan(String produk, int qty, List<Bahan> bahans) {
        this.mProduk = produk;
        this.mQty = qty;
        this.mBahans = bahans;
    }


    public String getmProduk() {
        return mProduk;
    }

    public int getmQty() {
        return mQty;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mProduk);
        parcel.writeInt(mQty);
        parcel.writeByte((byte) (mDone ? 1 : 0));
        parcel.writeTypedList(mBahans);
    }
}
