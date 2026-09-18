package prob6;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class KsmProb6 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 값 저장할 배열
    int[][] dp;

    private void LCS() throws IOException {
        String s1 = br.readLine();
        String s2 = br.readLine();
        dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                // 일치하는 값이 있으면 현재 길이 -1 위치에 해당하는 값 (이전 문자열) 에 1을 더해서 저장한다.
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // 일치하는 값이 없으면 현재 위치에서 문자 하나를 제외했을 때 최댓값을 가져온다.
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        bw.write(String.valueOf(dp[s1.length()][s2.length()]));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        KsmProb6 main = new KsmProb6();
        main.LCS();
    }
}
