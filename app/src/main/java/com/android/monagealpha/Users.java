package com.android.monagealpha;

public class Users {
    private  String nama;
    private String email;
    private String password;
    private String img;
    private int pemasukan;
    private int pengeluaran;
    private int saldo;
    private String kategori;

    public Users() {
    }

    public Users(String nama, String email, String password, String img, int pemasukan, int pengeluaran, int saldo, String kategori) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.img = img;
        this.pemasukan = pemasukan;
        this.pengeluaran = pengeluaran;
        this.saldo = saldo;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPemasukan() {
        return pemasukan;
    }

    public void setPemasukan(int pemasukan) {
        this.pemasukan = pemasukan;
    }

    public int getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(int pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
