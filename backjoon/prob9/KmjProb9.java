package prob9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class KmjProb9 {
    private static int N;
    private static int M;
    private static int[][] grid;
    private static ArrayList<int[]> virus = new ArrayList<>();
    private static int totalZero;
    private static ArrayList<int[]> candidates = new ArrayList<>();
    private static Queue<int[]> q = new LinkedList<>();
    private static boolean[][] visited;

    private static final int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 2) virus.add(new int[]{i, j});
                if (grid[i][j] == 0) {
                    totalZero++;
                    candidates.add(new int[]{i, j});
                }
            }
        }

        int minInfected = Integer.MAX_VALUE;

        int numCandidate = candidates.size();
        for (int i = 0; i < numCandidate; i++) {
            for (int j = i + 1; j < numCandidate; j++) {
                for (int k = j + 1; k < numCandidate; k++) {
                    visited = new boolean[N][M];

                    grid[candidates.get(i)[0]][candidates.get(i)[1]] = 1;
                    grid[candidates.get(j)[0]][candidates.get(j)[1]] = 1;
                    grid[candidates.get(k)[0]][candidates.get(k)[1]] = 1;

                    int result = bfs();
                    minInfected = Math.min(minInfected, result);

                    grid[candidates.get(i)[0]][candidates.get(i)[1]] = 0;
                    grid[candidates.get(j)[0]][candidates.get(j)[1]] = 0;
                    grid[candidates.get(k)[0]][candidates.get(k)[1]] = 0;
                }
            }
        }

        System.out.print(totalZero - 3 - minInfected);
        br.close();
    }

    private static int bfs() {
        q.clear();

        int infectedCnt = 0;

        for (int[] aVirus : virus) {
            visited[aVirus[0]][aVirus[1]] = true;
            q.add(new int[]{aVirus[0], aVirus[1]});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur[0] + move[i][0];
                int nextY = cur[1] + move[i][1];

                if (nextX >= 0 && nextX < N && nextY >=0 && nextY < M
                        && grid[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    q.add(new int[]{nextX, nextY});
                    infectedCnt++;
                }
            }
        }

        return infectedCnt;
    }
}