package prob5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class KmjProb5 {
    static Integer[][] arr = new Integer[41][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        arr[0][0] = 1;
        arr[0][1] = 0;
        arr[1][0] = 0;
        arr[1][1] = 1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(fibonacci(n)[0]);
            sb.append(" ");
            sb.append(fibonacci(n)[1]);
            if (i < t - 1) sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    private static Integer[] fibonacci(int n) {
        if (arr[n][0] == null || arr[n][1] == null) {
            arr[n][0] = fibonacci(n - 1)[0] + fibonacci(n - 2)[0];
            arr[n][1] = fibonacci(n - 1)[1] + fibonacci(n - 2)[1];
        }
        return arr[n];
    }
}
