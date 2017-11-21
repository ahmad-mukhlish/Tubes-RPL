package com.geeksquad.android.tubes.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GOODWARE1 on 11/21/2017.
 */

public class Bahan implements Parcelable {

    private String nama_bahan;
    private int jumlah_bahan;

    public Bahan(String nama_bahan, int jumlah_bahan) {
        this.nama_bahan = nama_bahan;
        this.jumlah_bahan = jumlah_bahan;
    }


    protected Bahan(Parcel in) {
        nama_bahan = in.readString();
        jumlah_bahan = in.readInt();
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

    public String getNama_bahan() {
        return nama_bahan;
    }

    public void setNama_bahan(String nama_bahan) {
        this.nama_bahan = nama_bahan;
    }

    public int getJumlah_bahan() {
        return jumlah_bahan;
    }

    public void setJumlah_bahan(int jumlah_bahan) {
        this.jumlah_bahan = jumlah_bahan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama_bahan);
        parcel.writeInt(jumlah_bahan);
    }
}
