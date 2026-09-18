package prob8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.Math;

public class KmjProb8 {
    private static Integer[][] table;
    private static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        table = new Integer[n][3];
        dp = new Integer[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
            table[i][2] = Integer.parseInt(st.nextToken());
            if (i == 0) {
                dp[i] = table[i];
            }
        }

        calc(n - 1);

        int result = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
        System.out.print(result);
        br.close();
    }

    private static void calc(int idx) {
        if (dp[idx][0] == null) {
            if (dp[idx - 1][0] == null) calc(idx - 1);
            dp[idx][0] = table[idx][0] + Math.min(dp[idx - 1][1], dp[idx - 1][2]);
            dp[idx][1] = table[idx][1] + Math.min(dp[idx - 1][0], dp[idx - 1][2]);
            dp[idx][2] = table[idx][2] + Math.min(dp[idx - 1][0], dp[idx - 1][1]);
        }
    }

}
