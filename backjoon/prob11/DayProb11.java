package prob11;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DayProb11 {

    private static int[][] map;
    private static int[][] dist;
    private static boolean[][] visited;
    private static int[] moveRow = {0, 0, 1, -1};
    private static int[] moveCol = {1, -1, 0, 0};
    private static int N;
    private static PriorityQueue<int[]> pq;

    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int iter = 1;
        while(true){
            N = Integer.parseInt(br.readLine());
            if (N == 0){break;}

            visited = new boolean[N][N];

            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], 10 * N * N);
            }

            map = new int[N][N];
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Dijkstra(0,0);

            sb.append("Problem ").append(iter).append(": ").append(dist[N-1][N-1]).append("\n");
            iter++;
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void Dijkstra(int row, int col) {
        pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[] {map[row][col], row * N + col});
        visited[row][col] = true;
        dist[row][col] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            for (int i = 0; i < 4; i++){
                int newRow = cur[1] / N + moveRow[i];
                int newCol = cur[1] % N + moveCol[i];
                if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) {
                    if (!visited[newRow][newCol]){
                        visited[newRow][newCol] = true;
                        if (cur[0] + map[newRow][newCol] < dist[newRow][newCol]){
                            dist[newRow][newCol] = cur[0] + map[newRow][newCol];
                            pq.offer(new int[] {dist[newRow][newCol], newRow * N + newCol});
                        }
                    }
                }
            }
        }
    }
}
