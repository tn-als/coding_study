package prob13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.lang.Math;

public class KmjProb13 {
    private static int N;
    private static int M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int maxSum = -1;

    private static final int[] moveX = {-1, 0, 1, 0};
    private static final int[] moveY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;

                checkT(i, j);
            }
        }

        System.out.print(maxSum);
    }

    private static void dfs(int x, int y, int cnt, int sum) {
        if (cnt == 4) {
            maxSum = Math.max(sum, maxSum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];
            if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && !visited[nextX][nextY]) {
                visited[nextX][nextY] = true;
                dfs(nextX, nextY, cnt + 1, sum + map[nextX][nextY]);
                visited[nextX][nextY] = false;
            }
        }
    }

    private static void checkT(int x, int y) {
        // 현재 칸을 중심으로 상우하좌 네 방향 값 수집
        int center = map[x][y];
        int neighborCount = 0;
        int sumNeighbors = 0;
        int minNeighbor = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int nx = x + moveX[i];
            int ny = y + moveY[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            int v = map[nx][ny];
            sumNeighbors += v;
            minNeighbor = Math.min(minNeighbor, v);
            neighborCount++;
        }

        // 인접 칸이 3개 이상이어야 T자형이 됨
        if (neighborCount < 3) return;

        // 4방향 모두 있으면(즉 neighborCount==4), 하나씩 제외하면서 네 가지 모양 계산
        // 3개만 있으면 무조건 그 세 칸 다 더한 게 유일한 T자형
        if (neighborCount == 4) {
            // 네 방향 중 하나를 뺀 값 = sumNeighbors - (해당 방향 값)
            // 전체 합 = center + 위 값
            maxSum = Math.max(maxSum, center + sumNeighbors - minNeighbor);
        } else {
            // neighborCount == 3
            maxSum = Math.max(maxSum, center + sumNeighbors);
        }
    }
}
