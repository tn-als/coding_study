package prob21;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class KsmProb21 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[][] meeting;

    private void meetingRoom() throws IOException {
        int n = Integer.parseInt(br.readLine());

        meeting = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meeting[i][0] = start;
            meeting[i][1] = end;
        }

        Arrays.sort(meeting, (a, b)->{
            if(a[1]!=b[1]) return Integer.compare(a[1],b[1]);
            return Integer.compare(a[0],b[0]);
        });

        int count = 0;
        int endTime = -1;
        for (int[] reservation : meeting) {
            int start = reservation[0];
            int end = reservation[1];

            if(endTime>start) continue;
            endTime = end;
            count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        KsmProb21 main = new KsmProb21();
        main.meetingRoom();
    }
}
