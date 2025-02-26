import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] buildTime = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
            }
            
            List<Integer>[] adj = new ArrayList[N + 1];
            int[] indegree = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                adj[X].add(Y);
                indegree[Y]++;
            }
            
            int W = Integer.parseInt(br.readLine());
            
            int[] result = new int[N + 1];
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                result[i] = buildTime[i];
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }
            
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next : adj[cur]) {
                    result[next] = Math.max(result[next], result[cur] + buildTime[next]);
                    
                    indegree[next] -= 1;
                    if (indegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
            
            bw.write(result[W] + "\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
}