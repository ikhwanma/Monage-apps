package com.android.monagealpha;

public class DataLaporan {
    private String title,kategori,memo,tanggal;
    private int uang;

    public DataLaporan() {
    }

    public DataLaporan(String title, String kategori, String memo, String tanggal, int uang) {
        this.title = title;
        this.kategori = kategori;
        this.memo = memo;
        this.tanggal = tanggal;
        this.uang = uang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }
}
