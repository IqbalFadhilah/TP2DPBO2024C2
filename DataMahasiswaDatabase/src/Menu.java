import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu extends JFrame{

    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.setVisible(true);
        // tampilkan window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // agar program ikut berhenti saat window diclose
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;
    private Database database;
    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JComboBox agamaComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel agamaLabel;


    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        database = new Database();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        String[] agama = {"", "Islam", "Kristen", "Katolik", "Hindu", "Buddha", "Konghucu"};
        agamaComboBox.setModel(new DefaultComboBoxModel(agama));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex == -1){
                    insertData();
                } else{
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex >= 0){
                    int option = JOptionPane.showConfirmDialog(null, "Hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        deleteData();
                    }
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedAgama = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                agamaComboBox.setSelectedItem(selectedAgama);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Agama"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");

            int i = 0;
            while(resultSet.next()){
                Object[] row = new Object[5];

                row[0] = i + 1;
                row[1] = resultSet.getString(2);
                row[2] = resultSet.getString(3);
                row[3] = resultSet.getString(4);
                row[4] = resultSet.getString(5);

                temp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return temp; // return juga harus diganti
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String agama = agamaComboBox.getSelectedItem().toString();

        // Check for empty fields
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || agama.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Check for existing NIM in the database
            ResultSet existingNIM = database.selectQuery("SELECT * FROM mahasiswa WHERE nim = '" + nim + "'");
            if (existingNIM.next()) {
                JOptionPane.showMessageDialog(null, "NIM sudah ada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e) {
            // Handle SQL exception
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO mahasiswa VALUES (null, '" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + agama + "')";
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert data berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }


    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String agama = agamaComboBox.getSelectedItem().toString();

        // Check empty fields
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || agama.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // update data mahasiswa di list
        for (Mahasiswa mhs : listMahasiswa) {
            if (mhs.getNim().equals(nim)) {
                mhs.setNama(nama);
                mhs.setJenisKelamin(jenisKelamin);
                mhs.setAgama(agama);
            }
        }

        String sql = "UPDATE mahasiswa SET nama = '" + nama + "', jenis_kelamin = '" + jenisKelamin + "', agama = '" + agama + "' WHERE nim = '" + nim + "'";
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Update Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
    }

    public void deleteData() {
        // ambil data nim yang akan dihapus
        String nim = nimField.getText();

        String sql = "DELETE FROM mahasiswa WHERE nim = '" + nim + "'";
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Delete Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }


    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        agamaComboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }
}
