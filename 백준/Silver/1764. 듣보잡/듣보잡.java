import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        Set<String> names = new HashSet<>();
        for (int i = 0; i < N; i++) {
            names.add(br.readLine());
        }

        ArrayList<String> unknowns = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (names.contains(name)) {
                unknowns.add(name);
            }
        }

        Collections.sort(unknowns);

        StringBuilder result = new StringBuilder();
        result.append(unknowns.size()).append("\n");
        for (String unknown:unknowns) {
            result.append(unknown).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}