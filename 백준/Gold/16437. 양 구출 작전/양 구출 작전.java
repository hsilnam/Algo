import java.io.*;
import java.util.*;

/**

 */
public class Main {
    static ArrayList<Integer>[] graph;
    static long[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

         graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList();
        }

        nums = new long[N + 1];


        for (int i = 2; i <= N; i++) {
            String[] temp = br.readLine().split(" ");
            String t = temp[0];
            long a = Long.parseLong(temp[1]);
            int p = Integer.parseInt(temp[2]);

            if (t.equals("W")) { // 늑대
                a *= (-1);
            }
            nums[i] = a;

            graph[i].add(p);
            graph[p].add(i);
        }

        dfs(1, 0);

        bw.write(Long.toString(nums[1]));

        br.close();
        bw.close();
    }

    public static void dfs(int num, int parent) {
        for(int next: graph[num]) {
            if(next == parent) {
                continue;
            }
            dfs(next, num);

            nums[num] += nums[next];
        }
        nums[num] = Math.max(0L, nums[num]);
    }
}