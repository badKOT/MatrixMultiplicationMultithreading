import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Matrix {
    private int[][] matr;

    public Matrix(int[][] matr) {
        this.matr = matr;
    }
    public Matrix() {

    }
    public Matrix(int rows, int cols) {
        this.matr = new int[rows][cols];
    }

    public void fillIn() {
        Scanner sc = new Scanner(System.in);
        if (matr == null) {
            System.out.print("Enter the number of rows: ");
            int rows = sc.nextInt();
            System.out.print("Enter the number of columns: ");
            int cols = sc.nextInt();
            this.matr = new int[rows][cols];
        }

        for (int i = 0; i < matr.length; i++) {
            System.out.println("Row " + (i+1) + ":");
            for (int j = 0; j < matr[0].length; j++) {
                matr[i][j] = sc.nextInt();
            }
            System.out.println("Row " + (i+1) + " is full.");
        }
        System.out.println("Your matrix is:");
        this.print();
    }
    public void fillInRand() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        if (matr == null) {
            System.out.print("Enter the number of rows or -1 if you want it to be generated: ");
            int m = sc.nextInt();
            if (m == -1) m = rand.nextInt(5, 20);
            System.out.print("Enter the number of columns or -1 if you want it to be generated: ");
            int n = sc.nextInt();
            if (n == -1) n = rand.nextInt(5, 20);
            this.matr = new int[m][n];
        }


        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[0].length; j++) {
                matr[i][j] = rand.nextInt(100);
            }
        }
//        System.out.println("Your matrix is:");
//        this.print();
    }
    public void print() {
        for (int i = 0; i < matr.length; i++) { //rows
            for (int j = 0; j < matr[0].length; j++) { //columns
                System.out.print(matr[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
    public int getNumOfRows() { return this.matr.length; }
    public int getNumOfCols() { return this.matr[0].length; }
    public int[][] getMatr() { return this.matr; }
    public static int[] generateDimensions(int origin, int bound) {
        Random rand = new Random();
        int rows1 = rand.nextInt(origin, bound);
        int cols1 = rand.nextInt(origin, bound);
        int cols2 = rand.nextInt(origin, bound);
        return new int[]{rows1, cols1, cols2};
    }
    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix res = new Matrix(a.getNumOfRows(), b.getNumOfCols());
        for (int[] ints : res.matr) Arrays.fill(ints, 0);

        for (int i = 0; i < a.getNumOfRows(); i++) {
            for (int j = 0; j < b.getNumOfCols(); j++) {
                for (int k = 0; k < b.getNumOfRows(); k++) {
                    res.matr[i][j] += a.matr[i][k] * b.matr[k][j];
                }
            }
        }
        return res;
    }
    public static boolean matricesAreEqual(Matrix a, Matrix b) {
        if (a.getNumOfRows() != b.getNumOfRows() ||
                a.getNumOfCols() != b.getNumOfCols()) return false;
        for (int i = 0; i < a.getNumOfRows(); i++)
            if (!Arrays.equals(a.matr[i], b.matr[i])) return false;

        return true;
    }
}
