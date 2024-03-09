import java.util.*;
import java.io.*;

/*
hashmap 사용
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(temp[i]);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int M = Integer.parseInt(br.readLine());
        temp = br.readLine().split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(temp[i]);
            result.append(map.getOrDefault(num,0)).append(" ");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}