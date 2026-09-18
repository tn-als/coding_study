package prob29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DayProb29 {

    private static boolean[][] display;

    public static void main (String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        display = new boolean[N][N];

        for (int i = 0; i < N; i++){
            String line = br.readLine();
            for (int j = 0; j < N; j++){
                display[i][j] = (line.charAt(j) == '1');
            }
        }

        String result = dc(0,0, N);

        System.out.println(result);

    }

    private static String dc(int x1, int y1, int len){
        if (len == 1) {return display[y1][x1] ? "1" : "0";}

        int half = len/2;
        String con1 = dc(x1, y1, half);
        String con2 = dc(x1 + half, y1, half);
        String con3 = dc(x1, y1 + half, half);
        String con4 = dc(x1 + half, y1 + half, half);
        String conquered = con1 + con2 + con3 + con4;

        if (conquered.equals("1111")) {
            return "1";
        }
        else if (conquered.equals("0000")) {
            return "0";
        }

        return "(" + conquered + ")";
    }
}
