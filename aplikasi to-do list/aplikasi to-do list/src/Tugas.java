// Tugas.java
import java.io.Serializable; // Diperlukan untuk menyimpan objek ke file

public class Tugas implements Serializable {
    private final String deskripsi;
    private boolean selesai;

    public Tugas(String deskripsi) {
        this.deskripsi = deskripsi;
        this.selesai = false; // Tugas baru secara default belum selesai
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public boolean isSelesai() {
        return selesai;
    }

    public void tandaiSelesai() {
        this.selesai = true;
    }

    // Metode toString untuk representasi teks tugas
    @Override
    public String toString() {
        return (selesai ? "[Selesai] " : "[Belum Selesai] ") + deskripsi;
    }
}