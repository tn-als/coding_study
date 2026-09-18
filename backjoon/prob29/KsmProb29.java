package prob29;

import java.io.*;
import java.util.Stack;

public class KsmProb29 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[][] arr;

    private void quadTree() throws IOException {
        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(s.charAt(j - 1) + "");
            }
        }

        if (n == 1) bw.write(arr[1][1]+"");
        else {
            String result = compression(1, n, 1, n);
            if (result.length() == 3) bw.write(result.charAt(1) + "");
            else bw.write(result);
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private String compression(int start, int end, int startY, int endY) {
        if (end - start == 0) {
            return String.valueOf(arr[startY][start]);
        }

        int mid = (start + end) / 2;
        int midY = (startY + endY) / 2;

        String leftUp = compression(start, mid, startY, midY);
        String rightUp = compression(mid + 1, end, startY, midY);
        String leftDown = compression(start, mid, midY + 1, endY);
        String rightDown = compression(mid + 1, end, midY + 1, endY);

        // 모두 다 값이 같다면
        if (leftUp.length() == 1 && leftUp.equals(rightUp) && leftUp.equals(leftDown) && leftUp.equals(rightDown)) {
            return leftUp;
        }

        // 다 값이 같지 않다면
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(leftUp).append(rightUp).append(leftDown).append(rightDown).append(")");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        KsmProb29 main = new KsmProb29();
        main.quadTree();
    }
}
