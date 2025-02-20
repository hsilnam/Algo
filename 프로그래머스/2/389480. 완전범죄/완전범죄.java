import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
         int itemCount = info.length;
        int[][][] dp = new int[itemCount + 1][n + 1][m + 1];

        for (int i = 0; i <= itemCount; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        
        dp[0][0][0] = 0;

        for (int i = 0; i < itemCount; i++) {
            int aTrace = info[i][0];
            int bTrace = info[i][1];
            
            for (int a = 0; a <= n; a++) {
                for (int b = 0; b <= m; b++) {
                    if (dp[i][a][b] == Integer.MAX_VALUE) {
                        continue;   
                    }
                    
                    int newA = Math.min(n, a + aTrace);
                    dp[i + 1][newA][b] = Math.min(dp[i + 1][newA][b], dp[i][a][b] + aTrace);

                    int newB = Math.min(m, b + bTrace);
                    dp[i + 1][a][newB] = Math.min(dp[i + 1][a][newB], dp[i][a][b]);
                }
            }
        }

        int minA = Integer.MAX_VALUE;
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (dp[itemCount][a][b] != Integer.MAX_VALUE) {
                    minA = Math.min(minA, dp[itemCount][a][b]);
                }
            }
        }

        return (minA == Integer.MAX_VALUE) ? -1 : minA;
    }   
}