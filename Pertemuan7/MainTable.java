/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7;

/**
 *
 * @author Rudhox
 */

//import Pertemuan7.table.TableModel;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;


public class MainTable extends JFrame {
    private void simpanFile(ArrayList<ArrayList<String>> data) {
        // Menampilkan pesan konfirmasi
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menyimpan file '.txt' ?",
        "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if(konfirmasi == JOptionPane.YES_OPTION) {
            try {
        // lokasi file
                BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));
            
                for(ArrayList<String> row : data) {
                    for (String s : row) {
                        writer.write(s + "\t");
                    }
        // Memindahkan data ke baris baru
                    writer.newLine();
                }
                writer.close();
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke file");
            }
            catch(IOException e) {
                JOptionPane.showMessageDialog(null, "Data gagal disimpan ke file");
                e.printStackTrace();
            }
        }
    }

    public MainTable() {
        // Mengatur layout 
        this.setLayout(null);

        // Label Nama
        JLabel labelNama = new JLabel("Nama: ");
        labelNama.setBounds(15, 40, 100, 10);

        // TextField Nama
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        // Label Nomor HP
        JLabel labelNomorHP = new JLabel("Nomor HP: ");
        labelNomorHP.setBounds(15, 100, 100, 10);

        // TextField Nomor HP
        JTextField textFieldNomorHP = new JTextField();
        textFieldNomorHP.setBounds(15, 120, 350, 30);

        // Label Jenis Kelamin
        JLabel labelRadio = new JLabel("Jenis Kelamin: ");
        labelRadio.setBounds(15, 160, 350, 10);

        // RadioButton "Laki-Laki"
        JRadioButton radioButton1 = new JRadioButton("Laki-Laki");
        radioButton1.setBounds(15, 180, 350, 30);

        // RadioButton "Perempuan"
        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 210, 350, 30);

        // Label "Alamat"
        JLabel labelAlamat = new JLabel("Alamat: ");
        labelAlamat.setBounds(15, 240, 350, 30);

        // TextArea "Alamat"
        JTextArea textAreaAlamat = new JTextArea(5, 20);
        textAreaAlamat.setBounds(15, 270, 350, 100);

        // ButtonGroup untuk mengelola RadioButton
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // Button "Simpan"
        JButton buttonAdd = new JButton("Simpan");
        buttonAdd.setBounds(15, 380, 100, 40);

        // Button "Edit"
        JButton buttonUpdate = new JButton("Edit");
        buttonUpdate.setBounds(120, 380, 100, 40);

        // Button "Hapus"
        JButton buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(225, 380, 100, 40);

        // Button "Simpan ke File"
        JButton buttonSave = new JButton("Simpan Ke File");
        buttonSave.setBounds(330, 380, 120, 40);

        // Tabel JTable
        javax.swing.JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 430, 560, 150);

        // Tabel custom (TableModel)
        TableModel tableModel = new TableModel();
        table.setModel(tableModel);

        // ActionListener pada Button "Simpan"
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        // Mengambil data dari inputan Nama, Nomor HP, Jenis Kelamin, dan Alamat
                String jenisKelamin = "";
                String nama = textFieldNama.getText();
                String nomorHP = textFieldNomorHP.getText();
                String alamat = textAreaAlamat.getText();

                if(radioButton1.isSelected() && radioButton2.isSelected()) {
                    JOptionPane.showMessageDialog(MainTable.this, "Hanya satu jenis kelamin yang dipilih!",
                    "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
                else if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                }
                else if(radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }
                else {
        // Notifikasi jika jenis kelamin belum dipilih
                    JOptionPane.showMessageDialog(MainTable.this, "Pilih jenis kelamin terlebih dahulu!",
                    "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    return;
                }

        // Validasi data kosong
                if(nama.isEmpty() || nomorHP.isEmpty() || alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(MainTable.this, "Semua harus diisi!",
                    "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
                else {
        // Konfirmasi sebelum penyimpanan data
                    int konfirmasi = JOptionPane.showConfirmDialog(MainTable.this, "Apakah anda yakin ingin menyimpan data ?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);

                    if(konfirmasi == JOptionPane.YES_OPTION) {
        // Menambahkan data ke model tabel
                        tableModel.add(new ArrayList<>(Arrays.asList(nama, nomorHP, jenisKelamin, alamat)));
        // Membersihkan table input sesudah penyimpanan
                        textFieldNama.setText("");
                        textFieldNomorHP.setText("");
                        textAreaAlamat.setText("");
                        labelRadio.setText("Jenis Kelamin: ");
                    }
                }
            }
        });

        // Menambahkan ActionListener pada Button "Edit"
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row == -1) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang ingin diubah!");
                    return;
                }

        // Mengambil data dari inputan Nama, Nomor HP, Jenis Kelamin, dan Alamat
                String jenisKelamin = "";
                String nama = textFieldNama.getText();
                String nomorHP = textFieldNomorHP.getText();
                String alamat = textAreaAlamat.getText();

                if(radioButton1.isSelected() && radioButton2.isSelected()) {
                    JOptionPane.showMessageDialog(MainTable.this, "Hanya satu jenis kelamin yang dipilih!",
                            "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
                else if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                }
                else if(radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }
                else {
        // Notifikasi jika jenis kelamin belum dipilih
                    JOptionPane.showMessageDialog(MainTable.this, "Pilih jenis kelamin terlebih dahulu!",
                            "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    return;
                }

        // Validasi data kosong
                if(nama.isEmpty() || nomorHP.isEmpty() || alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(MainTable.this, "Semua harus diisi!",
                            "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
                else {
        // Konfirmasi sebelum pengubahan data
                    int konfirmasi = JOptionPane.showConfirmDialog(MainTable.this, "Apakah anda yakin ingin mengubah data ?",
                            "Konfirmasi", JOptionPane.YES_NO_OPTION);

                    if(konfirmasi == JOptionPane.YES_OPTION) {
        // Menambahkan data ke tabel
                        ArrayList<String> data = new ArrayList<String>(Arrays.asList(nama, nomorHP, jenisKelamin, alamat));
                        tableModel.update(data, row);

        // Membersihkan table input sesudah penyimpanan
                        textFieldNama.setText("");
                        textFieldNomorHP.setText("");
                        textAreaAlamat.setText("");
                        labelRadio.setText("Jenis Kelamin: ");
                    }
                }
            }
        });

        // Menambahkan ActionListener pada tombol "Hapus"
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow >= 0) {
                    int option = JOptionPane.showConfirmDialog(MainTable.this, "Apakah anda yakin ingin menghapus data ?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    
                    if(option == JOptionPane.YES_OPTION) {
        // Metode deleteRow untuk menghapus isi table
                        tableModel.delete(selectedRow);
                        JOptionPane.showMessageDialog(MainTable.this, "Data berhasil dihapus!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(MainTable.this, "Pilih baris yang ingin dihapus!",
                    "Konfirmasi", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Menambahkan ActionListener pada tombol "Simpan ke File"
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanFile(tableModel.getData());
            }
        });

        // Menghendel penutupan frame
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int konfirmasi = JOptionPane.showConfirmDialog(MainTable.this,
                        "Anda yakin ingin keluar ?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
                if(konfirmasi == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        // Menambahkan komponen-komponen frame
        this.add(buttonAdd);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(buttonSave);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelNomorHP);
        this.add(textFieldNomorHP);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelAlamat);
        this.add(textAreaAlamat);
        this.add(scrollableTable);

        // Ukuran dan tata letak frame
        this.setSize(600, 650);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainTable m = new MainTable();
                m.setVisible(true);
            }
        });
    }
}
