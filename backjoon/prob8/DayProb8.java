package prob8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayProb8 {
    public static void main(String[] argv) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int cost[][] = new int[3][N+1];

        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            cost[0][i] = Math.min(cost[1][i-1], cost[2][i-1]) + R;
            cost[1][i] = Math.min(cost[0][i-1], cost[2][i-1]) + G;
            cost[2][i] = Math.min(cost[0][i-1], cost[1][i-1]) + B;
        }

        int result = Math.min(Math.min(cost[0][N],cost[1][N]),cost[2][N]);
        System.out.println(result);
    }
}
