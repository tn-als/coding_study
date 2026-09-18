package prob34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayProb34 {
    public static void main (String[] argv) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        String num = st.nextToken();

        st = new StringTokenizer(br.readLine());
        double movex = Double.parseDouble(st.nextToken());
        double movey = Double.parseDouble(st.nextToken());

        double twos = Math.pow(2,d);
        double beforex = 1;
        double beforey = 1;

        for (int i = 0; i < d; i++){
            char now = num.charAt(i);
            if (now == '2') {
                beforex += (twos/2);
            } else if (now == '4') {
                beforey += (twos/2);
            } else if (now == '3') {
                beforex += (twos/2);
                beforey += (twos/2);
            }
            twos /= 2;
        }

        twos = Math.pow(2,d);
        double newx = beforex - movex;
        double newy = beforey - movey;
        String answer = "";

        if (newx > twos || newy > twos || newx < 1 || newy < 1){
            System.out.println("-1");
            return;
        }

        for (int i = 0; i < d; i++){
            if (newx > (twos/2) && newy > (twos/2)){
                answer += "3";
                newx -= (twos/2);
                newy -= (twos/2);
            } else if (newx > (twos/2)){
                answer += "2";
                newx -= (twos/2);
            } else if (newy > (twos/2)){
                answer += "4";
                newy -= (twos/2);
            } else {
                answer += "1";
            }
            twos /= 2;
        }

        System.out.println(answer);

    }
}
