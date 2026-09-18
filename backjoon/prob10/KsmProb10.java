package prob10;

import java.awt.*;
import java.io.*;
import java.util.*;

public class KsmProb10 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    ArrayList<Integer> apartCount = new ArrayList<Integer>();

    int[] distX = {1, 0, -1, 0};
    int[] distY = {0, 1, 0, -1};

    int[][] apart;
    boolean[][] visited;

    private void numbering() throws IOException {
        int n = Integer.parseInt(br.readLine());

        apart = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                apart[i][j] = Integer.parseInt(line.charAt(j) + "");
            }
        }


        StringBuilder sb = new StringBuilder();

        int index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (apart[i][j] == 1 && !visited[i][j]) {
                    apartCount.add(0);
                    dfs(index++, j, i);
                }
            }
        }

        sb.append(index + "\n");
        Collections.sort(apartCount);
        for (Integer count : apartCount) {
            sb.append(count + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private void dfs(int index, int x, int y) {
        if (apart[y][x] == 0) return;

        apartCount.set(index, apartCount.get(index) + 1);
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int nextX = x + distX[i];
            int nextY = y + distY[i];

            if (nextX < 0 || nextX >= apart.length || nextY < 0 || nextY >= apart[0].length || visited[nextY][nextX])
                continue;
            dfs(index, nextX, nextY);
        }
    }

    public static void main(String[] args) throws Exception {
        KsmProb10 main = new KsmProb10();
        main.numbering();
    }
}
