import java.util.Scanner;

public class AplikasiToDoList {
    private static ManajerTugas manajer;
    private static Scanner scanner;

    public static void main(String[] args) {
        manajer = new ManajerTugas();
        scanner = new Scanner(System.in);
        int pilihan;

        do {
            tampilkanMenu();
            System.out.print("Masukkan pilihan Anda: ");
            pilihan = bacaPilihan();

            switch (pilihan) {
                case 1 -> tambahTugasBaru();
                case 2 -> tandaiTugasSelesai();
                case 3 -> hapusTugas();
                case 4 -> manajer.tampilkanSemuaTugas();
                case 5 -> System.out.println("Terima kasih telah menggunakan aplikasi To-Do List!");
                default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
            System.out.println(); // Baris kosong untuk kerapian
        } while (pilihan != 5);

        scanner.close(); // Tutup scanner saat program berakhir
    }

    private static void tampilkanMenu() {
        System.out.println("===== Aplikasi To-Do List =====");
        System.out.println("1. Tambah Tugas Baru");
        System.out.println("2. Tandai Tugas Selesai");
        System.out.println("3. Hapus Tugas Tertentu");
        System.out.println("4. Tampilkan Semua Tugas");
        System.out.println("5. Keluar");
        System.out.println("===============================");
    }

    private static int bacaPilihan() {
        while (!scanner.hasNextInt()) {
            System.out.println("Input tidak valid. Masukkan angka.");
            scanner.next(); // Buang input yang salah
            System.out.print("Masukkan pilihan Anda: ");
        }
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline yang tersisa setelah nextInt()
        return pilihan;
    }

    private static void tambahTugasBaru() {
        System.out.print("Masukkan deskripsi tugas baru: ");
        String deskripsi = scanner.nextLine();
        if (!deskripsi.trim().isEmpty()) {
            manajer.tambahTugas(deskripsi);
        } else {
            System.out.println("Deskripsi tugas tidak boleh kosong.");
        }
    }

    private static void tandaiTugasSelesai() {
        manajer.tampilkanSemuaTugas(); // Tampilkan daftar tugas agar pengguna bisa memilih
        if (manajer.getDaftarTugasSize() == 0) { // Tambahkan metode helper di ManajerTugas
            return;
        }
        System.out.print("Masukkan nomor tugas yang ingin ditandai selesai: ");
        int indeks = bacaPilihan();
        manajer.tandaiTugasSelesai(indeks - 1); // Indeks di ArrayList dimulai dari 0
    }

    private static void hapusTugas() {
        manajer.tampilkanSemuaTugas(); // Tampilkan daftar tugas agar pengguna bisa memilih
        if (manajer.getDaftarTugasSize() == 0) { // Tambahkan metode helper di ManajerTugas
            return;
        }
        System.out.print("Masukkan nomor tugas yang ingin dihapus: ");
        int indeks = bacaPilihan();
        manajer.hapusTugas(indeks - 1); // Indeks di ArrayList dimulai dari 0
    }
}