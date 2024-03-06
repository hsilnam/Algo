import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");

        int[] arr = new int[N];
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(temp[i]);
            arr[i] = num;
            if (!set.contains(num)) {
                list.add(num);
            }
            set.add(num);
        }

        Collections.sort(list);

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            result.append(map.get(arr[i])).append(" ");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}