package prob15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayProb15 {

    private static int N;
    private static float[][] slopes;

    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[] buildings = new int[N];
        slopes = new float[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (i == j) {continue;}
                slopes[i][j] = (float) (buildings[j] - buildings[i]) /Math.abs(j - i);
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++){
            int count = calCnt(i);
            if (max < count) {max = count;}
        }

        System.out.print(max);
    }

    private static int calCnt(int start){
        int count = 0;
        float prevSlope = -Float.MAX_VALUE;
        for (int i = start - 1; i >= 0; i--){
            float nowSlope = slopes[start][i];
            if (prevSlope < nowSlope){
                count ++;
                prevSlope = nowSlope;
            }
        }
        prevSlope = -Float.MAX_VALUE;
        for (int i = start + 1; i < N; i++){
            float nowSlope = slopes[start][i];
            if (prevSlope < nowSlope){
                count ++;
                prevSlope = nowSlope;
            }
        }
        return count;
    }
}
