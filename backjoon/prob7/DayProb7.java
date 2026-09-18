package prob7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DayProb7 {
    public static void main(String[] argv) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int div = n / 5;

        int five = -1;
        int three = -1;

        for (int i = div; i >= 0; i--){
            int rest = n - (5 * i);
            if ((rest / 3) * 3 == rest){
                five = i;
                three = rest / 3;
                break;
            }
        }

        if (five >= 0){
            System.out.println(five+three);
        } else{
            System.out.println(-1);
        }
    }
}
