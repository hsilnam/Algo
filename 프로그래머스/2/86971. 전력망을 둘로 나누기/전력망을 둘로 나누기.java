/*
- 트리 형태라 #1이 root이다.
1. 양방향 그래프를 만들어준다
2. 간선을 하나씩 끊어가면서 끊은 쪽의 한쪽에 BFS검사를 한다
    - 해당 노드에서 뻗어나가는 개수 센다
      (주의: 송전탑의 개수를 세는 것임으로 자기자신을 포함한 cnt=1로 초기화해야한다)
3. 송전탑 개수 차이 (|개수 - (전체개수 - 개수)| = |2*개수-전체개수|) 값들 중 최소 값을 구한다
4. 최소 값을 리턴한다
 */

import java.util.*;
class Solution {
    public int solution(int n, int[][] wires) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0 ; i < n ; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires) {
            int a = wire[0]-1; // 번호 시작 0으로 맞춰주기
            int b = wire[1]-1; // 번호 시작 0으로 맞춰주기
            graph[a].add(b);
            graph[b].add(a);
        }
        
        int min = Integer.MAX_VALUE;
        for(int[] wire : wires) {
            int start = wire[0]-1; // 번호 시작 0으로 맞춰주기
            int block = wire[1]-1; // 번호 시작 0으로 맞춰주기
            
            int[] cnts = new int[n];
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new ArrayDeque<>();
            visited[start] = true;
            visited[block] = true;
            queue.offer(start);

            int cnt = 1;
            while(!queue.isEmpty()) {
                int cur = queue.poll();

                for(int next:graph[cur]) {
                    if(visited[next]) {
                        continue;
                    }
                    cnt++;
                    queue.offer(next);
                    visited[next] = true;
                }
            }
            min = Math.min(min, Math.abs(2*cnt-n));
        }
        return min;
    }    
}