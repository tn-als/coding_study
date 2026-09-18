package prob31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayProb31 {
    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int len = (int) Math.pow(2, N);

        int result = dq(len, r, c);

        System.out.print(result);
    }

    private static int dq(int len, int r, int c){
        if (len == 1) {return 0;}

        int pivot = len / 2;
        int square = (int) Math.pow(pivot, 2);

        if (r < pivot && c < pivot) { // Z 첫경로
            return dq(pivot, r, c);
        } else if (r < pivot && c >= pivot) { // Z 2경로
            return square + dq(pivot, r , c - pivot);
        } else if (r >= pivot && c < pivot) { // Z 3경로
            return 2 * square + dq(pivot, r - pivot, c);
        } else { // Z 4경로
            return 3 * square + dq(pivot, r - pivot, c - pivot);
        }

    }
}
