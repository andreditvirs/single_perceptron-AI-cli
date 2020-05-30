import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Deklarasi semua var yang dibutuhkan
        Scanner sc = new Scanner(System.in); // Scanner untuk inputan
        System.out.println("MENU\n 1. Jika ingin mengetik input secara manual.\n 2. Jika ingin otomatis");
        System.out.print("Pilih = ");
        int menu = sc.nextInt();

        // Pilih menu
        if (menu == 1) { // Manual
            // Masukkan meu
            System.out.print("Masukkan meu (Learning Rate) = ");
            double meu = sc.nextDouble();
            new Manual(meu);
        } else if (menu == 2) {
            // Masukkan meu
            System.out.print("Masukkan meu (Learning Rate) = ");
            double meu = sc.nextDouble();
            // Masukkan jumlah iterasi
            System.out.print("Masukkan jumlah iterasi = ");
            int jumlahIterasi = sc.nextInt();
            new Auto(jumlahIterasi, meu);
        } else {
            System.out.print("Silahkan mengulang kembali program");
        }
    }
}