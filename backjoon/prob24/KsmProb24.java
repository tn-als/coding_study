package prob24;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KsmProb24 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private void tieNum() throws Exception {
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> positives = new ArrayList<>();
        ArrayList<Integer> negatives = new ArrayList<>();
        int total = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) positives.add(num);   
            else if (num == 1) total++;           // 1은 바로 개수 셈
            else negatives.add(num);
        }

        Collections.sort(negatives);
        positives.sort(Collections.reverseOrder());

        // 1. 음수 처리
        for (int i = 0; i + 1 < negatives.size(); i += 2) {
            total += negatives.get(i) * negatives.get(i + 1);
        }
        // 홀수로 남은 음수는 그냥 더함
        if (negatives.size() % 2 == 1) total += negatives.get(negatives.size() - 1);

        // 2. 양수 처리
        for (int i = 0; i + 1 < positives.size(); i += 2) {
            total += positives.get(i) * positives.get(i + 1);
        }
        // 홀수로 남은 양수는 그냥 더함
        if (positives.size() % 2 == 1) total += positives.get(positives.size() - 1);

        bw.write(String.valueOf(total));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        KsmProb24 main = new KsmProb24();
        main.tieNum();
    }
}
