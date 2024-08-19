import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, Integer> map = new TreeMap<>();
        int total = 0;
        String name;
        while ((name = br.readLine()) != null) {
            map.put(name, map.getOrDefault(name, 0) + 1);
            total++;
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            result.append(entry.getKey())
                    .append(" ")
                    .append(String.format("%.4f", (double) entry.getValue() / (double) total * 100.0))
                    .append("\n");
        }

        bw.write(result.toString());


        br.close();
        bw.close();
    }
}