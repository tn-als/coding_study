package prob10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DayProb10 {

    private static int[][] map;
    private static boolean[][] visited;
    private static Queue<Integer> queue = new LinkedList<>();
    private static int N;
    private static int[] moveRow = {0, 0, 1, -1};
    private static int[] moveCol = {1, -1, 0, 0};

    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++){
            String line = br.readLine();
            for (int j = 0; j < N; j++){
                map[i][j] = line.charAt(j) - '0';
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    int cnt = bfs(i, j);
                    result.add(cnt);
                }
            }
        }

        Collections.sort(result);

        sb.append(result.size()).append("\n");
        for (Integer integer : result) {
            sb.append(integer).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static int bfs(int row, int col){
        visited[row][col] = true;
        queue.offer(row * N + col);
        int cnt = 1;

        while (!queue.isEmpty()) {
            int next = queue.poll();
            for (int i = 0; i < 4; i++){
                int newRow = next / N + moveRow[i];
                int newCol = next % N + moveCol[i];
                if (newRow >=0 && newRow < N && newCol >=0 && newCol < N){
                    if (!visited[newRow][newCol] && map[newRow][newCol] == 1){
                        visited[newRow][newCol] = true;
                        queue.offer(newRow * N + newCol);
                        cnt ++;
                    }
                }
            }
        }

        return cnt;
    }
}
