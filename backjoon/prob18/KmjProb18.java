package prob18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KmjProb18 {
    static int N, M, H, answer = -1;
    static boolean[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true; // a번째 줄의 b-세로선과 b+1-세로선 사이에 가로선 있음
        }

        // 사다리 추가 0~3개 시도
        for (int add = 0; add <= 3; add++) {
            if (dfs(1, 0, add)) {  // y=1부터(행), 추가 카운트, 목표 개수
                answer = add;
                break;
            }
        }

        System.out.println(answer);
    }

    // 백트래킹 dfs: 행, 추가선수, 목표선수
    static boolean dfs(int row, int count, int max) {
        if (count == max) {
            return checkLadder();
        }

        for (int i = row; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (canPlace(i, j)) {
                    ladder[i][j] = true;
                    if (dfs(i, count + 1, max)) {
                        ladder[i][j] = false; // 복구
                        return true; // 한 번이라도 성공하면 즉시 종료
                    }
                    ladder[i][j] = false;
                }
            }
        }
        return false;
    }

    static boolean canPlace(int i, int j) {
        // 인접 가로선 X
        return !ladder[i][j] && !ladder[i][j - 1] && !ladder[i][j + 1];
    }

    // i → i 를 모두 만족하는지 체크
    static boolean checkLadder() {
        for (int i = 1; i <= N; i++) {
            int col = i;
            for (int row = 1; row <= H; row++) {
                if (ladder[row][col]) col++;
                else if (ladder[row][col - 1]) col--;
            }
            if (col != i) return false;
        }
        return true;
    }
}

// 초기 구현

//public class KmjProb18 {
//    private static int answer = -1;
//    private static int N;
//    private static int H;
//    private static boolean[][] ladder;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        H = Integer.parseInt(st.nextToken());
//
//        ladder = new boolean[H][N];
//        for (int i = 0; i < M; i++) {
//            st = new StringTokenizer(br.readLine());
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            ladder[a - 1][b - 1] = true;
//        }
//
//        int maxAdd = H * (N - 1) - M;
//        for (int i = 0; i <= Math.min(3, maxAdd); i++) {
//            buildLadder(0, i);
//            if (answer != -1) break;
//        }
//
//        System.out.print(answer);
//
//        br.close();
//    }
//
//    // 1. 반복문으로 추가 사다리 1 ~ (H * (N-1) - M)개 일 때 bt로 확인하기
//    // 2. bt할 때 한 번 더 true 일 때랑 false 일 때 bt 으로 확인하기
//    // 추가 사다리가 최대 3개까지니깐 반복문이 메모리랑 시간을 덜 쓸 것 같아서 반복문으로 해보기
//    private static void buildLadder(int buildCnt, int maxBuild) {
//
//        if (buildCnt == maxBuild) {
//            if (checkIsValid()) answer = maxBuild;
//            return ;
//        }
//
//        for (int i = 0; i < H; i++) {
//
//            if (answer != -1) break;
//
//            for (int j = 0; j < N - 1; j++) {
//
//                if (answer != -1) break;
//
//                if (ladder[i][j]) continue;
//                if ((j + 1 < N && ladder[i][j + 1]) || (j - 1 >= 0 && ladder[i][j - 1])) continue;
//
//                ladder[i][j] = true;
//                buildLadder(buildCnt + 1, maxBuild);
//                ladder[i][j] = false;
//            }
//        }
//    }
//
//    private static boolean checkIsValid() {
//        for (int i = 0; i < N; i++) {
//            int curCol = i;
//            int curDepth = 0;
//            while (curDepth < H) {
//                if (ladder[curDepth][curCol]) curCol++;
//                else if (curCol - 1 >= 0 && ladder[curDepth][curCol - 1]) curCol--;
//                curDepth++;
//            }
//            if (curCol != i) return false;
//        }
//
//        return true;
//    }
//}
