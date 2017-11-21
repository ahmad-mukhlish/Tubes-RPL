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


    public static String HATTA_RESPONSE = "[{\"meja\":\"1\",\"tanggal\":\"2017-11-01\",\"catatan\":\"jangan pake lama\",\"listmakanan\":[{\"id_makanan\":\"1\",\"nama\":\"Burger\",\"qty\":\"3\",\"bahan\":[{\"id_bahan\":\"1\",\"nama_bahan\":\"Daging\",\"jumlah_bahan\":\"50\"},{\"id_bahan\":\"2\",\"nama_bahan\":\"Roti Burger\",\"jumlah_bahan\":\"100\"},{\"id_bahan\":\"3\",\"nama_bahan\":\"Telur\",\"jumlah_bahan\":\"75\"},{\"id_bahan\":\"4\",\"nama_bahan\":\"Garam\",\"jumlah_bahan\":\"2\"},{\"id_bahan\":\"5\",\"nama_bahan\":\"Merica\",\"jumlah_bahan\":\"1\"},{\"id_bahan\":\"6\",\"nama_bahan\":\"Bawang Bombay\",\"jumlah_bahan\":\"2\"},{\"id_bahan\":\"7\",\"nama_bahan\":\"Daun Selada\",\"jumlah_bahan\":\"2\"},{\"id_bahan\":\"8\",\"nama_bahan\":\"Keju\",\"jumlah_bahan\":\"4\"},{\"id_bahan\":\"9\",\"nama_bahan\":\"Mentega\",\"jumlah_bahan\":\"1\"},{\"id_bahan\":\"10\",\"nama_bahan\":\"Saus Tomat\",\"jumlah_bahan\":\"1\"}]}," +
            "{\"id_makanan\":\"3\",\"nama\":\"Nasi Goreng\",\"qty\":\"4\",\"bahan\":[{\"id_bahan\":\"17\",\"nama_bahan\":\"Bawang Merah\",\"jumlah_bahan\":\"15\"},{\"id_bahan\":\"18\",\"nama_bahan\":\"Bawang Putih\",\"jumlah_bahan\":\"15\"},{\"id_bahan\":\"19\",\"nama_bahan\":\"Cabai Merah\",\"jumlah_bahan\":\"10\"},{\"id_bahan\":\"20\",\"nama_bahan\":\"Saus Tiram\",\"jumlah_bahan\":\"10\"},{\"id_bahan\":\"10\",\"nama_bahan\":\"Saus Tomat\",\"jumlah_bahan\":\"10\"},{\"id_bahan\":\"4\",\"nama_bahan\":\"Garam\",\"jumlah_bahan\":\"5\"},{\"id_bahan\":\"21\",\"nama_bahan\":\"Kecap Manis\",\"jumlah_bahan\":\"10\"},{\"id_bahan\":\"22\",\"nama_bahan\":\"Nasi\",\"jumlah_bahan\":\"500\"},{\"id_bahan\":\"3\",\"nama_bahan\":\"Telur\",\"jumlah_bahan\":\"75\"},{\"id_bahan\":\"23\",\"nama_bahan\":\"Sosis\",\"jumlah_bahan\":\"75\"}]}," +
            "{\"id_makanan\":\"7\",\"nama\":\"Bajigur\",\"qty\":\"6\",\"bahan\":[{\"id_bahan\":\"34\",\"nama_bahan\":\"Santan\",\"jumlah_bahan\":\"150\"},{\"id_bahan\":\"30\",\"nama_bahan\":\"Jahe\",\"jumlah_bahan\":\"5\"},{\"id_bahan\":\"33\",\"nama_bahan\":\"Gula Merah\",\"jumlah_bahan\":\"75\"},{\"id_bahan\":\"31\",\"nama_bahan\":\"Kayu Manis\",\"jumlah_bahan\":\"3\"},{\"id_bahan\":\"35\",\"nama_bahan\":\"Kopi Bubuk\",\"jumlah_bahan\":\"5\"}]}]}]";

    private String tanggal, keterangan;
    private int no_meja, items;
    private List<Detail> detail;


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
