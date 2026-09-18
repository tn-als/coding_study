package prob35;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DayProb35 {
    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        // 이런 단순 노가다 방식이 맞는걸까 ?
        for (int i = 0; i < n; i++){
            String thisline = br.readLine();
            for (int j = 0; j < m; j++){
                map[i][j] = thisline.charAt(j) - '0';
            }
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2];
        visited[0][0][0] = true;

        q.add(new int[]{0,0,0,0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int broke = cur[2];
            int dist = cur[3];

            if (cx == n-1 && cy == m-1) {
                System.out.println(dist+1);
                return;
            }

            for (int i = 0; i < 4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                // 벽이 없는곳 (0) 이고, broke 가 0이던 1이던 상관없이 방문한적이 없는 경우
                if (map[nx][ny] == 0 && !visited[nx][ny][broke]) {
                    visited[nx][ny][broke] = true;
                    q.add(new int[]{nx, ny, broke, dist+1});
                }
                // 벽이 있는곳 (1) 이고, broke가 0이라서 뚫을 수 있는 경우, 근데 다른곳에서 이미 뚫고 간 더 짧은 경로가 있으면 제외해야하니깐
                if (map[nx][ny] == 1 && broke == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    q.add(new int[]{nx, ny, 1, dist+1});
                }

            }
        }

        System.out.println(-1);

    }
}
