package prob19;

import java.io.*;
import java.util.StringTokenizer;

// 참가번호 0부터 시작
// 짝수 명 남았을 때는 밤
// 홀수 명 남았을 때는 낮
// 마피아는 한명

// 밤에는 아무나 죽임
// 낮에는 유죄 지수 높은 사람 죽음
// 유죄 지수 높은 사람이 여러 명이면 번호가 낮은 사람이 죽음
public class KsmProb19 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, eunjin;
    /*ArrayList<int[]> guilty = new ArrayList<>();*/
    int[][] r;
    int[] guilty;

    private void mafia() throws IOException {
        n = Integer.parseInt(br.readLine());
        int alive = (1 << n) - 1;       // 1이 n개 있음
        guilty = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            //guilty.add(new int[]{i, Integer.parseInt(st.nextToken())});
            guilty[i] = Integer.parseInt(st.nextToken());
        }

        r = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                r[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        eunjin = Integer.parseInt(br.readLine());

        if (n == 1) bw.write("0");
        else bw.write(String.valueOf(playGame(0, -1, alive)));
        bw.flush();
        bw.close();
        br.close();
    }

    private int playGame(int days, int deathNum, int alive) {
        // 두 명 남으면 은진 이김
        // 은진이 죽으면 시민 이김
        int alivecount = Integer.bitCount(alive);
        if (deathNum == eunjin) return days;
        else if (alivecount == 2) {
            return days + 1;
        }

        int maxDays = 0;

        // 밤
        // 반복 o
        if (alivecount % 2 == 0) {
            days++;
            for (int i = 0; i < n; i++) {
                if (guilty[i] == -200 || i == eunjin) continue;

                int guiltNum = guilty[i];
                guilty[i] = -200;
                alive ^= (1 << i);

                maxDays = Math.max(maxDays, playGame(days, i, alive));

                guilty[i] = guiltNum;
                alive ^= (1 << i);
            }
        }
        // 낮 -> 이전에 밤이었음
        // 반복 x
        else {
            int max = 0;
            int deathPerson = 0;

            // 유죄 지수 변화
            for (int i = 0; i < n; i++) {
                // 게임이 진행된 상태일 때
                int guiltNum = guilty[i];
                if (deathNum != -1) {
                    if (guilty[i] == -200) continue;
                    guilty[i] += r[deathNum][i];
                    guiltNum = guilty[i];
                }

                if (max < guiltNum) {
                    max = guiltNum;
                    deathPerson = i;
                }
            }

            guilty[deathPerson] = -200;
            alive ^=(1<<deathPerson);

            maxDays = playGame(days, deathPerson, alive);

            if (deathNum != -1) {
                for (int i = 0; i < n; i++) {
                    if (i == deathPerson) {
                        guilty[i] = max - r[deathNum][i];
                        continue;
                    }
                    if (guilty[i] == -200) continue;
                    guilty[i] -= r[deathNum][i];
                }
            }
        }

        return maxDays;
    }

    public static void main(String[] args) throws Exception {
        KsmProb19 main = new KsmProb19();
        main.mafia();
    }
}
