package prob12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DayProb12 {

    private static List<List<int[]>> edges;
    private static int[] dist;
    private static boolean[] visited;

    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        dist = new int[V + 1];
        visited = new boolean[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        edges = new ArrayList<>(V + 1);
        for (int i = 0; i <= V; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.get(u).add(new int[]{v,w});
        }

        dijkstra(K);

        for (int i = 1; i < V+1; i++){
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            }else{
                sb.append(dist[i]).append("\n");
            }
        }

        System.out.print(sb.toString());

    }

    private static void dijkstra(int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        pq.offer(new int[] {K, 0});
        dist[K] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            visited[cur[0]] = true;
            List<int[]> e = edges.get(cur[0]);
            for (int[] es : e){
                if (!visited[es[0]] && dist[es[0]] > dist[cur[0]] + es[1]){
                    dist[es[0]] = dist[cur[0]] + es[1];
                    pq.offer(new int[] {es[0], dist[es[0]]});
                }
            }
        }
    }

}
