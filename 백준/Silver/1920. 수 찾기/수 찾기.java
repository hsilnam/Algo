import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(temp[i]));
        }
        
        int M = Integer.parseInt(br.readLine());
        temp = br.readLine().split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            result.append((set.contains(Integer.parseInt(temp[i]))) ? 1 : 0).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}