package com.example.ppapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class HoaDon implements Serializable {
    private int id;
    private String maHoaDon;
    private String trangThai;
    private int viTriTrang;
    private String tenShipper;
    private String gioXuat;
    private String ngayLap;
    private String loaiHoaDon;
    public HoaDon() {
    }

    public HoaDon(int id, String maHoaDon, String trangThai, int viTriTrang, String tenShipper, String gioXuat, String ngayLap,String loaiHoaDon) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.trangThai = trangThai;
        this.viTriTrang = viTriTrang;
        this.tenShipper = tenShipper;
        this.gioXuat = gioXuat;
        this.ngayLap = ngayLap;
        this.loaiHoaDon = loaiHoaDon;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getViTriTrang() {
        return viTriTrang;
    }

    public void setViTriTrang(int viTriTrang) {
        this.viTriTrang = viTriTrang;
    }

    public String getTenShipper() {
        return tenShipper;
    }

    public void setTenShipper(String tenShipper) {
        this.tenShipper = tenShipper;
    }

    public String getGioXuat() {
        return gioXuat;
    }

    public void setGioXuat(String gioXuat) {
        this.gioXuat = gioXuat;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public void setLoaiHoaDon(String loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }
    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }


    @Override
    public String toString() {
        return "HoaDon{" +
                "id=" + id +
                ", maHoaDon='" + maHoaDon + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", viTriTrang=" + viTriTrang +
                ", tenShipper='" + tenShipper + '\'' +
                ", gioXuat='" + gioXuat + '\'' +
                ", ngayLap='" + ngayLap + '\'' +
                ", loaiHoaDon='" + loaiHoaDon + '\'' +
                '}';
    }
}
