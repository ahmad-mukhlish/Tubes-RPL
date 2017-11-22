package com.geeksquad.android.tubes.entity;

import java.util.List;

/**
 * Created by ASUS on 25/10/2017.
 */

public class Order {


    public static String USERNAME = "admin";
    public static String PASSWORD = "jasuke";

    public static final String BASE_PATH = "http://192.168.1.3/restoran/";
    public static final String JSON_REPLY_KOKI = "server.php?operasi=view_koki";


    private int meja, items;
    private String tanggal, catatan;
    private List<Makanan> listmakanan;


    public Order(int meja, String tanggal, String keterangan, int items, List<Makanan> listmakanan) {
        this.meja = meja;
        this.tanggal = tanggal;
        this.catatan = keterangan;
        this.items = items;
        this.listmakanan = listmakanan;
    }

    public int getMeja() {
        return meja;
    }


    public String getTanggal() {
        return tanggal;
    }


    public String getCatatan() {
        return catatan;
    }


    public int getItems() {
        return items;
    }


    public List<Makanan> getListmakanan() {
        return listmakanan;
    }


}
