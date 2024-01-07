import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            char[] commands = br.readLine().toCharArray();
            int N = Integer.parseInt(br.readLine());
            ArrayList<Integer> list = new ArrayList<>();

            StringBuilder numSb = new StringBuilder();
            for (char c : br.readLine().toCharArray()) {
                if (c == '[' || c == ',' || c == ']') {
                    if (numSb.length() == 0) {
                        continue;
                    }
                    list.add(Integer.parseInt(numSb.toString()));

                    numSb = new StringBuilder();
                } else { // 숫자인 경우
                    numSb.append(c);
                }
            }

            boolean isError = false;
            boolean toggle = true; // 앞: true / 뒤: false;
            for (char cmd : commands) {
                if (cmd == 'R') {
                    toggle = !toggle;
                } else if (cmd == 'D') {
                    if (list.size() == 0) {
                        isError = true;
                        break;
                    }
                    int idx = (toggle) ? 0 : list.size() - 1;
                    list.remove(idx);
                }
            }
            
            StringBuilder sb = new StringBuilder();
            if (isError) {
                sb.append("error");
            } else {
                sb.append("[");
                for (int i = 0; i < list.size(); i++) {
                    int idx = (toggle) ? i : list.size() - i - 1;
                    sb.append(list.get(idx));
                    if (i != list.size() - 1) {
                        sb.append(",");
                    }
                }
                sb.append("]");
            }
            result.append(sb.toString()).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}