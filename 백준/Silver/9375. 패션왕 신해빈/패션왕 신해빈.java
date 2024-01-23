import java.util.*;
import java.io.*;


/*
1. HashMap을 이용하여 옷의 종류별로 갯수를 센다
2. 각 옷 종류에 대하여 하나씩 뽑히는 경우를 센다
    - (iC1+1)+(jC1)+...
    - 여기서 1을 더해주는 것은 아예 그 종류를 안입었을 경우를 뜻함
3. 마지막에 아무것도 안입은 경우(알몸)을 뺀다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            HashMap<String, Integer> cloths = new HashMap<>();
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split(" ");
                cloths.put(temp[1], cloths.getOrDefault(temp[1], 0) + 1);
            }

            int cnt = 1;
            for (int cloth : cloths.values()) {
                cnt *= (cloth + 1);
            }

            result.append(cnt - 1).append("\n");
        }

        bw.write(result.toString());
        br.close();
        bw.close();
    }
}