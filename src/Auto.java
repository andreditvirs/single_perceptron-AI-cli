public class Auto{
    private int iterasi;
    private double meu;
    private int[][] inputSequence = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};
    private double[] weight;
    private int endEpoch;

    public Auto(int iterasi, double meu) {
        this.iterasi = iterasi;
        this.meu = meu;

        NLP nlp = new NLP();
        weight = nlp.getWeight();
        iterasi(nlp);
        printHasil(nlp);
    }

    public void iterasi(NLP nlp){
        // SUMMATION
        double sum = 0;
        int[] input = new int[nlp.getJumlahInput()]; // array input/inputSequence
        for (int itr = 1; itr <= iterasi; itr++) {
            System.out.println("\n(EPOCH ke - " + itr + ")");
            int c_err = 0;
            for (int i = 0; i < nlp.getTarget().length; i++) {
                for (int k = 0; k < nlp.getJumlahInput(); k++){
                    input[k] = inputSequence[i][k];
                }
                for (int j = 0; j < nlp.getJumlahInput(); j++) {
                    sum += input[j] * nlp.getWeight()[j];
                }

                // Mencari nilai output sesuai rumus stept()/sign()/sigmoid()
                int out = nlp.step(sum);
                System.out.println("============================");
                System.out.println("Output saat ini = "+out);
                System.out.println("Output yang diharapkan= "+nlp.getTarget()[i]);

                if (out != nlp.getTarget()[i]) { // Jika satu bearti masih error
                    System.out.println("ERROR");
                    c_err++;
                    double err = nlp.getTarget()[i] - out;
                    if (err != 0) {
                        for (int j = 0; j < nlp.getJumlahInput(); j++) {
                            weight[j] = weight[j] + meu * input[j] * err;
                            System.out.println("Weight "+ (j+1) +" berubah ke " + weight[j]);
                        }
                    }
                }
            }
            System.out.println("-> Pada EPOCH ini ada " + c_err + " error");
            if (c_err == 0) {
                endEpoch = itr;
                break;
            }
            endEpoch = itr;
        }
    }

    public void printHasil(NLP nlp){
        System.out.println();
        System.out.println("Hasil Akhir");
        for (int i = 0; i < nlp.getJumlahInput(); i++) {
            System.out.println("-> WEIGHT "+ (i+1) +" = "+ weight[i]);
        }
        System.out.println("Data sesuai target pada EPOCH ke - "+ endEpoch);
    }
}
