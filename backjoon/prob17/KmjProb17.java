package prob17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KmjProb17 {
    private static int N;
    private static int[][] inning;
    private static final ArrayList<Integer> battingOrder = new ArrayList<>();
    private static int maxScore = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inning = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                inning[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        setBattingOrder(0);

        System.out.print(maxScore);

        br.close();
    }

    private static void setBattingOrder(int order) {
        if (order == 9) {
            cntScore();
            return;
        }
        if (order == 3) {
            battingOrder.add(0);
            setBattingOrder(order + 1);
            battingOrder.remove(order);
            return;
        }
        for (int i = 1; i < 9; i++) {
            if (!battingOrder.contains(i)) {
                battingOrder.add(i);
                setBattingOrder(order + 1);
                battingOrder.remove(order);
            }
        }
    }

    private static void cntScore() {
        int score = 0;
        int inningCnt = 1;
        int curBatterIdx = 0;
        while (inningCnt <= N) {
            int outCnt = 0;
            boolean[] base = new boolean[4];

            while (outCnt < 3) {

                int curBatter = battingOrder.get(curBatterIdx % 9);
                int curResult = inning[inningCnt - 1][curBatter];

                if (curResult == 0) {
                    outCnt++;
                }
                else if (curResult == 1) {
                    if (base[3]) score++;
                    base[3] = base[2];
                    base[2] = base[1];
                    base[1] = true;
                }
                else if (curResult == 2) {
                    if (base[3]) score++;
                    if (base[2]) score++;
                    base[3] = base[1];
                    base[2] = true;
                    base[1] = false;
                }
                else if (curResult == 3) {
                    for (int i = 1; i <= 3; i++) {
                        if (base[i]) score++;
                        base[i] = false;
                    }
                    base[3] = true;
                }
                else {
                    for (int i = 1; i <= 3; i++) {
                        if (base[i]) score++;
                    }
                    score++;
                    Arrays.fill(base, false);
                }

                curBatterIdx++;
            }

            Arrays.fill(base, false);
            inningCnt++;
        }

        maxScore = Math.max(score, maxScore);
    }

//    private static void cntScore() {
//        int score = 0;
//        int inningCnt = 1;
//        int curBatterIdx = 0;
//        ArrayList<Integer> base = new ArrayList<>();
//        while (inningCnt <= N) {
//            int outCnt = 0;
//
//            while (outCnt < 3) {
//
//                int curBatter = battingOrder.get(curBatterIdx % 9);
//                int curResult = inning[inningCnt - 1][curBatter];
//
//                if (curResult == 0) {
//                    outCnt++;
//                }
//                else if (curResult == 4) {
//                    score += base.size() + 1;
//                    base.clear();
//                }
//                else {
//                    int homeBaseArrive = 0;
//                    for (int i = 0; i < base.size(); i++) {
//                        int nextBase = base.get(i) + curResult;
//                        if (nextBase >= 4) {
//                            score++;
//                            homeBaseArrive++;
//                        }
//                        else {
//                            base.set(i, nextBase);
//                        }
//                    }
//
//                    if (homeBaseArrive > 0) {
//                        base.subList(0, homeBaseArrive).clear();
//                    }
//
//                    base.add(curResult);
//                }
//
//                curBatterIdx++;
//            }
//
//            base.clear();
//            inningCnt++;
//        }
//
//        maxScore = Math.max(score, maxScore);
//    }
}
