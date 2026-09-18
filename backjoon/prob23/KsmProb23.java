package prob23;

import java.io.*;
import java.util.*;

public class KsmProb23 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private void delivery() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());               // 마을 개수
        int c = Integer.parseInt(st.nextToken());               // 트럭 총 용량

        int m = Integer.parseInt(br.readLine());
        int[][] village = new int[m][3];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int boxes = Integer.parseInt(st.nextToken());

            village[i] = new int[]{from, to, boxes};
        }

        Arrays.sort(village, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        int total = 0;
        int[] capacity = new int[n + 1];
        Arrays.fill(capacity, c);

        for (int[] box : village) {
            int from = box[0];
            int to = box[1];
            int boxes = box[2];

            // from ~ to-1 구간 중 최소값 찾기
            int min = Integer.MAX_VALUE;
            for (int i = from; i < to; i++) {
                min = Math.min(min, capacity[i]);
            }

            // 실제 실을 수 있는 개수
            int deliver = Math.min(min, boxes);
            total += deliver;

            for (int i = from; i < to; i++) {
                capacity[i] -= deliver;
            }
        }

        bw.write(String.valueOf(total));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        KsmProb23 main = new KsmProb23();
        main.delivery();
    }
}
