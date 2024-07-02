import java.io.*;
import java.util.*;

/*
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int K = Integer.parseInt(temp[0]);
        int L = Integer.parseInt(temp[1]);

        LinkedHashSet<String> set = new LinkedHashSet<>();
        for (int i = 0; i < L; i++) {
            String student = br.readLine();
            if (set.contains(student)) {
                set.remove(student);
            }
            set.add(student);
        }

        StringBuilder result = new StringBuilder();
        int cnt = 0;
        for (String student : set) {
            if (cnt >= K) {
                break;
            }
            result.append(student).append("\n");
            cnt++;
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}