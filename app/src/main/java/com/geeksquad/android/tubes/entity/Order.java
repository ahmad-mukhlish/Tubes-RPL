package com.geeksquad.android.tubes.entity;

import java.util.List;


public class Order {

    private final String LOG_TAG = Order.class.getName();

    public static String USERNAME = "admin";
    public static String PASSWORD = "jasuke";

    public static final String BASE_PATH = "http://192.168.1.3/restoran/";
    public static final String JSON_REPLY_KOKI = "server.php?operasi=rincian_koki";
    public static final String JSON_CONFIRM = "server.php?operasi=konfirmasi_masak&kode_pesanan=";


    private int mMeja, mItems, mKodePesanan;
    private String mTanggal, mCatatan;
    private List<Makanan> mListMakanan;


    public Order(int mMeja, int mItems, int mKodePesanan, String mTanggal, String mCatatan, List<Makanan> mListMakanan) {
        this.mMeja = mMeja;
        this.mItems = mItems;
        this.mKodePesanan = mKodePesanan;
        this.mTanggal = mTanggal;
        this.mCatatan = mCatatan;
        this.mListMakanan = mListMakanan;
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


    public int getmKodePesanan() {
        return mKodePesanan;
    }
}
