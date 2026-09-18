package prob17;

import java.io.*;
import java.util.StringTokenizer;

public class KsmProb17 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[][] players;

    private void baseball() throws IOException {
        n = Integer.parseInt(br.readLine());
        players = new int[n][9];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] order = new int[9];
        order[3] = 0;
        bw.write(String.valueOf(findMax(order, new boolean[9], 0)));
        bw.flush();
        bw.close();
        br.close();
    }

    private int findMax(int[] order, boolean[] visited, int index) {
        if (index == 9) {
            return playGame(order);
        }

        int max = 0;
        if(index==3){
            max = Math.max(findMax(order, visited, index + 1), max);
        }else{
            for (int i = 1; i < 9; i++) {
                if (visited[i]) continue;
                order[index] = i;
                visited[i] = true;
                max = Math.max(findMax(order, visited, index + 1), max);
                visited[i] = false;
            }
        }

        return max;
    }

    private int playGame(int[] order) {
        int score = 0;
        int currentPlayer = 0;

        for (int i = 0; i < n; i++) {
            int out = 0;
            boolean[] ground = new boolean[4];

            while (out < 3) {
                int player = order[currentPlayer];
                int result = players[i][player];
                if (result == 0) {
                    out++;
                } else if (result == 4) {
                    for (int k = 1; k <= 3; k++) {
                        if (ground[k]) {
                            score++;
                            ground[k] = false;
                        }
                    }
                    score++;
                } else {
                    for (int j = 3; j >= 1; j--) {
                        if (!ground[j]) continue;
                        ground[j] = false;
                        if (j + result > 3) score++;
                        else ground[j + result] = true;
                    }

                    ground[result] = true;
                }

                currentPlayer++;
                if (currentPlayer == 9) currentPlayer = 0;
            }

        }

        return score;
    }

    public static void main(String[] args) throws Exception {
        KsmProb17 main = new KsmProb17();
        main.baseball();
    }
}
