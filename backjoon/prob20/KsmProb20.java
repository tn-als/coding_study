package prob20;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class KsmProb20 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int character = 0b00000000000000000000000000;//(1<<26)-1;      // 1: 읽을 수 없음, 0: 읽을 수 있음
    int k;
    int[] words;

    private void teach() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (k < 5) {
            for (int i = 0; i < n; i++) {
                br.readLine();
            }
            bw.write("0");
        } else {
            words = new int[n];
            // a,n,t,i,c 먼저 읽을 수 있도록 처리
            character ^= (1 << (0));
            character ^= (1 << ('c' - 'a'));
            character ^= (1 << ('i' - 'a'));
            character ^= (1 << ('n' - 'a'));
            character ^= (1 << ('t' - 'a'));

            for (int i = 0; i < n; i++) {
                String input = br.readLine().replace("anta", "").replace("tica", "");

                int bit = 0b00000000000000000000000000;
                for (int j = 0; j < input.length(); j++) {
                    int index = input.charAt(j) - 'a';
                    bit |= (1 << index);
                }
                words[i] = bit;
            }

            bw.write(String.valueOf(learn(1, 5)));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int learn(int startIndex, int count) {
        if (count == k) {
            // 읽을 수 있는 단어 개수 세기
            int readable = 0;
            for (int i = 0; i < words.length; i++) {
                if ((words[i] & character) == words[i]) readable++;
            }
            return readable;
        }

        int max = 0;
        for (int i = startIndex; i < 26; i++) {
            // 읽을 수 있음
            if ((character & (1 << i)) == 1) continue;

            // 1 -> 0
            character ^= (1 << i);

            max = Math.max(max, learn(i + 1, count + 1));

            // 1 -> 0
            character ^= (1 << i);
        }

        return max;
    }

    public static void main(String[] args) throws Exception {
        KsmProb20 main = new KsmProb20();
        main.teach();
    }
}
