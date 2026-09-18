package prob12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class FixedNode {
    int end;
    int weight;

    public FixedNode(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

public class KmjProb12 {
    private static int src;
    private static ArrayList<FixedNode>[] graph;
    private static PriorityQueue<FixedNode> queue = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
    private static Integer[] cnt;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V + 1];
        cnt = new Integer[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            cnt[i] = Integer.MAX_VALUE;
        }
        src = Integer.parseInt(br.readLine());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new FixedNode(v, w));
        }
        visited = new boolean[V + 1];
        cnt[src] = 0;
        findPath();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (cnt[i] == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(cnt[i]);
            if (i < V) sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    private static void findPath() {
        queue.add(new FixedNode(src, 0));
        while (!queue.isEmpty()) {
            FixedNode u = queue.poll();

            if (!visited[u.end]) visited[u.end] = true;

            for (int i = 0; i < graph[u.end].size(); i++) {
                FixedNode v = graph[u.end].get(i);
                if (!visited[v.end] && u.weight + v.weight < cnt[v.end]) {
                    cnt[v.end] = u.weight + v.weight;
                    queue.add(new FixedNode(v.end, cnt[v.end]));
                }
            }
        }
    }
}
