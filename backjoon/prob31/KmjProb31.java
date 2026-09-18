package prob31;


import java.util.Scanner;

public class KmjProb31 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int base = 0;
        while (N >= 0) {
            N--;
            int subLen = (int) Math.pow(2, N);
            if (r + 1 <= subLen && c + 1 > subLen) {
                base += (int) Math.pow(subLen, 2);
                c -= subLen;
            }
            else if (r + 1 > subLen && c + 1 <= subLen) {
                base += (int) Math.pow(subLen, 2) * 2;
                r -= subLen;
            }
            else if (r + 1 > subLen && c + 1 > subLen) {
                base += (int) Math.pow(subLen, 2) * 3;
                r -= subLen;
                c -= subLen;
            }
        }
        System.out.print(base);
        sc.close();
    }
}
