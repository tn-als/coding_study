package prob8;

import java.io.*;
import java.util.StringTokenizer;

public class KsmProb8 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private void RGB() throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[][] houses = new int[n][3];
        int[][] dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                houses[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if(i==0){
                    dp[i][j] = houses[i][j];
                    continue;
                }

                int before1=-1;
                int before2=-1;
                for(int k=0;k<3;k++){
                    if(k==j) continue;
                    if(before1==-1){
                        before1=dp[i-1][k];
                    }else before2=dp[i-1][k];
                }

                dp[i][j] = houses[i][j] + Math.min(before1,before2);
            }
        }

        int min = Math.min(dp[n-1][0], dp[n-1][1]);
        min = Math.min(min, dp[n-1][2]);
        bw.write(min+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        KsmProb8 main = new KsmProb8();
        main.RGB();
    }
}
