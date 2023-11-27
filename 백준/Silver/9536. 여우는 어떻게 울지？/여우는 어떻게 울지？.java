import java.util.*;
import java.io.*;

/*
# -> 1
. -> 0
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String[] speaks = br.readLine().split(" ");
            HashSet otherAnimalSet = new HashSet();
            String check = br.readLine();
            while (!"what does the fox say?".equals(check)) {
                String[] temp = check.split(" ");
                otherAnimalSet.add(temp[2]);
                check = br.readLine();
            }

            for (String speak : speaks) {
                if (otherAnimalSet.contains(speak)) {
                    continue;
                }
                result.append(speak).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result.toString());
    }
}