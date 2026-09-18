package prob30;

import java.util.Scanner;

public class KmjProb30 {
    private static long[] dp = new long[51];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();

        dp[0] = 3;
        int idx = 0;
        while (dp[idx] < N) {
            idx++;
            dp[idx] = 2 * dp[idx - 1] + idx + 3;
        }

        System.out.println(find(idx, N));
    }

    private static char find(int idx, long N) {
        if (idx == 0) return "moo".charAt((int) N - 1);

        if (N <= dp[idx - 1]) return find(idx - 1, N);
        else if (N > dp[idx - 1] + idx + 3) return find(idx - 1, N - dp[idx - 1] - idx - 3);
        else {
            if (N - dp[idx - 1] == 1) return 'm';
            else return 'o';
        }
    }
}
