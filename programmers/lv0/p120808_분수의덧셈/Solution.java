package programmers.lv0.p120808_분수의덧셈;

import java.io.*;
import java.util.Arrays;

public class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int denom = GCD(denom1, denom2);
        int numer = (denom / denom1 * numer1) + (denom / denom2 * numer2);

        int gcd = GCD(numer, denom);
        return new int[]{numer / gcd, denom / gcd};
    }

    public int GCD(int a, int b) {
        return a % b == 0 ? b : GCD(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Solution sol = new Solution();

        int numer1 = Integer.parseInt(br.readLine());
        int denom1 = Integer.parseInt(br.readLine());
        int numer2 = Integer.parseInt(br.readLine());
        int denom2 = Integer.parseInt(br.readLine());
        int[] result = sol.solution(numer1, denom1, numer2, denom2);

        bw.write(Arrays.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
