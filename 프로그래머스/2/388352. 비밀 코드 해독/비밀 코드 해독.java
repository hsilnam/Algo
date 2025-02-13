import java.util.*;

class Solution {
    private static List<Set<Integer>> possibleCodes = new ArrayList<>();
    
    public int solution(int n, int[][] q, int[] ans) {
        possibleCodes.clear();
        comb(new HashSet<>(), 1, n);
        
        int validCount = 0;
        for (Set<Integer> code : possibleCodes) {
            if (isValidCode(code, q, ans)) {
                validCount++;
            }
        }
        
        return validCount;
    }
    
    private static void comb(Set<Integer> current, int start, int n) {
        if (current.size() == 5) {
            possibleCodes.add(new HashSet<>(current));
            return;
        }
        for (int i = start; i <= n; i++) {
            current.add(i);
            comb(current, i + 1, n);
            current.remove(i);
        }
    }
    
    private static boolean isValidCode(Set<Integer> code, int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int matchCount = 0;
            for (int num : q[i]) {
                if (code.contains(num)) {
                    matchCount++;
                }
            }
            if (matchCount != ans[i]) {
                return false;
            }
        }
        return true;
    }
}
