import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for (int i = 0; i < schedules.length; i++) {
            int schedule = schedules[i]+10;
            int h = schedule / 100;
            int m = schedule % 100;

            if (m >= 60) {
                h += 1;
                m = m % 60;
            }

            int limit = h * 100 + m;
            
            boolean isOk = true;
            
            for (int j = 0; j < 7; j++) {
                int currentDay = (startday + j - 1) % 7;
                
                if (currentDay >= 5){
                    continue;
                }
                
                if (timelogs[i][j] > limit) {
                    isOk = false;
                    break;
                }
            }
            
            if (isOk) {
                answer++;
            }
        }
        
        return answer;
    }
}
