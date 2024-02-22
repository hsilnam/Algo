import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        StringBuilder result = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        int idx = 0;
        result.append("<");
        while (!list.isEmpty()) {
            idx = (idx + K - 1) % list.size();
            if (list.size() != N) {
                result.append(", ");
            }
            result.append(list.get(idx));
            list.remove(idx);
        }
        result.append(">");

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}