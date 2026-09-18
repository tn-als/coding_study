package prob15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KmjProb15 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int cnt = 0;

            double maxSlope = -Double.MAX_VALUE;
            for (int j = i + 1; j < N; j++) {
                double slope = (double) (heights[j] - heights[i]) / (j - i);
                if (slope > maxSlope) {
                    maxSlope = slope;
                    cnt++;
                }
            }

            maxSlope = Double.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                double slope = (double) (heights[j] - heights[i]) / (j - i);
                if (slope < maxSlope) {
                    maxSlope = slope;
                    cnt++;
                }
            }

            answer = Math.max(answer, cnt);
        }

        System.out.print(answer);
    }
}