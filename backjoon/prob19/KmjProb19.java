package prob19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KmjProb19 {
    private static int N;
    private static int[] guilty;
    private static int[][] R;
    private static int eunjin;

    private static boolean[] isDead;
    private static int maxNightCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        guilty = new int[N];
        for (int i = 0; i < N; i++) {
            guilty[i] = Integer.parseInt(st.nextToken());
        }
        R = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        eunjin = Integer.parseInt(br.readLine());

        isDead = new boolean[N];
        playMafia(N, 0);

        System.out.print(maxNightCnt);
        br.close();
    }

    private static void playMafia(int remainCnt, int nightCnt) {
        if (isDead[eunjin]) {
            maxNightCnt = Math.max(maxNightCnt, nightCnt);
            return;
        }

        if (remainCnt == 1) {
            maxNightCnt = Math.max(maxNightCnt, nightCnt);
            return;
        }

        if (remainCnt % 2 == 0) {

            for (int i = 0; i < N; i++) {
                if (!isDead[i] && i != eunjin) {
                    isDead[i] = true;
                    for (int j = 0; j < N; j++) {
                        if (!isDead[j]) guilty[j] += R[i][j];
                    }

                    playMafia(remainCnt - 1, nightCnt + 1);

                    for (int j = 0; j < N; j++) {
                        if (!isDead[j]) guilty[j] -= R[i][j];
                    }
                    isDead[i] = false;
                }
            }
        }
        else {
            int killIdx = -1;
            int maxG = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                if (!isDead[i] && guilty[i] > maxG) {
                    maxG = guilty[i];
                    killIdx = i;
                }
            }
            isDead[killIdx] = true;
            playMafia(remainCnt - 1, nightCnt);
            isDead[killIdx] = false;
        }
    }
}