package prob27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DayProb27 {

    private static int[] dp;

    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[60];
        dp[1] = 5;
        dp[2] = 13;

        int i;
        for (i = 3; i < 60; i++){
            dp[i] = dp[i-1] + dp[i-2] + 1;
            if (dp[i] > N){break;}
        }

        divideConquer(i, N);
    }

    private static void divideConquer(int idx, int target){
        if (idx == 1) {
            System.out.println("Messi".charAt(target - 1));
            return;
        } else if (idx == 2){
            if (target == 6){
                System.out.println("Messi Messi Gimossi");
            } else{
                System.out.println("Messi Gimossi".charAt(target - 1));
            } return;
        }

        if (dp[idx - 1] + 1 == target){
            System.out.println("Messi Messi Gimossi");
        } else if (target <= dp[idx - 1]){
            divideConquer(idx-1, target);
        } else {
            divideConquer(idx-2, target-dp[idx-1]-1);
        }
    }
}
