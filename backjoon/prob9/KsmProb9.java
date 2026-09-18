package prob9;

import java.awt.*;
import java.io.*;
import java.util.*;

public class KsmProb9 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private final int EMPTY = 0;
    private final int WALL = 1;
    private final int VIRUS = 2;

    int[] distX = {0, -1, 0, 1};
    int[] distY = {-1, 0, 1, 0};
    int[][] map;
    
    ArrayList<Point> emptyList = new ArrayList<>();
    int n, m;
    int max = -1;

    private void lab() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // empty인 것만 리스트에 따로 저장
                if (map[i][j] == EMPTY) emptyList.add(new Point(j, i));
            }
        }

        bt(0, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();

    }

    private void bt(int count, int start) {
        // 벽을 3개 세웠으면 바이러스 퍼뜨려서 안전지역 값 구함
        if (count == 3) {
            int[][] copy = new int[n][m];
            for (int i = 0; i < n; i++) {
                copy[i] = Arrays.copyOf(map[i], map[i].length);
            }
            max = Math.max(max, spreadVirus(copy));
            return;
        }

        // empty인 부분에 벽을 세움
        for (int i = start; i < emptyList.size(); i++) {
            Point p = emptyList.get(i);
            map[p.y][p.x] = WALL;
            bt(count + 1, i + 1);
            map[p.y][p.x] = EMPTY;
        }
    }

    private int spreadVirus(int[][] copy) {
        Queue<Point> q = new LinkedList<>();

        // 0의 개수 저장
        int safeEra = findVirus(q, copy);
        boolean[][] visited = new boolean[copy.length][copy[0].length];

        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;

            visited[y][x] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = x + distX[i];
                int nextY = y + distY[i];

                // 범위 벗어나면 continue
                if (!(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n)) continue;

                // 현재 위치에 바이러스가 있고 다음 위치가 비어있다면
                // 다음 위치에 바이러스 넣고 safeEra--
                if (copy[y][x] == VIRUS && copy[nextY][nextX] == EMPTY) {
                    copy[nextY][nextX] = VIRUS;
                    safeEra--;
                }
                // 현재 위치에 바이러스가 없고 다음 위치에 방문했다면 continue
                else {
                    if (visited[nextY][nextX]) continue;
                }

                q.add(new Point(nextX, nextY));
            }
        }

        return safeEra;
    }

    // 초기의 바이러스가 있는 위치를 모두 큐에 저장
    private int findVirus(Queue<Point> q, int[][] copy) {
        int safeEra = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == VIRUS) {
                    q.add(new Point(j, i));
                }
                if (copy[i][j] == EMPTY) {
                    safeEra++;
                }
            }
        }
        return safeEra;
    }

    public static void main(String[] args) throws Exception {
        KsmProb9 main = new KsmProb9();
        main.lab();
    }
}
