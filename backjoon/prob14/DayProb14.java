package prob14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayProb14 {

    private static boolean[] remote = new boolean[10];
    private static String goal;

    public static void main(String[] argv) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        goal = br.readLine();
        int goalInt = Integer.parseInt(goal);
        int N = Integer.parseInt(br.readLine());

        if (N > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++){
                int broken = Integer.parseInt(st.nextToken());
                remote[broken] = true;
            }
        }

        if (goalInt == 100) {
            System.out.println("0");
        } else if (N == 10) {
            System.out.println(Math.abs(100 - goalInt));
        } else {
            int moveInt = minClick2(goalInt);
            String result = moveInt + "";

            int resultLen = result.length();
            System.out.println(Math.min(resultLen + Math.abs(moveInt - goalInt), Math.abs(100 - goalInt)));
        }
    }

    private static int minClick2(int goalInt){
        int plus = 0;
        int minus = 0;
        while(true){
            if (goalInt + minus >= 0 && move(goalInt + minus--)){
                return goalInt + (minus + 1);
            } else if (goalInt + plus <= 1000000 && move(goalInt + plus++)){
                return goalInt + (plus - 1);
            }
        }
    }

    private static boolean move(int moved){
        if (moved == 0) {return !remote[0];}
        int target = moved;
        while (target > 0){
            if (remote[target%10]) {return false;}
            target /= 10;
        }
        return true;
    }

}