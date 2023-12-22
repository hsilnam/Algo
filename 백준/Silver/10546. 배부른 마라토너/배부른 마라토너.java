import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> peopleCnt = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            if (peopleCnt.containsKey(name)) {
                peopleCnt.put(name, peopleCnt.get(name) + 1);
            } else {
                peopleCnt.put(name, 1);
            }

        }

        for (int i = 0; i < N - 1; i++) {
            String name = br.readLine();
            if (peopleCnt.get(name) == 1) {
                peopleCnt.remove(name);
            } else if (peopleCnt.get(name) > 1) {
                peopleCnt.put(name, peopleCnt.get(name) - 1);
            }
        }

        for (String person : peopleCnt.keySet()) {
            System.out.println(person);
        }
    }

}