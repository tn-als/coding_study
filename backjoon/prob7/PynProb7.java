package prob7;

import java.util.Arrays;
import java.util.Scanner;

public class PynProb7 {
    /*
     * 설탕 배달
     * dp[i] : 무게 i까지의 최소봉지개수
     *
     * dp[i] = Min(dp[i-3]+1, dp[i-5]+1)
     * */

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[5001];
        Arrays.fill(dp, -1);

        dp[3] = dp[5] = 1;

        for(int i=6; i<=N; i++) {
            if(dp[i-3] == -1 && dp[i-5] == -1) {
                continue;
            }
            if(dp[i-3] == -1) {
                dp[i] = dp[i-5]+1;
                continue;
            }
            if(dp[i-5] == -1) {
                dp[i] = dp[i-3]+1;
                continue;
            }
            dp[i] = Math.min(dp[i-3]+1, dp[i-5]+1);

        }



        System.out.println(dp[N]);
    }
}