package prob25;

import prob24.KsmProb24;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class KsmProb25 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private void dslr() throws IOException {
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(findMin(a, b)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private String findMin(int initial, int goal) throws IOException {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        boolean[] visited = new boolean[10000];
        int[] prev = new int[10000];
        char[] cmd = new char[10000];

        q.offer(initial);
        visited[initial] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == goal) break;

            // D
            int d = (curr * 2) % 10000;
            if (!visited[d]) {
                visited[d] = true;
                prev[d] = curr;
                cmd[d] = 'D';
                q.offer(d);
            }

            // S
            int s = (curr == 0 ? 9999 : curr - 1);
            if (!visited[s]) {
                visited[s] = true;
                prev[s] = curr;
                cmd[s] = 'S';
                q.offer(s);
            }

            // L
            int l = (curr % 1000) * 10 + curr / 1000;
            if (!visited[l]) {
                visited[l] = true;
                prev[l] = curr;
                cmd[l] = 'L';
                q.offer(l);
            }

            // R
            int r = (curr / 10) + (curr % 10) * 1000;
            if (!visited[r]) {
                visited[r] = true;
                prev[r] = curr;
                cmd[r] = 'R';
                q.offer(r);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = goal; i != initial; i = prev[i]) {
            sb.append(cmd[i]);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) throws Exception {
        KsmProb25 main = new KsmProb25();
        main.dslr();
    }
}
