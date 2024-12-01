/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package frontend;

import backend.*;

public class TestBackend {

    public static void main(String[] args) {
//        Kategori novel = new Kategori().getById(31);
//        Kategori referensi = new Kategori().getById(32);
//
//        Buku buku1 = new Buku(novel, "Timun Mas", "Elex Media", "Bang Supit");
//        Buku buku2 = new Buku(referensi, "Metode Linier", "Springer", "Alex Baldwin");
//        Buku buku3 = new Buku(novel, "Bintang Terang", "Erlangga", "Mat Sewoot");

        Anggota agt1 = new Anggota().getById(6);
        Anggota agt2 = new Anggota().getById(7);
        Anggota agt3 = new Anggota().getById(9);

        Buku bk1 = new Buku().getById(13);
        Buku bk2 = new Buku().getById(14);
        Buku bk3 = new Buku().getById(15);

        Peminjaman pj1 = new Peminjaman(agt1, bk1, "2024-10-12", "2024-11-12");
        Peminjaman pj2 = new Peminjaman(agt2, bk2, "2024-9-08", "2024-9-15");
        Peminjaman pj3 = new Peminjaman(agt3, bk3, "2024-11-01", "2024-11-27");

        // test insert
        pj1.save();
        pj2.save();
        pj3.save();

        // test update
        pj2.setIdbuku(bk1);
        pj2.save();

        // test delete
        pj2.delete();

        // test select all
        for (Peminjaman pinjam : new Peminjaman().getAll()) {
            System.out.println("Peminjam: " + pinjam.getIdanggota().getNama() + ", Buku: " + pinjam.getIdbuku().getJudul() + ", tgl pinjam: " + pinjam.getTanggalpinjam() + ", tgl pinjam: " + pinjam.getTanggalkembali());
        }

        // test search
        for (Peminjaman pinjam : new Peminjaman().search("timun")) {
            System.out.println("Peminjam: " + pinjam.getIdanggota().getNama() + ", Buku: " + pinjam.getIdbuku().getJudul() + ", tgl pinjam: " + pinjam.getTanggalpinjam() + ", tgl pinjam: " + pinjam.getTanggalkembali());
        }
    }
}
