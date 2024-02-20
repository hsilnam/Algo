import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            String cmd = temp[0];
            if (cmd.equals("all")) {
                set = new HashSet<>();
                for (int j = 1; j <= 20; j++) {
                    set.add(j);
                }
            } else if (cmd.equals("empty")) {
                set = new HashSet<>();
            } else {
                int num = Integer.parseInt(temp[1]);
                if (cmd.equals("add")) {
                    set.add(num);
                } else if (cmd.equals("remove")) {
                    set.remove(num);
                } else if (cmd.equals("check")) {
                    result.append((set.contains(num)) ? 1 : 0).append("\n");
                } else if (cmd.equals("toggle")) {
                    if (set.contains(num)) {
                        set.remove(num);
                    } else {
                        set.add(num);
                    }
                }
            }
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}