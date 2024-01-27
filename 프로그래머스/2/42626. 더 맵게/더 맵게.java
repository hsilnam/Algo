/*
PriorityQueue
- Heap (균형 이진 트리)
- offer: O(logN)
- poll: O(logN)

Arrays.sort()
- DualPivotQuicksort
- 평균: O(NlogN)
- 최악: O(N^2)

Collections.sort()
- TimeSort (삽입정렬 + 합병정렬)
- 평균: O(NlogN)
- 최악: O(NlogN)

-------
방법
1. PriorityQueue 사용하기
2. Min Heap 구현하기

*/

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        for(int scv : scoville) {
            heap.offer(scv);
        }
        
        int cnt = 0;
        while(heap.peek() < K) {
            int first = heap.poll();
            if(heap.isEmpty()) {
                return -1;
            }
            int second = heap.poll();
            heap.offer(first + (second*2));
            cnt++;
        }
        
        return cnt;
    }
}