package programmers.hash.p42576_완주하지못한선수;

import java.io.*;
import java.util.*;

public class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();

        for(int i=0; i<participant.length; i++){
            hm.put(participant[i], hm.getOrDefault(participant[i], 0) + 1);
        }

        for(int i=0; i<completion.length; i++){
            hm.replace(completion[i], hm.get(completion[i])-1);
        }

        for(String name: hm.keySet()){
            if(hm.get(name).equals(0)) continue;
            answer=name;
            break;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        programmers.hash.p42576_완주하지못한선수.Solution sol = new programmers.hash.p42576_완주하지못한선수.Solution();
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        bw.write(sol.solution(participant, completion));
        bw.flush();
        bw.close();
        br.close();
    }
}
