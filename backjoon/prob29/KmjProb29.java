package prob29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KmjProb29 {
    private static int[][] grid;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }

        cnt(0, 0, N, N);
        System.out.println(sb);
    }

    private static void cnt (int startX, int startY, int endX, int endY) {
        if (!checkZero(startX, startY, endX, endY)) {
            sb.append('(');
            int half = (endX - startX) / 2;
            cnt(startX, startY, startX + half, startY + half);
            cnt(startX, startY + half, startX + half, endY);
            cnt(startX + half, startY, endX, startY + half);
            cnt(startX + half, startY + half, endX, endY);
            sb.append(')');
        } else {
            sb.append(grid[startX][startY]);
        }
    }

    private static boolean checkZero(int startX, int startY, int endX, int endY) {
        int first = grid[startX][startY];
        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                if (grid[i][j] != first) return false;
            }
        }
        return true;
    }

}
