package com.example.akashjpro.model;

import java.io.Serializable;

/**
 * Created by Akashjpro on 9/28/2016.
 */

public class QuocGia implements Serializable {
    private Integer id;
    private String  ten;
    private String  thuDo;
    private String  quocTich;
    private byte[]    quocKy;
    private Number  dienTich;
    private Number      danSo;
    private String  khuVuc;
    private String  GDP;
    private String  dvTien;

    public QuocGia(Integer id, String ten, String thuDo, String quocTich, byte[] quocKy, Number dienTich, Number danSo, String khuVuc, String GDP, String dvTien)
    {
        this.id = id;
        this.ten = ten;
        this.thuDo = thuDo;
        this.quocTich = quocTich;
        this.quocKy = quocKy;
        this.dienTich = dienTich;
        this.danSo = danSo;
        this.khuVuc = khuVuc;
        this.GDP = GDP;
        this.dvTien = dvTien;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getThuDo() {
        return thuDo;
    }

    public void setThuDo(String thuDo) {
        this.thuDo = thuDo;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public byte[] getQuocKy() {
        return quocKy;
    }

    public void setQuocKy(byte[] quocKy) {
        this.quocKy = quocKy;
    }

    public Number getDienTich() {
        return dienTich;
    }

    public void setDienTich(Number dienTich) {
        this.dienTich = dienTich;
    }

    public Number getDanSo() {
        return danSo;
    }

    public void setDanSo(Number danSo) {
        this.danSo = danSo;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }

    public String getGDP() {
        return GDP;
    }

    public void setGDP(String GDP) {
        this.GDP = GDP;
    }

    public String getDvTien() {
        return dvTien;
    }

    public void setDvTien(String dvTien) {
        this.dvTien = dvTien;
    }
}
