package prob21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DayProb21 {
    public static void main (String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int [][] timetable = new int[N][2];
        int lastEnd = 0;
        int count = 0;

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            timetable[i][0] = Integer.parseInt(st.nextToken());
            timetable[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(timetable, (a,b) -> {
            if (a[1] == b[1]) {return a[0] - b[0];}
            return a[1] - b[1];
        });

        for (int i = 0; i < N; i++){
            if (timetable[i][0] >= lastEnd) {
                count++;
                lastEnd = timetable[i][1];
            }
        }

        System.out.println(count);

    }
}
