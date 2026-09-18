package prob14;

import prob13.KsmPorb13;

import java.io.*;
import java.util.StringTokenizer;

public class KsmProb14 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 98 ~ 102번은 +, - 사용
    boolean[] remoteController = new boolean[10];
    String n;
    int min = Integer.MAX_VALUE;

    private void chanel() throws IOException {
        n = br.readLine();
        int m = Integer.parseInt(br.readLine());

        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            while (st.hasMoreTokens()) {
                String num = st.nextToken();
                remoteController[Integer.parseInt(num)] = true;
            }
        }

        int num = Integer.parseInt(n);
        switch (num) {
            case 98:
                min = 2;
                break;
            case 99:
                min = 1;
                break;
            case 100:
                min = 0;
                break;
            case 101:
                min = 1;
                break;
            case 102:
                min = 2;
                break;
            default:
                StringBuilder inputNum = new StringBuilder();
                inputNum.append(1).append(0).append(0);
                bruteforce(inputNum, 0);        // 100에서 시작해서 +, -만 누르는 경우

                for (int i = 0; i < 1000000; i++) {
                    inputNum = isValid(i);
                    if (inputNum.length() > 0) {      // 왜 백준에서 isEmpty() 쓰면 컴파일 에러 나지?
                        bruteforce(inputNum, inputNum.length());
                    }
                }
        }
        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
        br.close();
    }

    private void bruteforce(StringBuilder inputNum, int count) {
        int distance = Integer.parseInt(inputNum.toString()) - Integer.parseInt(n);
        if (distance >= 0) min = Math.min(min, count + distance);

        distance = Integer.parseInt(n) - Integer.parseInt(inputNum.toString());
        if (distance > 0) min = Math.min(min, count + distance);
    }

    private StringBuilder isValid(int num) {
        StringBuilder inputNum = new StringBuilder();
        String numStr = String.valueOf(num);
        for (int i = 0; i < numStr.length(); i++) {
            if (!remoteController[Integer.parseInt(numStr.charAt(i)+"")])
                inputNum.append(numStr.charAt(i));
            else {
                inputNum.setLength(0);
                break;
            }
        }

        return inputNum;
    }

    public static void main(String[] args) throws Exception {
        KsmProb14 main = new KsmProb14();
        main.chanel();

    }
}
