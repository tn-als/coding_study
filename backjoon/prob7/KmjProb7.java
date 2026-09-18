package prob7;

import java.util.Scanner;
import java.lang.Math;

public class KmjProb7 {
    private static Integer[] dp;
    private static int INT_BIG = 1000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        dp = new Integer[N + 1];
        dp[0] = 0;
        dp[1] = INT_BIG;
        dp[2] = INT_BIG;

        int result = cntMin(N);

        if (result == INT_BIG) System.out.print(-1);
        else System.out.print(cntMin(N));

        sc.close();
    }

    private static int cntMin(int idx) {
        if (idx < 0) return INT_BIG;

        if (dp[idx] == null) {
            int minCnt = Math.min(cntMin(idx - 3), cntMin(idx - 5));
            if (minCnt >= INT_BIG) dp[idx] = minCnt;
            else dp[idx] = minCnt + 1;
        }

        return dp[idx];
    }
}
