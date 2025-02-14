import java.util.*;

class Solution {
    public int solution(String[] storage, String[] requests) {
        int N = storage.length;
        int M = storage[0].length();
        
        final int[][] moves = {{-1,0},{1,0},{0,1},{0,-1}};
        
        
        int[][] map = new int[N+2][M+2]; // padding
        
        for(int i=0; i<N; i++) {
            String row = storage[i];
            for(int j=0; j<M;j++) {
                map[i+1][j+1] = row.charAt(j);
            }
        }
        
        int cnt = 0;
        for(String request: requests) {
            int c = request.charAt(0);
            int len = request.length();
            if(len > 1) {
                for(int i = 1; i < N+1; i++) {
                    for(int j = 1; j<M+1; j++) {
                        if(map[i][j] == c) {
                            cnt++;
                            map[i][j] = 0;
                        }
                    }
                }
            } else{
                boolean[][] visited = new boolean[N+2][M+2];
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{0,0});
                visited[0][0] = true;
                
                while(!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for(int[] move: moves) {
                        int nx = cur[0] + move[0];
                        int ny = cur[1] + move[1];
                        
                        if(nx <0 || nx >= N+2 || ny<0 || ny>=M+2) {
                            continue;
                        }
                        if(visited[nx][ny]) {
                            continue;
                        }
                        if(map[nx][ny] != c && map[nx][ny] != 0) {
                            continue;
                        }
                        if(map[nx][ny] == c) {
                            cnt++;
                            map[nx][ny] = 0;
                            visited[nx][ny] = true;
                            continue;
                        }
                        queue.offer(new int[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        
        int answer = N*M - cnt;
        
        return answer;
    }
}