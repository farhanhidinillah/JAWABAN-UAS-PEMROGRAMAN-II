// ManajerTugas.java
import java.io.*;
import java.util.ArrayList;

public class ManajerTugas {
    private ArrayList<Tugas> daftarTugas;
    private static final String NAMA_FILE = "daftar_tugas.txt";

    public ManajerTugas() {
        daftarTugas = new ArrayList<>();
        bacaTugasDariFile(); // Coba baca tugas saat objek dibuat
    }

    public void tambahTugas(String deskripsi) {
        daftarTugas.add(new Tugas(deskripsi));
        System.out.println("Tugas '" + deskripsi + "' berhasil ditambahkan.");
        simpanTugasKeFile();
    }

    public void tandaiTugasSelesai(int indeks) {
        if (indeks >= 0 && indeks < daftarTugas.size()) {
            daftarTugas.get(indeks).tandaiSelesai();
            System.out.println("Tugas ke-" + (indeks + 1) + " ditandai sebagai selesai.");
            simpanTugasKeFile();
        } else {
            System.out.println("Indeks tugas tidak valid.");
        }
    }

    public void hapusTugas(int indeks) {
        if (indeks >= 0 && indeks < daftarTugas.size()) {
            String deskripsiTugas = daftarTugas.remove(indeks).getDeskripsi();
            System.out.println("Tugas '" + deskripsiTugas + "' berhasil dihapus.");
            simpanTugasKeFile();
        } else {
            System.out.println("Indeks tugas tidak valid.");
        }
    }

    public void tampilkanSemuaTugas() {
        if (daftarTugas.isEmpty()) {
            System.out.println("Daftar tugas kosong.");
            return;
        }
        System.out.println("\n--- Daftar Tugas Anda ---");
        for (int i = 0; i < daftarTugas.size(); i++) {
            System.out.println((i + 1) + ". " + daftarTugas.get(i));
        }
        System.out.println("-------------------------\n");
    }

    // Metode untuk menyimpan tugas ke file
    private void simpanTugasKeFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NAMA_FILE))) {
            oos.writeObject(daftarTugas);
            // System.out.println("Tugas berhasil disimpan ke file."); // Bisa di-uncomment untuk debugging
        } catch (IOException e) {
            System.err.println("Gagal menyimpan tugas ke file: " + e.getMessage());
        }
    }

    // Metode untuk membaca tugas dari file
    private void bacaTugasDariFile() {
        File file = new File(NAMA_FILE);
        if (file.exists() && file.length() > 0) { // Pastikan file ada dan tidak kosong
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NAMA_FILE))) {
                daftarTugas = (ArrayList<Tugas>) ois.readObject();
                // System.out.println("Tugas berhasil dibaca dari file."); // Bisa di-uncomment untuk debugging
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Gagal membaca tugas dari file: " + e.getMessage());
                daftarTugas = new ArrayList<>(); // Inisialisasi ulang jika gagal membaca
            }
        } else {
            System.out.println("File tugas belum ada atau kosong. Membuat daftar tugas baru.");
        }
    }

    int getDaftarTugasSize() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}