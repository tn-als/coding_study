package programmers.lv0.p12906_같은숫자는싫어;

import java.io.*;
import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Deque<Integer> q = new ArrayDeque<>();

        int n=arr.length;
        for(int i=0; i<n; i++){
            int before = -1;
            if(!q.isEmpty()) before = q.peekLast();

            if(before != arr[i] || before == -1) q.offer(arr[i]);
        }

        int len = q.size();
        int[] answer = new int[len];
        int index=0;
        while(!q.isEmpty()){
            answer[index++]=q.poll();
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        programmers.lv0.p12906_같은숫자는싫어.Solution sol = new programmers.lv0.p12906_같은숫자는싫어.Solution();
        int[] array = {1, 2, 3, 3, 3, 4};
        int[] result = sol.solution(array);

        bw.write(Arrays.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
