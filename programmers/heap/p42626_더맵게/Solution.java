package programmers.heap.p42626_더맵게;

import java.io.*;
import java.util.*;

public class Solution {
    public int solution(int[] scoville, int K) {
        if(scoville.length==1){
            if(scoville[0]<K) return -1;
            else return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int count=0;

        for(int i=0; i<scoville.length; i++){
            int num=scoville[i];
            pq.offer(num);
        }

        if(pq.peek()>=K) return 0;

        while(pq.size()>1){
            int num=pq.poll()+(pq.poll()*2);
            pq.offer(num);
            count++;

            if(pq.peek()>=K) return count;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        programmers.heap.p42626_더맵게.Solution sol = new programmers.heap.p42626_더맵게.Solution();
        int[] scoville = {1, 2, 3, 9, 10, 12};
        bw.write(Integer.toString(sol.solution(scoville, 7)));
        bw.flush();
        bw.close();
        br.close();
    }
}
