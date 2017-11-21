package com.geeksquad.android.tubes.entity;

import java.util.List;

/**
 * Created by ASUS on 25/10/2017.
 */

public class Order {


    public static String USERNAME = "admin";
    public static String PASSWORD = "jasuke";

    public static final String BASE_PATH = "http://192.168.1.9/restoran/";
    public static final String JSON_REPLY_MENU = "server.php?operasi=view";

    public static String DUMMY_RESPONSE = "[{\"no_meja\":6,\"tanggal\":\"2017-11-08  09:10:00\",\"items\":3,\"detail\":[{\"produk\":\"Tiramisu\",\"qty\":2},{\"produk\":\"Nasi goreng\",\"qty\":2},{\"produk\":\"Cola\",\"qty\":2}],\"keterangan\":\"Colanya jangan pake lama\"}," +
            "{\"no_meja\":5,\"tanggal\":\"2017-11-08  09:10:00\",\"items\":3,\"detail\":[{\"produk\":\"Tiramisu\",\"qty\":2},{\"produk\":\"Nasi goreng\",\"qty\":2},{\"produk\":\"Cola\",\"qty\":2}],\"keterangan\":\"Colanya jangan pake lama\"}," +
            "{\"no_meja\":4,\"tanggal\":\"2017-11-08  09:10:00\",\"items\":3,\"detail\":[{\"produk\":\"Tiramisu\",\"qty\":2},{\"produk\":\"Nasi goreng\",\"qty\":2},{\"produk\":\"Cola\",\"qty\":2}],\"keterangan\":\"Colanya jangan pake lama\"}," +
            "{\"no_meja\":2,\"tanggal\":\"2017-11-08  09:10:00\",\"items\":3,\"detail\":[{\"produk\":\"Tiramisu\",\"qty\":2},{\"produk\":\"Nasi goreng\",\"qty\":2},{\"produk\":\"Cola\",\"qty\":2}],\"keterangan\":\"Colanya jangan pake lama\"}," +
            "{\"no_meja\":8,\"tanggal\":\"2017-11-08  09:10:00\",\"items\":3,\"detail\":[{\"produk\":\"Tiramisu\",\"qty\":2},{\"produk\":\"Nasi goreng\",\"qty\":2},{\"produk\":\"Cola\",\"qty\":2}],\"keterangan\":\"Colanya jangan pake lama\"}," +
            "{\"no_meja\":4,\"tanggal\":\"2017-11-08  09:10:00\",\"items\":3,\"detail\":[{\"produk\":\"Tiramisu\",\"qty\":2},{\"produk\":\"Nasi goreng\",\"qty\":2},{\"produk\":\"Cola\",\"qty\":2}],\"keterangan\":\"Colanya jangan pake lama\"}]";

    private String tanggal, keterangan;
    private int no_meja, items;
    private List<Detail> detail;


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    private boolean visible = true;


    public Order(int no_meja, String tanggal, String keterangan, int items, List<Detail> detail) {
        this.no_meja = no_meja;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.items = items;
        this.detail = detail;
    }

    public int getNo_meja() {
        return no_meja;
    }


    public String getTanggal() {
        return tanggal;
    }


    public String getKeterangan() {
        return keterangan;
    }


    public int getItems() {
        return items;
    }


    public List<Detail> getDetail() {
        return detail;
    }


}
