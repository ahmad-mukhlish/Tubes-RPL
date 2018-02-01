package com.geeksquad.android.tubes.entity;

import java.util.List;


public class Order {

    private final String LOG_TAG = Order.class.getName();

    public static String USERNAME = "admin";
    public static String PASSWORD = "jasuke";

    //GET JSON
    public static final String BASE_PATH = "http://192.168.1.11/ci-restserver/index.php/koki";

    //PUT JSON
    public static final String PUT_CONFIRM = "/konfirmasi";



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
