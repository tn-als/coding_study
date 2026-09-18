package prob23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KmjProb23 {

    private static class DeliveryInfo {
        private final int from;
        private final int to;
        private final int numBox;

        public DeliveryInfo(int from, int to, int numBox) {
            this.from = from;
            this.to = to;
            this.numBox = numBox;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        DeliveryInfo[] deliveryArr = new DeliveryInfo[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int numBox = Integer.parseInt(st.nextToken());
            DeliveryInfo deliveryInfo = new DeliveryInfo(from, to, numBox);
            deliveryArr[i] = deliveryInfo;
        }

        Arrays.sort(deliveryArr, (o1, o2) -> {
            if (o1.to == o2.to) return o1.from - o2.from;
            return o1.to - o2.to;
        });

        int[] available = new int[N + 1];
        Arrays.fill(available, C);
        int totalBox = 0;

        for (DeliveryInfo info : deliveryArr) {
            int minBox = Integer.MAX_VALUE;
            for (int j = info.from; j < info.to; j++) {
                minBox = Math.min(minBox, available[j]);
            }
            int boxesToLoad = Math.min(minBox, info.numBox);
            totalBox += boxesToLoad;
            for (int j = info.from; j < info.to; j++) {
                available[j] -= boxesToLoad;
            }
        }
        System.out.println(totalBox);

    }
}
