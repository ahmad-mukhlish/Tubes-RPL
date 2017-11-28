package com.geeksquad.android.tubes.entity;

import android.os.Parcel;
import android.os.Parcelable;


public class Bahan implements Parcelable {

    private final String LOG_TAG = Bahan.class.getName();

    private String mNamaBahan;
    private int mJumlahBahan;

    public Bahan(String nama_bahan, int jumlah_bahan) {
        this.mNamaBahan = nama_bahan;
        this.mJumlahBahan = jumlah_bahan;
    }


    protected Bahan(Parcel in) {
        mNamaBahan = in.readString();
        mJumlahBahan = in.readInt();
    }

    public static final Creator<Bahan> CREATOR = new Creator<Bahan>() {
        @Override
        public Bahan createFromParcel(Parcel in) {
            return new Bahan(in);
        }

        @Override
        public Bahan[] newArray(int size) {
            return new Bahan[size];
        }
    };

    public String getmNamaBahan() {
        return mNamaBahan;
    }

    public int getmJumlahBahan() {
        return mJumlahBahan;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mNamaBahan);
        parcel.writeInt(mJumlahBahan);
    }
}
