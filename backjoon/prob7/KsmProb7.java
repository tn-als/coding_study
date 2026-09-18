package prob7;

import java.io.*;

public class KsmProb7 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private void deliverSuger() throws IOException {
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        count += n / 5;
        int remain = n % 5;

        if(remain%3==0) count+=remain/3;
        else{
            if(count==0){
                if(remain%3==0) count+=remain/3;
                else count=-1;
            }else{
                while(true){
                    count--;
                    remain+=5;
                    if(remain%3==0) {
                        count += remain / 3;
                        break;
                    }
                    if(count==0) {
                        count = -1;
                        break;
                    }
                }
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        KsmProb7 main = new KsmProb7();
        main.deliverSuger();
    }
}
