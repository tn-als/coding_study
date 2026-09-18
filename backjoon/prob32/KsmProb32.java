package prob32;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class KsmProb32 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int max;
    int[] rectangles;

    private void biggestRectangleInHisto() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            rectangles = new int[n];

            for (int i = 0; i < n; i++) {
                rectangles[i] = Integer.parseInt(st.nextToken());
            }

            // 일단 가로 n, 세로 1인 직사각형의 넓이를 max로 둠
            max = n;
            calculateWidths(n);

            sb.append(max).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private void calculateWidths(int n) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 이전보다 작으면 계산
            if (i > 0 && rectangles[i] < rectangles[i - 1]) {
                while (!stack.isEmpty()) {
                    int curr = stack.pop();
                    int width = rectangles[curr] * (i - curr);
                    if (width > max) max = width;
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            int width = rectangles[curr] * (n - curr);
            if (width > max) max = width;
        }
    }

    public static void main(String[] args) throws Exception {
        KsmProb32 main = new KsmProb32();
        main.biggestRectangleInHisto();
    }
}
