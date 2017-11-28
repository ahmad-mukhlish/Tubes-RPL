package com.geeksquad.android.tubes.entity;

import java.util.List;


public class Order {

    private final String LOG_TAG = Order.class.getName();

    public static String USERNAME = "admin";
    public static String PASSWORD = "jasuke";

    public static final String BASE_PATH = "http://192.168.1.3/restoran/";
    public static final String JSON_REPLY_KOKI = "server.php?operasi=rincian_koki";


    private int mMeja, mItems;
    private String mTanggal, mCatatan;
    private List<Makanan> mListMakanan;


    public Order(int meja, String tanggal, String keterangan, int items, List<Makanan> listmakanan) {
        this.mMeja = meja;
        this.mTanggal = tanggal;
        this.mCatatan = keterangan;
        this.mItems = items;
        this.mListMakanan = listmakanan;
    }

    public int getmMeja() {
        return mMeja;
    }


    public String getmTanggal() {
        return mTanggal;
    }


    public String getmCatatan() {
        return mCatatan;
    }


    public int getmItems() {
        return mItems;
    }


    public List<Makanan> getListmakanan() {
        return mListMakanan;
    }


}
