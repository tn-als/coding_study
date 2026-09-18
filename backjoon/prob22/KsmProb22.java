package prob22;

import prob21.KsmProb21;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class KsmProb22 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[][] jem;
    int[] bags;

    private void jemThief() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        jem = new int[n][2];
        bags = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            jem[i][0] = Integer.parseInt(st.nextToken());
            jem[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);
        Arrays.sort(jem, Comparator.comparingInt((int[] o) -> o[0]));

        long cost = 0;
        int index = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < k; i++) {
            int bag = bags[i];

            while (index < n && jem[index][0] <= bag) {
                pq.offer(jem[index++][1]);
            }

            if (!pq.isEmpty()) {
                cost += pq.poll();
            }
        }

        bw.write(cost + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        KsmProb22 main = new KsmProb22();
        main.jemThief();
    }
}
