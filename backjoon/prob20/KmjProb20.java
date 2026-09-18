package prob20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KmjProb20 {
    private static int K;
    private static int[] words;

    private static int maxRead = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new int[N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                int bitIdx = line.charAt(j) - 'a';
                words[i] |= 1 << bitIdx;
            }
        }

        int taught = 0;
        taught = taught | 1;
        taught |= 1 << ('n' - 'a');
        taught |= 1 << ('t' - 'a');
        taught |= 1 << ('i' - 'a');
        taught |= 1 << ('c' - 'a');

        if (K < 5) System.out.print(0);
        else {
            teach(0, taught, 0);
            System.out.print(maxRead);
        }

        br.close();
    }

    private static void teach(int cnt, int taught, int start) {
        if (cnt == K - 5) {
            int readCnt = 0;
            for (int word : words) {
                if ((word | taught) == taught) readCnt++;
            }
            maxRead = Math.max(readCnt, maxRead);
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((taught & (1 << i)) == 0) {
                teach(cnt + 1, taught | (1 << i), i + 1);
            }
        }
    }
}
