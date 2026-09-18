package prob31;

import java.io.*;
import java.util.StringTokenizer;

public class KsmProb31 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int r, c;
    int count;

    private void Z() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int length = (1 << n) - 1;

        division(0, length, 0, length);
        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }

    private void division(int startX, int endX, int startY, int endY) {
        if (endX - startX == 1) {
            if (r == startY && c == endX) {
                count++;
            } else if (r == endY && c == startX) {
                count += 2;
            } else if (r == endY && c == endX) {
                count += 3;
            }
            return;
        }

        int midX = (startX + endX) / 2;
        int midY = (startY + endY) / 2;
        int block = (midX - startX + 1) * (midX - startX + 1);

        // 위쪽
        if (r >= startY && r <= midY) {
            // 왼쪽
            if (c >= startX && c <= midX) {
                division(startX, midX, startY, midY);
            }
            // 오른쪽
            else {
                count += block;
                division(midX + 1, endX, startY, midY);
            }
        }
        // 아래쪽
        else {
            // 왼쪽
            if (c >= startX && c <= midX) {
                count += 2 * block;
                division(startX, midX, midY + 1, endY);
            }
            // 오른쪽
            else {
                count += 3 * block;
                division(midX + 1, endX, midY + 1, endY);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        KsmProb31 main = new KsmProb31();
        main.Z();
    }
}