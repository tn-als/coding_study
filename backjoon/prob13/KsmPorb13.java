package prob13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class KsmPorb13 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int max = 0;
    int[][] map;
    boolean[][] visited;
    int[] distX = {0, -1, 0, 1};
    int[] distY = {1, 0, -1, 0};

    int[][][] centerShape = {
            {{-1, 0}, {0, 1}, {1, 0}}, // ㅗ
            {{-1, 0}, {0, -1}, {1, 0}}, // ㅜ
            {{-1, 0}, {0, 1}, {0, -1}}, // ㅓ
            {{1, 0}, {0, 1}, {0, -1}}  // ㅏ
    };

    private void getMap() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 2][m + 2];
        visited = new boolean[n + 2][m + 2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                centerTetromino(j, i);
                visited[i][j] = true;
                findMax(j, i, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }

    private void centerTetromino(int x, int y) {
        for (int[][] shape : centerShape) {
            int sum = 0;
            boolean valid = true;
            for (int[] block : shape) {
                int nextX = x + block[0];
                int nextY = y + block[1];
                if (nextX < 1 || nextX > m || nextY < 1 || nextY > n) {
                    valid = false;
                    break;
                }
                sum += map[nextY][nextX];
            }

            if (valid) max = Math.max(max, sum + map[y][x]);
        }
    }

    private void findMax(int x, int y, int count, int total) {
        if (count == 4) {
            max = Math.max(max, total);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + distX[i];
            int nextY = y + distY[i];

            if (nextX <= 0 || nextX > m || nextY <= 0 || nextY > n) continue;
            if (visited[nextY][nextX]) continue;

            visited[nextY][nextX] = true;
            findMax(nextX, nextY, count + 1, total + map[nextY][nextX]);
            visited[nextY][nextX] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        KsmPorb13 main = new KsmPorb13();
        main.getMap();
    }
}
