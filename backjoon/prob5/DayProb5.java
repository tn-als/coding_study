package prob5;

import java.io.*;

public class DayProb5 {

    private static int[] count1 = new int[41];
    private static int[] count0 = new int[41];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int[] Ns = new int[T];
        int Nmax = 0;

        for (int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            Ns[i] = N;
            if (N > Nmax) {
                Nmax = N;
            }
        }

        count1[0] = 0;
        count0[0] = 1;
        count1[1] = 1;
        count0[1] = 0;
        for (int i = 2; i <= Nmax; i++){
            count1[i] = count1[i-2] + count1[i-1];
            count0[i] = count0[i-2] + count0[i-1];
        }

        for (int i = 0; i < T; i++){
            sb.append(count0[Ns[i]]).append(" ").append(count1[Ns[i]]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
