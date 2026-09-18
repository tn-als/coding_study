package prob18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayProb18 {

    private static int N;
    private static int M;
    private static int H;
    private static int[][] garo;
    private static int answer;

    public static void main (String[] argv) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        garo = new int[H][N];

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int hori = Integer.parseInt(st.nextToken()) - 1;
            int verti = Integer.parseInt(st.nextToken()) - 1;
            garo[hori][verti] = 1;
            garo[hori][verti + 1] = -1;
        }

        answer = 4;
        for (int i = 0; i <= 3; i++){
            makeLadder(0,0, i);
            if (answer == i) {break;}
        }
        if (answer == 4){answer = -1;}
        System.out.print(answer);

    }

    private static void makeLadder (int count, int idx, int maxcnt) {
        if (count == maxcnt) {
            if (playLadder()) {
                answer = count;
            }
            return;
        }

        for (int i = idx; i < H*(N-1); i++){
            int row = i / (N-1);
            int col = i % (N-1);

            if (garo[row][col] == 0 && garo[row][col+1] == 0){
                garo[row][col] = 1;
                garo[row][col+1] = -1;
                makeLadder(count + 1, i+1, maxcnt);
                if (answer != 4) {return;}
                garo[row][col] = 0;
                garo[row][col+1] = 0;
            }
        }
    }

    private static boolean playLadder() {
        for (int i = 0; i < N; i++){
            int now = i;
            for (int j = 0; j < H; j++){
                now += garo[j][now];
            }
            if (now != i) {return false;}
        }
        return true;
    }

}
