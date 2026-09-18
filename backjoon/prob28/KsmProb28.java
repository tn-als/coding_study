package prob28;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class KsmProb28 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[][] arr;

    private void procession() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] result = new int[n][n];

        // 제곱수가 1이 아니면
        if(b!=1) result = solve(b);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 제곱수가 1이면 arr에서 1000을 나눈 나머지를 sb에 추가
                if(b==1) sb.append(arr[i][j] % 1000).append(" ");
                else sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private int[][] solve(long count) {
        // 제곱수가 1이면 arr를 return
        if (count == 1) {
            return arr;
        }

        // 제곱수의 절반을 구함
        int[][] half = solve(count / 2);

        // 행렬의 제곱을 구함
        int[][] result = calculateSquare(half, half);

        // 홀수면 arr를 한번 더 곱합
        if(count % 2 == 1){
            result = calculateSquare(result, arr);
        }

        return result;
    }

    private int[][] calculateSquare(int[][] arr1, int[][] arr2) {
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += arr1[i][k] * arr2[k][j];
                }
                result[i][j] %= 1000;
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        KsmProb28 main = new KsmProb28();
        main.procession();
    }
}
