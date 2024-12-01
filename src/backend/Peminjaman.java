/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.util.ArrayList;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Peminjaman {

    private int idpeminjaman;
    private Anggota idanggota = new Anggota();
    private Buku idbuku = new Buku();
    private String tanggalpinjam;
    private String tanggalkembali;

    public Peminjaman() {
    }

    public Peminjaman(Anggota idanggota, Buku idbuku, String tanggalpinjam, String tanggalkembali) {
        this.idanggota = idanggota;
        this.idbuku = idbuku;
        this.tanggalpinjam = tanggalpinjam;
        this.tanggalkembali = tanggalkembali;
    }

    public int getIdpeminjaman() {
        return idpeminjaman;
    }

    public void setIdpeminjaman(int idpeminjaman) {
        this.idpeminjaman = idpeminjaman;
    }

    public Anggota getIdanggota() {
        return idanggota;
    }

    public void setIdanggota(Anggota idanggota) {
        this.idanggota = idanggota;
    }

    public Buku getIdbuku() {
        return idbuku;
    }

    public void setIdbuku(Buku idbuku) {
        this.idbuku = idbuku;
    }

    public String getTanggalpinjam() {
        return tanggalpinjam;
    }

    public void setTanggalPinjam(LocalDate tanggal) {
        this.tanggalpinjam = tanggal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getTanggalkembali() {
        return tanggalkembali;
    }

    public void setTanggalKembali(LocalDate tanggal) {
        this.tanggalkembali = tanggal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Peminjaman getById(int id) {
        Peminjaman pj = new Peminjaman();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + " pj.idpeminjaman AS idpeminjaman, "
                + " agt.idanggota AS idanggota, "
                + " agt.nama AS nama, "
                + " agt.alamat AS alamat, "
                + " agt.telepon AS telepon, "
                + " b.idbuku AS idbuku, "
                + " b.judul AS judul, "
                + " pj.tanggalpinjam AS tanggalpinjam, "
                + " pj.tanggalkembali AS tanggalkembali "
                + " FROM peminjaman pj "
                + " JOIN anggota agt ON pj.idanggota = agt.idanggota "
                + " JOIN buku b ON pj.idbuku = b.idbuku "
                + " WHERE pj.idpeminjaman = '" + id + "'");

        try {
            while (rs.next()) {
                pj = new Peminjaman();
                pj.setIdpeminjaman(rs.getInt("idbuku"));
                pj.getIdanggota().setIdanggota(rs.getInt("idanggota"));
                pj.getIdanggota().setNama(rs.getString("nama"));
                pj.getIdanggota().setAlamat(rs.getString("alamat"));
                pj.getIdanggota().setTelepon(rs.getString("telepon"));
                pj.getIdbuku().setIdbuku(rs.getInt("idbuku"));
                pj.getIdbuku().setJudul(rs.getString("judul"));
                pj.setTanggalPinjam(rs.getDate("tanggalpinjam").toLocalDate());
                pj.setTanggalKembali(rs.getDate("tanggalkembali").toLocalDate());

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return pj;
    }

    public ArrayList<Peminjaman> getAll() {
        ArrayList<Peminjaman> listPj = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + " pj.idpeminjaman AS idpeminjaman, "
                + " agt.idanggota AS idanggota, "
                + " agt.nama AS nama, "
                + " agt.alamat AS alamat, "
                + " agt.telepon AS telepon, "
                + " b.idbuku AS idbuku, "
                + " b.judul AS judul, "
                + " pj.tanggalpinjam AS tanggalpinjam, "
                + " pj.tanggalkembali AS tanggalkembali "
                + " FROM peminjaman pj "
                + " JOIN anggota agt ON pj.idanggota = agt.idanggota "
                + " JOIN buku b ON pj.idbuku = b.idbuku ");

        try {
            while (rs.next()) {
                Peminjaman pj = new Peminjaman();
                pj.setIdpeminjaman(rs.getInt("idpeminjaman"));
                pj.getIdanggota().setIdanggota(rs.getInt("idanggota"));
                pj.getIdanggota().setNama(rs.getString("nama"));
                pj.getIdanggota().setAlamat(rs.getString("alamat"));
                pj.getIdanggota().setTelepon(rs.getString("telepon"));
                pj.getIdbuku().setIdbuku(rs.getInt("idbuku"));
                pj.getIdbuku().setJudul(rs.getString("judul"));
                pj.setTanggalPinjam(rs.getDate("tanggalpinjam").toLocalDate());
                pj.setTanggalKembali(rs.getDate("tanggalkembali").toLocalDate());
                
                listPj.add(pj);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listPj;
    }

    public ArrayList<Peminjaman> search(String keyword) {
        ArrayList<Peminjaman> listPj = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + " pj.idpeminjaman AS idpeminjaman, "
                + " agt.idanggota AS idanggota, "
                + " agt.nama AS nama, "
                + " agt.alamat AS alamat, "
                + " agt.telepon AS telepon, "
                + " b.idbuku AS idbuku, "
                + " b.judul AS judul, "
                + " pj.tanggalpinjam AS tanggalpinjam, "
                + " pj.tanggalkembali AS tanggalkembali "
                + " FROM peminjaman pj "
                + " JOIN anggota agt ON pj.idanggota = agt.idanggota "
                + " JOIN buku b ON pj.idbuku = b.idbuku "
                + " WHERE b.judul LIKE '%" + keyword + "%' "
                + " OR agt.nama LIKE '%" + keyword + "%' "
                + " OR agt.alamat LIKE '%" + keyword + "%' "
                + " OR agt.telepon LIKE '%" + keyword + "%' "
                + " OR pj.tanggalpinjam LIKE '%" + keyword + "%' "
                + " OR pj.tanggalkembali LIKE '%" + keyword + "%' ");
        try {
            while (rs.next()) {
                Peminjaman pj = new Peminjaman();
                pj.setIdpeminjaman(rs.getInt("idbuku"));
                pj.getIdanggota().setIdanggota(rs.getInt("idanggota"));
                pj.getIdanggota().setNama(rs.getString("nama"));
                pj.getIdanggota().setAlamat(rs.getString("alamat"));
                pj.getIdanggota().setTelepon(rs.getString("telepon"));
                pj.getIdbuku().setIdbuku(rs.getInt("idbuku"));
                pj.getIdbuku().setJudul(rs.getString("judul"));
                pj.setTanggalPinjam(rs.getDate("tanggalpinjam").toLocalDate());
                pj.setTanggalKembali(rs.getDate("tanggalkembali").toLocalDate());

                listPj.add(pj);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listPj;

    }

    public void save() {
        if (getById(idpeminjaman).getIdpeminjaman() == 0) {
            String SQL = "INSERT INTO peminjaman (idanggota, idbuku, tanggalpinjam, tanggalkembali) VALUES("
                    + "'" + this.getIdanggota().getIdanggota() + "', "
                    + "'" + this.getIdbuku().getIdbuku() + "', "
                    + "'" + this.tanggalpinjam + "', "
                    + "'" + this.tanggalkembali + "' "
                    + ")";
            this.idpeminjaman = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE peminjaman SET "
                    + " idanggota = '" + this.getIdanggota().getIdanggota() + "', "
                    + " idbuku = '" + this.getIdbuku().getIdbuku() + "', "
                    + " tanggalpinjam = '" + this.tanggalpinjam + "', "
                    + " tanggalkembali = '" + this.tanggalpinjam + "' "
                    + " WHERE idpeminjaman = '" + this.idpeminjaman + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    public void delete() {
        String SQL = "DELETE FROM peminjaman WHERE idpeminjaman = '" + this.idpeminjaman + "'";
        DBHelper.executeQuery(SQL);
    }
}
