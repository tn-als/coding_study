package prob16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DayProb16 {

    private static int N;
    private static boolean[] col;
    private static boolean[] diag1;   //
    private static boolean[] diag2;

    public static void main(String[] argv) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        col = new boolean[N];
        diag1 = new boolean[2*N-1];
        diag2 = new boolean[2*N-1];

        System.out.println(dfs(0));

    }

    public static int dfs(int depth){
        if (depth >= N) {return 1;}

        int incount = 0;
        for (int i = 0; i < N; i++){
            int d1 = depth + i;
            int d2 = depth - i + (N - 1);
            if (col[i] || diag1[d1] || diag2[d2]) {continue;}

            col[i] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            incount += dfs(depth+1);

            col[i] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
        return incount;
    }

}
