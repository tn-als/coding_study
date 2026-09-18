package prob11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

public class KmjProb11 {
    private static int N;
    private static int[][] map;
    private static int[][] dp;
    private static boolean[][] visited;
    private static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> map[a[0]][a[1]]));

    private static int[] moveX = {-1, 0, 1, 0};
    private static int[] moveY = {0, 1, 0, -1};

    private static int INF = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int testNum = 0;
        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            testNum++;

            map = new int[N][N];
            dp = new int[N][N];
            dp[0][0] = map[0][0];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();
            sb.append("Problem ").append(testNum).append(": ")
                    .append(dp[N - 1][N - 1]).append("\n");

            N = Integer.parseInt(br.readLine());
        }

        System.out.print(sb);
    }

    private static void dijkstra() {
        // 지역변수로 선언하여 static 초기화 문제 해결
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = map[0][0];  // map 읽기 완료 후 초기화
        pq.add(new int[]{0, 0, dp[0][0]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], cost = cur[2];

            if (dp[x][y] < cost) continue;  // 이미 더 짧은 경로로 방문

            for (int i = 0; i < 4; i++) {
                int nx = x + moveX[i];
                int ny = y + moveY[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    int nextCost = cost + map[nx][ny];
                    if (dp[nx][ny] > nextCost) {
                        dp[nx][ny] = nextCost;
                        pq.add(new int[]{nx, ny, nextCost});
                    }
                }
            }
        }
    }
}