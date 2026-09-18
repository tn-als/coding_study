package prob20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayProb20 {

    private static boolean[] alphabet;
    private static String[] words;
    private static int N;
    private static int K;
    private static int[] picked;

    public static void main (String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // a, n, t, c, i
        if (K < 5) {
            System.out.println(0);
            return;
        }

        if (K == 26){
            System.out.println(N);
            return;
        }

        alphabet = new boolean[26];
        words = new String[N];
        picked = new int[K-5];
        int need = 0;

        // 필요한 알파벳 저장
        for (int i = 0; i < N; i++){
            String word = br.readLine();
            String excepted = word.substring(4, word.length()-4);
            words[i] = excepted;
            for (int j = 0; j < excepted.length(); j++){
                int idx = excepted.charAt(j) - 'a';
                if (idx == 0 || idx == 2 || idx == 8 || idx == 13 || idx == 19 || alphabet[idx]){continue;}
                alphabet[idx] = true;
                need++;
            }
        }

        if (need < K-5){
            System.out.println(N);
            return;
        }

        System.out.println(backtracking(0,0));
    }

    private static int backtracking(int nowNum, int idx) {
        if (nowNum == K - 5) {
            return checkMaxLetter();
        }

        int innerMax = 0;

        for (int i = idx; i < 26; i++){
            if (!alphabet[i]) {continue;}

            alphabet[i] = false;
            picked[nowNum] = i;
            int cnt = backtracking(nowNum + 1, i + 1);
            if (cnt > innerMax){innerMax = cnt;}
            alphabet[i] = true;
        }

        return innerMax;
    }

    private static int checkMaxLetter(){
        int count = 0;
        for (int i = 0; i < N; i++){
            String target = words[i];
            if(checkOneWord(target)){count++;}
        }
        return count;
    }

    private static boolean checkOneWord(String target){
        for (int i = 0; i < target.length(); i++){
            boolean no = false;
            int targetInt = target.charAt(i) - 'a';

            // a,c,t,i,n 일 경우 확인하지 않기
            if (!alphabet[targetInt]) {continue;}

            for (int j = 0; j < K-5; j++){
                if (picked[j] == targetInt){
                    no = true;
                    break;
                }
            }
            if (!no) {return false;}
        }
        return true;
    }
}
