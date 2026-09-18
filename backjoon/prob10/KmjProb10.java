package prob10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class KmjProb10 {
    private static int N;
    private static int[][] map;
    private static boolean[][] visited;
    private static Queue<int[]> q = new LinkedList<>();
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    private static int[] moveX = {-1, 0, 1, 0};
    private static int[] moveY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(inputLine.charAt(j)));
            }
        }
        visited = new boolean[N][N];

        int totalCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    int cntHouse = bfs(i, j);
                    pq.add(cntHouse);
                    totalCnt++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(totalCnt).append("\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(int x, int y) {
        q.add(new int[]{x, y});
        visited[x][y] = true;

        int cnt = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur[0] + moveX[i];
                int nextY = cur[1] + moveY[i];

                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                    if (map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                        q.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }
}