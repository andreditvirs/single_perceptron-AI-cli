import java.util.Random;

public class NLP {

    private int jumlahInput = 3; // Jumlah input/bobot array contoh = {1, (0, 0)}, {1, (0, 1)}, ... yang ada dalam kurung biasa dimasukkan user
    public double[] weight = new double[jumlahInput]; // array weight
    private int[] target = {0, 0, 0, 1};

    public int getJumlahInput(){
        return jumlahInput;
    }

    public int[] getTarget(){
        return target;
    }

    public double[] getWeight(){
        for (int i = 0; i < jumlahInput; i++) {
            weight[i] = generateRandom(-1, 1);
        }
        return weight;
    }

    public double generateRandom(double min, double max) {
        Random r = new Random();
        return (r.nextInt((int) ((max - min) * 10 + 1)) + min * 10) / 10.0;
    }

    public int step(double x){
        if (x >= 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
