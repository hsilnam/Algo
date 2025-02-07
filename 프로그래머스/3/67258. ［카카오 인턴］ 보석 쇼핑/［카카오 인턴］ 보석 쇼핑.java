import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] gems) {
        HashSet<String> gemTypes = new HashSet<>(Arrays.asList(gems)); 
        int totalTypes = gemTypes.size();
        int minLen = Integer.MAX_VALUE;
        
        Map<String, Integer> gemCnt = new HashMap<>();
        int left = 0, right = 0;
        int[] answer = {0, gems.length};

        while (right < gems.length) {
            
            gemCnt.put(gems[right], gemCnt.getOrDefault(gems[right], 0) + 1);
            right++;

            while (gemCnt.size() == totalTypes) {
                if (right - left < minLen) {
                    minLen = right - left;
                    answer[0] = left + 1;
                    answer[1] = right;
                }

                gemCnt.put(gems[left], gemCnt.get(gems[left]) - 1);
                if (gemCnt.get(gems[left]) == 0) {
                    gemCnt.remove(gems[left]);
                }
                left++;
            }
        }
        return answer;
    }
}