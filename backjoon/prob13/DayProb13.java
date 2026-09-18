package prob13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayProb13 {

    private static int[][] paper;
    private static int N;
    private static int M;
    private static int[] moveRow = {0,0,-1,1};
    private static int[] moveCol = {-1,1,0,0};
    private static int[] fuRow = {0, 2, 0, -2};
    private static int[] fuCol = {2, 0, -2, 0};
    private static int[][] fu = {{-1, 1}, {1 ,1}, {1, -1}, {-1,-1}};
    private static boolean visited[][];

    public static void main(String[] argv) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                int result = dfs(i, j, 0);
                int result2 = fu(i, j);
                if (max < result) {max = result;}
                if (max < result2) {max = result2;}
            }
        }

        System.out.println(max);
    }

    private static int fu (int row, int col) {
        int max = 0;
        for (int i = 0; i < 4; i++){
            int newRow = row + fuRow[i];
            int newCol = col + fuCol[i];
            if (newRow < 0 || newCol < 0 || newRow >= N || newCol >= M){continue;}

            int new1 = 0;
            if (i == 0) {
                for (int j = 0; j < 3; j++){
                    new1 += paper[row][col+j];
                }
            } else if (i == 1) {
                for (int j = 0; j < 3; j++){
                    new1 += paper[row+j][col];
                }
            } else if (i == 2) {
                for (int j = 0; j < 3; j++){
                    new1 += paper[row][col-j];
                }
            } else {
                for (int j = 0; j < 3; j++){
                    new1 += paper[row-j][col];
                }
            }

            for (int j = 0; j < 2; j++){
                int fuRow = row + fu[(i+j)%4][0];
                int fuCol = col + fu[(i+j)%4][1];
                if(fuRow < 0 || fuCol < 0 || fuRow >= N || fuCol >= M){continue;}
                int new2 = new1 + paper[fuRow][fuCol];
                if (new2 > max) {max = new2;}
            }
        }
        return max;
    }

    private static int dfs (int row, int col, int depth) {
        if (depth == 3) {
            return paper[row][col];
        }

        int max = 0;
        visited[row][col] = true;

        for (int j = 0; j < 4; j++){
            int newRow = row + moveRow[j];
            int newCol = col + moveCol[j];
            if (newRow < 0 || newCol < 0 || newRow >= N || newCol >= M){continue;}
            if (visited[newRow][newCol]){continue;}

            int result = paper[row][col] + dfs(newRow, newCol, depth + 1);
            if (max < result){
                max = result;
            }
        }

        visited[row][col] = false;
        return max;
    }

}
