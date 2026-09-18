package prob16;

import java.util.Scanner;
import java.lang.Math;

public class KmjProb16 {
    private static int[] arr;
    private static int N;
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        nQueen(0);
        System.out.print(count);
        sc.close();
    }

    private static void nQueen(int col) {
        if (col == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[col] = i;
            if (isValid(col)) {
                nQueen(col + 1);
            }
        }
    }

    private static boolean isValid(int col) {
        for (int i = 0; i < col; i++) {
            if (arr[i] == arr[col] || Math.abs(arr[i] - arr[col]) == Math.abs(i - col)) return false;
        }
        return true;
    }
}
