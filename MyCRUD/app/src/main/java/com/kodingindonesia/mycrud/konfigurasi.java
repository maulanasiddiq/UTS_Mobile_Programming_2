package com.kodingindonesia.mycrud;

/**
 * Created by muhammadyusuf on 01/19/2017.
 * kodingindonesia
 */

public class konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.43.196/laptop/tambahPgw.php";
    public static final String URL_GET_ALL = "http://192.168.43.196/laptop/tampilSemuaPgw.php";
    public static final String URL_GET_EMP = "http://192.168.43.196/laptop/tampilPgw.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.196/laptop/updatePgw.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.196/laptop/hapusPgw.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_WARNA = "warna"; //desg itu variabel untuk posisi
    public static final String KEY_EMP_STOK = "stok"; //salary itu variabel untuk gajih
    public static final String KEY_EMP_JENIS = "jenis"; //salary itu variabel untuk gajih

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_WARNA = "warna";
    public static final String TAG_STOK = "stok";
    public static final String TAG_JENIS = "jenis";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
}
