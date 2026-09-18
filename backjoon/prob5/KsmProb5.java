package prob5;

import java.io.*;

public class KsmProb5 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[][] dp;

    private void fibonacciFun() throws IOException {
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            dp = new int[n + 1][2];
            count(n);
            bw.write(dp[n][0] + " " + dp[n][1] + "\n");

        }
        bw.flush();
        bw.close();
        br.close();
    }

    private void count(int n) {
        for (int i = 0; i <= n; i++) {
            if (i == 0) dp[i][0] = 1;
            else if (i == 1) dp[i][1] = 1;
            else {
                dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
                dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        KsmProb5 main = new KsmProb5();
        main.fibonacciFun();
    }
}
