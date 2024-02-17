import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        HashMap<String, Integer> nameInfo = new HashMap<>();
        HashMap<Integer, String> numInfo = new HashMap<>();
        for (int i = 1; i < N + 1; i++) {
            String name = br.readLine();
            nameInfo.put(name, i);
            numInfo.put(i, name);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            if(isDigit(input)) {
                result.append(numInfo.get(Integer.parseInt(input)));
            } else {
                result.append(nameInfo.get(input));
            }
            result.append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static boolean isDigit(String str) {
        return ("A".compareTo(str) > 0);
    }
}