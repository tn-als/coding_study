package programmers.bruteforce.p86491_최소직사각형;

import java.io.*;

public class Solution {
    public int solution(int[][] sizes) {
        int w=0;
        int h=0;

        for(int i=0; i<sizes.length; i++){
            int big=Math.max(sizes[i][0], sizes[i][1]);
            int small=Math.min(sizes[i][0], sizes[i][1]);

            if(big>w) w=big;
            if(small>h) h=small;
        }

        return w*h;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        programmers.bruteforce.p86491_최소직사각형.Solution sol = new programmers.bruteforce.p86491_최소직사각형.Solution();
        int[][] array = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        bw.write(Integer.toString(sol.solution(array)));
        bw.flush();
        bw.close();
        br.close();
    }
}
