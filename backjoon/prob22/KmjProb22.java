package prob22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class KmjProb22 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] jewelries = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewelries[i][0] = Integer.parseInt(st.nextToken());
            jewelries[i][1] = Integer.parseInt(st.nextToken());
        }
        // 무게 오름차순
        Arrays.sort(jewelries, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        // 무게 오름차순
        Arrays.sort(bags);

        // 가격 내림차순
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long sum = 0;
        int idx = 0;
        for (int bag : bags) {
            // 가방에 담을 수 있는 보석들을 pq에 저장
            // pq에 이미 앞선 가방에 담을 수 있는 보석들이 저장되어 있음
            // 다음 가방은 이전 가방보다 담을 수 있는 무게가 크기 때문에
            // pq에 이미 있는 보석들에 더 담을 수 있는 보석들만 추가하면 됨
            // 따라사 pq랑 idx를 초기화할 필요 X
            while (idx < N && jewelries[idx][0] <= bag) {
                pq.add(jewelries[idx][1]);
                idx++;
            }

            // 담을 수 있는 보석들 중 가장 가격이 높은 걸 가방에 담기
            if (!pq.isEmpty()) {
                sum += pq.poll();
            }
        }

        System.out.print(sum);
        br.close();
    }
}