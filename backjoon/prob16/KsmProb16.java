package prob16;

import prob13.KsmPorb13;

import java.io.*;
import java.util.Arrays;

public class KsmProb16 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    boolean[][] chess;

    private void nQueens() throws IOException {
        int n = Integer.parseInt(br.readLine());
        chess = new boolean[n][n];

        bw.write(String.valueOf(countQueens(0)));
        bw.flush();
        bw.close();
        br.close();
    }

    private int countQueens(int y) throws IOException {
        if (y == chess.length) {
            return 1;
        }

        int count = 0;
        for (int i = 0; i < chess.length; i++) {
            if (!diagonal(i, y) || !horizontal(i, y)) continue;
            chess[y][i] = true;
            count += countQueens(y + 1);
            chess[y][i] = false;
        }

        return count;
    }

    private boolean diagonal(int x, int y) {
        int currentX = x-1;
        int currentY = y-1;
        // 대각선 왼쪽으로 위로 올라가는 방향
        while (currentX >= 0 && currentY >= 0) {
            if (chess[currentY--][currentX--]) return false;
        }

        currentX = x+1;
        currentY = y-1;
        // 대각선 오른쪽으로 위로 올라가는 방향
        while (currentX < chess.length && currentY >= 0) {
            if (chess[currentY--][currentX++]) return false;
        }

        return true;
    }

    private boolean horizontal(int x, int y) {
        int currentY = y-1;
        while (currentY >= 0) {
            if (chess[currentY--][x]) return false;
        }
        return true;
    }


    public static void main(String[] args) throws Exception {
        KsmProb16 main = new KsmProb16();
        main.nQueens();
    }
}
