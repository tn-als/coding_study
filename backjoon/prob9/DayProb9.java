package prob9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayProb9 {

    static int rows;
    static int cols;
    static int[][] lab;

    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());

        lab = new int[rows][cols];

        // 저장
        for (int i = 0; i < rows; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cols; j++){
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max_result = 0;

        // 벽 세우기 brute-force
        for (int i = 0; i < rows * cols - 2; i++){
            if (lab[i/cols][i%cols] != 0){continue;}
            for (int j = i + 1; j < rows * cols - 1; j++){
                if (lab[j/cols][j%cols] != 0){continue;}
                for (int k = j + 1; k < rows * cols; k++){
                    if (lab[k/cols][k%cols] != 0){continue;}

                    int[][] new_lab = new int[rows][cols];
                    for (int r = 0; r < rows; r++) {
                        System.arraycopy(lab[r], 0, new_lab[r], 0, cols);
                    }

                    new_lab[i/cols][i%cols] = 1;
                    new_lab[j/cols][j%cols] = 1;
                    new_lab[k/cols][k%cols] = 1;

                    // BFS
                    int result = calculate(new_lab);

                    if (max_result < result) {
                        max_result = result;
                    }
                }
            }
        }

        System.out.println(max_result);
    }

    private static int calculate(int[][]lab){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (lab[i][j] == 2){
                    lab[i][j] = 0;
                    DFS(lab, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (lab[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }

    private static void DFS(int[][] lab, int row, int col){
        if (lab[row][col] == 0){
            lab[row][col] = 2;
        }
        else if (lab[row][col] == 1 || lab[row][col] == 2){
            return;
        }
        if (col + 1 < cols) {DFS(lab, row, col + 1);}
        if (row + 1 < rows) {DFS(lab, row + 1, col);}
        if (col - 1 >= 0) {DFS(lab, row, col - 1);}
        if (row - 1 >= 0) {DFS(lab, row - 1, col);}
    }
}
