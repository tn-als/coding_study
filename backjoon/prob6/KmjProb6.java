package prob6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

public class KmjProb6 {
    private static Integer[][] dp;
    private static String a;
    private static String b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();
        dp = new Integer[a.length()][b.length()];
        System.out.print(cntLCS(a.length() - 1, b.length() - 1));
        br.close();
    }

    private static int cntLCS(int x, int y) {
        if (x < 0 || y < 0) return 0;
        if (dp[x][y] == null) {
            dp[x][y] = a.charAt(x) == b.charAt(y) ? cntLCS(x - 1, y - 1) + 1 : Math.max(cntLCS(x - 1, y), cntLCS(x, y - 1));
        }
        return dp[x][y];
    }
}
