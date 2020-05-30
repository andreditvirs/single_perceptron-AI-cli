import java.util.Scanner;

public class Manual {
    private double meu;
    private double[] weight;
    private int endEpoch;

    public Manual(double meu) {
        this.meu = meu;

        NLP nlp = new NLP();
        weight = nlp.getWeight();
        iterasi(nlp);
        printHasil(nlp);
    }

    public void iterasi(NLP nlp) {
        // SUMMATION
        double sum = 0;
        int[] input = new int[nlp.getJumlahInput()]; // array input/inputSequence
        int jwb = 1;
        int c_epoch = 1;
        Scanner sc = new Scanner(System.in); // Scanner untuk inputan

        while (jwb == 1) {
            int c_err = 0; // Counter untuk error
            System.out.print("\n(EPOCH ke - " + c_epoch+")");
            for (int i = 0; i < nlp.getTarget().length; i++) {// Satu EPOCH 4 data
                // Input pertama selalu satu (1)
                System.out.println("\nDATA ke - " + (i+1));
                input[0] = 1;
                System.out.println("Input ke 1 selalu 1");
                System.out.print("Masukkan input ke 2 = ");
                input[1] = sc.nextInt();
                System.out.print("Masukkan input ke 3 = ");
                input[2] = sc.nextInt();

                for (int j = 0; j < nlp.getJumlahInput(); j++) {
                    sum += input[j] * nlp.getWeight()[j];
                }

                // Mencari nilai output sesuai rumus stept()/sign()/sigmoid()
                int out = nlp.step(sum);
                System.out.println("============================");
                System.out.println("Output saat ini = " + out);
                System.out.println("Output yang diharapkan= " + nlp.getTarget()[i]);

                if (out != nlp.getTarget()[i]) { // Jika satu bearti masih error
                    System.out.println("ERROR");
                    c_err++;
                    double err = nlp.getTarget()[i] - out;
                    if (err != 0) {
                        for (int j = 0; j < nlp.getJumlahInput(); j++) {
                            weight[j] = weight[j] + meu * input[j] * err;
                            System.out.println("Weight " + (j + 1) + " berubah ke " + weight[j]);
                        }
                    }
                }
            }
            System.out.println("-> Pada EPOCH ini ada " + c_err + " error");
            if (c_err == 0) {
                endEpoch = c_epoch;
                break;
            }
            System.out.print("Lanjut ke epoch berikutnya? (1 untuk lanjut/0 untuk tidak lanjut) = ");
            while (sc.hasNext()) {
                if (sc.hasNextInt()) {
                    jwb = sc.nextInt();
                    break;
                }
            }
            System.out.println();
            endEpoch = c_epoch;
            c_epoch++;
        }
    }

    public void printHasil(NLP nlp) {
        System.out.println();
        System.out.println("Hasil Akhir");
        for (int i = 0; i < nlp.getJumlahInput(); i++) {
            System.out.println("-> WEIGHT " + (i + 1) + " = " + weight[i]);
        }
        System.out.println("Data terakhir pada EPOCH ke - " + endEpoch);
    }
}
