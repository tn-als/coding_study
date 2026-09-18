package prob14;

import java.util.*;
import java.io.*;

public class PynProb14 {
    /*
     * 첨접근 : 목표숫자 N으로부터 +- 1씩 접근
     *
     * 엣지케이스
     * 1. N = 0
     * 2. 모든 채널이 다 고장
     * 3. decN에서 바로 break;하면 안됨
     * */


    // 0~9까지 고장여부
    public static boolean[] isBroken = new boolean[10];

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        if(M != 0) {
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++) {
                int btn = Integer.parseInt(st.nextToken());
                isBroken[btn] = true;
            }
        }


        // answer의 초기값 : 그냥 + 버튼이나 - 버튼만으로 N까지 가는 횟수
        int answer = Math.abs(100 - N);



        for(int i=0; i<=1000000; i++) {
            if(isPossible(i)) {
                int press = String.valueOf(i).length();
                int move = Math.abs(N-i);
                answer = Math.min(answer, press+move);
            }
        }




        System.out.println(answer);

    }


    public static boolean isPossible(int n) {
        if(n==0) {
            if(isBroken[0]) return false;
        }
        while(n>0) {
            int k = n%10;
            if(isBroken[k]) return false;
            n = n/10;
        }
        return true;
    }

}