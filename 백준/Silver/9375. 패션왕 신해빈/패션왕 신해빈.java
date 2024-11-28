import java.io.*;
import java.util.*;

/*
HashMap 이용
- type에 맞춰서 종류 카운트
- 알몸이 아닌 옷들의 조합:
    - 각 종류마다, 안입은 경우 + 같은 종류의 의상 중 하나 고르는 경우가 있다
        = (1+iC1)
    - 의상들의 조합은 곱과 같다
        = (1+aC1) * (1+bC1) * ...
    - 전부 안입는 경우가 1개가 나오므로 -1을 한다
        = (1+aC1) * (1+bC1) * ... -1
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split(" ");
                map.put(temp[1], map.getOrDefault(temp[1], 0) + 1);
            }

            int comb = 1;
            for (int cnt : map.values()) {
                comb *= (cnt + 1);
            }
            comb -= 1;

            answer.append(comb).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}