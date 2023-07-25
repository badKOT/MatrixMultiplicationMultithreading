import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Multiplication {

    static int[][] m1;
    static int[][] m2;
    static int[][] m3;
    static class Worker implements Runnable {
        int i;
        public Worker(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    m3[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
    }

    public Multiplication(Matrix a, Matrix b) {
        m1 = a.getMatr();
        m2 = b.getMatr();
        m3 = new int[a.getNumOfRows()][b.getNumOfCols()];
        for (int[] ints : m3) Arrays.fill(ints, 0);
    }
    public Multiplication() {
        Random rand = new Random();
        int rows1 = rand.nextInt(3, 8);
        int cols1 = rand.nextInt(3, 8);
        int cols2 = rand.nextInt(3, 8);

        Matrix a = new Matrix(rows1, cols1);
        a.fillInRand();
        m1 = a.getMatr();
        int rows = a.getNumOfCols();

        a = new Matrix(cols1, cols2);
        a.fillInRand();
        m2 = a.getMatr();

        m3 = new int[rows][a.getNumOfCols()];

        for (int[] ints : m3) Arrays.fill(ints, 0);
    }

    public static void main(String[] args) {
        final int MAX_THREADS = 4;

        int[] d = Matrix.generateDimensions(900, 1000);
        Matrix a = new Matrix(d[0], d[1]);
        Matrix b = new Matrix(d[1], d[2]);
        a.fillInRand();
        b.fillInRand();
        Multiplication mul = new Multiplication(a, b);

        int start = (int) System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
        for (int i = 0; i < a.getNumOfRows(); i++) {
            executor.submit(new Multiplication.Worker(i));
        }
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int end = (int) System.currentTimeMillis();

        System.out.println("Multithreading calculations completed!");
        System.out.println(end - start);
//        new Matrix(m3).print();

        start = (int) System.currentTimeMillis();
        Matrix res = Matrix.multiply(a, b);
        end = (int) System.currentTimeMillis();
        System.out.println("Traditional calculations completed!");
//        res.print();
        System.out.println(end - start);
//        System.out.println(Matrix.matricesAreEqual(new Matrix(m3), res));
    }
}
