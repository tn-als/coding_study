package prob11;

import java.awt.*;
import java.io.*;
import java.util.*;

public class KsmProb11 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] distX = {0, -1, 0, 1};
    int[] distY = {1, 0, -1, 0};

    private void zelda() throws IOException {
        StringBuilder sb = new StringBuilder();

        // 문제 번호
        int index = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            // 입력받은 도둑루피 저장
            int[][] cave = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem " + (index++) + ": " + bfs(cave) + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private int bfs(int[][] cave) {
        // 다익스트라 알고리즘을 위한 우선순위 큐
        // cave의 각 인덱스 값을 비교하여 오름차순으로 정렬
        PriorityQueue<Point> q = new PriorityQueue<>(
                Comparator.comparingInt(p -> cave[p.y][p.x])
        );

        // 입력 도둑루피 값이 0이상 9이하로 0이 나올 수도 있기 때문에
        // 초기 값은 null로 하고자 Integer 사용
        Integer[][] rupee = new Integer[cave.length][cave.length];

        q.add(new Point(0, 0));
        rupee[0][0] = cave[0][0];

        while (!q.isEmpty()) {
            Point p = q.poll();

            // 목표 위치면 아래의 반복문 돌지 않고 바로 종료
            if (p.x == cave.length - 1 && p.y == cave.length - 1) continue;

            for (int i = 0; i < 4; i++) {
                int nextX = p.x + distX[i];
                int nextY = p.y + distY[i];

                // 배열 범위 벗어나면 continue
                if (nextX < 0 || nextX >= cave.length || nextY < 0 || nextY >= cave.length) continue;

                // 다음 위치에 갈 때 현재 위치 값을 더한 값
                int addRupee = rupee[p.y][p.x] + cave[nextY][nextX];

                // 다음 위치의 루피가 배열에 아직 저장되지 않았거나
                // 다음 위치의 루피 값보다 addRupee가 작다면
                // 다음 위치의 루피 값을 addRupee로 변경하고 큐에 해당 인덱스 넣음
                if(rupee[nextY][nextX] == null || rupee[nextY][nextX] > addRupee){
                    rupee[nextY][nextX] = addRupee;
                    q.add(new Point(nextX, nextY));
                }
            }
        }

        return rupee[cave.length - 1][cave.length - 1];
    }

    public static void main(String[] args) throws Exception {
        KsmProb11 main = new KsmProb11();
        main.zelda();
    }
}
