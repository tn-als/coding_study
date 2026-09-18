package prob12;

import java.awt.*;
import java.io.*;
import java.util.*;

public class KsmProb12 {
    class Node {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private void shortestPath() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        ArrayList<Node>[] adj = new ArrayList[v + 1];

        // 각 값을 입력받아 adj[from]에 Node(to,cost)를 저장
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (adj[from] == null) {
                adj[from] = new ArrayList<Node>();
            }

            adj[from].add(new Node(to, cost));
        }

        // 최단 경로 저장
        Integer[] sp = new Integer[v + 1];

        // 각 인덱스 별 최단 경로 구함
        dijkstraShortestPath(adj, sp, k);

        // 시작 인덱스면 0, 최단 경로가 없다면 INF, 나머지는 sp[i]를 sb에 저장하고
        // sb를 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            if (i == k) {
                sb.append("0\n");
                continue;
            }
            sb.append((sp[i] == null ? "INF" : sp[i]) + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private void dijkstraShortestPath(ArrayList<Node>[] adj, Integer[] sp, int start) {
        // 저장된 최단 거리 값의 오름차순으로 from 정렬
        PriorityQueue<Integer> q = new PriorityQueue<>(
                Comparator.comparingInt(from -> sp[from])
        );

        q.add(start);
        while (!q.isEmpty()) {
            int from = q.poll();

            // adj[from]이 null이면 (가중치가 없다면) continue
            if (adj[from] == null) continue;

            for (int i = 0; i < adj[from].size(); i++) {
                Node n = adj[from].get(i);

                // 현재 위치까지의 최단 거리 값 + n.cost
                int addCost = (sp[from] == null ? 0 : sp[from]) + n.cost;

                // 다음 위치의 최단 거리가 null이거나
                // addCost가 다음 위치 최단 거리보다 작다면
                // 다음 위치 최단거리를 addCost로 변경하고 큐에 넣음
                if (sp[n.to] == null || addCost < sp[n.to]) {
                    sp[n.to] = addCost;
                    q.add(n.to);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        KsmProb12 main = new KsmProb12();
        main.shortestPath();
    }
}
