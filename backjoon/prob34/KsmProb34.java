package prob34;

import java.io.*;
import java.util.StringTokenizer;

public class KsmProb34 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringBuilder sb;

    private void quadrant() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        String num = st.nextToken();
        sb = new StringBuilder(num);

        st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());

        long size = 1L << num.length();
        long[] location = getLocation(0, num.length() - 1, 0, 0, size);

        location[0] += x;
        location[1] -= y;

        if (location[0] >= size || location[1] >= size || location[0] < 0 || location[1] < 0) bw.write("-1");
        else bw.write(getQuadrant(0, 0, location, size, new StringBuilder()));
        bw.flush();
        bw.close();
        br.close();
    }

    private long[] getLocation(int index, int maxIndex, long currX, long currY, long length) {
        int n = sb.charAt(index) - '0';
        long mid = length / 2;
        switch (n) {
            case 1:
                currX += mid;
                break;
            case 3:
                currY += mid;
                break;
            case 4:
                currX += mid;
                currY += mid;
        }

        if (index == maxIndex) {
            return new long[]{currX, currY};
        }
        return getLocation(index + 1, maxIndex, currX, currY, mid);
    }

    private String getQuadrant(long currX, long currY, long[] goal, long length, StringBuilder s) {
        if (length == 1) {
            return s.toString();
        }

        long mid = length / 2;

        // 1사분면
        if (goal[0] >= currX + mid && goal[1] < currY + mid) {
            currX += mid;
            s.append("1");
            return getQuadrant(currX, currY, goal, mid, s);
        }
        // 2사분면
        else if (goal[0] < currX + mid && goal[1] < currY + mid) {
            s.append("2");
            return getQuadrant(currX, currY, goal, mid, s);
        }
        // 3사분면
        else if (goal[0] < currX + mid && goal[1] >= currY + mid) {
            currY += mid;
            s.append("3");
            return getQuadrant(currX, currY, goal, mid, s);
        }
        // 4사분면
        else {
            currX += mid;
            currY += mid;
            s.append("4");
            return getQuadrant(currX, currY, goal, mid, s);
        }
    }

    public static void main(String[] args) throws Exception {
        KsmProb34 main = new KsmProb34();
        main.quadrant();
    }
}
