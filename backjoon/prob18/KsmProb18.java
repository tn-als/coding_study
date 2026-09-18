package prob18;

import prob17.KsmProb17;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class KsmProb18 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m, h;
    boolean[][] ladder;
    ArrayList<int[]> possibilities = new ArrayList<>();

    private void controlLadder() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());       // 세로선
        m = Integer.parseInt(st.nextToken());       // 가로선
        h = Integer.parseInt(st.nextToken());       // 세로선마다 가로선 놓을 수 있는 위치의 개수

        ladder = new boolean[h + 1][n + 1];

        if (m == 0) bw.write("0");
        else {
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                ladder[y][x] = true;
            }

            boolean flag = false;
            for (int i = 0; i <= 3; i++) {
                if (drawLines(0, i, 0)) {
                    bw.write(String.valueOf(i));
                    flag = true;
                    break;
                }
            }

            if (!flag) bw.write("-1");
        }
        bw.flush();
        bw.close();
        br.close();


    }

    private boolean drawLines(int depth, int target, int start) {
        if (depth == target) {
            for (int i = 1; i <= n; i++) {
                int current = i;
                for (int j = 1; j <= h; j++) {
                    if (ladder[j][current]) current++;
                    else if (ladder[j][current - 1]) current--;
                }

                if (current != i) {
                    return false;
                }
            }
            return true;
        }

        // 제일 마지막 열은 안 봄
        for (int i = start; i < h * (n - 1); i++) {
            int x = i % (n - 1) + 1;
            int y = i / (n - 1) + 1;

            if (ladder[y][x] || ladder[y][x - 1] || ladder[y][x + 1]) continue;
            ladder[y][x] = true;
            if (drawLines(depth + 1, target, i + 1)) return true;
            ladder[y][x] = false;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        KsmProb18 main = new KsmProb18();
        main.controlLadder();
    }
}
