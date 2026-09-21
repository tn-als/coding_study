package programmers.lv0.p120812_최빈값구하기;

import java.io.*;
import java.util.HashMap;

public class Solution {
    public int solution(int[] array) {
        HashMap<Integer, Integer> hm = new HashMap<>();

        for(int i=0; i<array.length; i++){
            int num = array[i];
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        int max = 0;
        int answer = -1;

        for (int key : hm.keySet()) {
            int count = hm.get(key);

            if (count > max) {
                max = count;
                answer = key;
            } else if (count == max) {
                answer = -1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        programmers.lv0.p120812_최빈값구하기.Solution sol = new programmers.lv0.p120812_최빈값구하기.Solution();
        int[] array = {1, 2, 3, 3, 3, 4};
        int result = sol.solution(array);

        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
