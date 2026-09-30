package programmers.bfs.p1844_게임맵최단거리;

import java.io.*;
import java.util.*;

public class Solution {
    int[] X={0, -1, 0, 1};
    int[] Y={1, 0, -1, 0};

    int min;

    public int solution(int[][] maps) {
        min=maps.length*maps[0].length+1;
        bfs(maps);

        if(min==maps.length*maps[0].length+1) return -1;
        else return min;
    }

    public void bfs(int[][] maps){
        Queue<int[]> queue = new LinkedList<>();
        int maxX=maps[0].length;
        int maxY=maps.length;
        boolean[][] isvisited=new boolean[maxY][maxX];

        int[] curr={0,0,1};
        queue.offer(curr);

        while(!queue.isEmpty()){
            curr=queue.poll();
            if(isvisited[curr[1]][curr[0]]==true) continue;

            isvisited[curr[1]][curr[0]]=true;
            if(curr[0]==maxX-1 && curr[1]==maxY-1){
                if(curr[2]<min) min=curr[2];
                continue;
            }

            for(int i=0; i<4; i++){
                int newX=curr[0]+X[i];
                int newY=curr[1]+Y[i];

                if(newX<0 || newX>=maxX || newY<0 || newY>=maxY) continue;
                if(maps[newY][newX]==0) continue;
                if(isvisited[newY][newX]==true) continue;

                int[] route={newX, newY, curr[2]+1};
                queue.offer(route);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        programmers.bfs.p1844_게임맵최단거리.Solution sol = new programmers.bfs.p1844_게임맵최단거리.Solution();
        int[][] array = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        bw.write(Integer.toString(sol.solution(array)));
        bw.flush();
        bw.close();
        br.close();
    }
}
