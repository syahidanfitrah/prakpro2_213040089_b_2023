/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7;

/**
 *
 * @author Rudhox
 */

import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

public class TableModel extends AbstractTableModel {
    // Mendefinisikan nama-nama kolom
    private String[] columnNames = {"Nama", "Nomor HP", "Jenis Kelamin", "Alamat"};
    // Data tabel disimpan dalam ArrayList dari ArrayList String
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    // Mengembalikan jumlah kolom dalam tabel
    public int getColumnCount() {
        return columnNames.length;
    }

    // Mengembalikan jumlah baris dalam tabel
    public int getRowCount() {
        return data.size();
    }

    // Mengembalikan nama kolom pada indeks
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Mengembalikan nilai pada sel yang ditentukan
    public Object getValueAt(int row, int col) {
        List<String> rowItem = data.get(row);
        return rowItem.get(col);
    }

    // Mendefinisikan sel-sel tabel
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ArrayList<String> row = data.get(rowIndex);
        row.set(columnIndex, (String) aValue);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    // Menambahkan baris data baru
    public void add(ArrayList<String> value) {
        data.add(value);

    // Event notifikasi baris baru telah ditambahkan
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Mengubah baris dat
    public void update(ArrayList<String> newValue, int row) {
        data.set(row, newValue);
        fireTableRowsUpdated(row, row);
    }

    // Menghapus baris data
    public void delete(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }
}
