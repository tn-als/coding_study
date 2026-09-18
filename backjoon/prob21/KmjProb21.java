package prob21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class KmjProb21 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] meetings = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meetings, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int cnt = 0;
        int prevEnd = -1;
        for (int i = 0; i < N; i++) {
            if (prevEnd <= meetings[i][0]) {
                cnt++;
                prevEnd = meetings[i][1];
            }
        }
        System.out.print(cnt);
        br.close();
    }
}
