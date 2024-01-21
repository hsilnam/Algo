/*
1. 두 큐의 전체 합을 구한다
2. 전체 합을 나눠서 짝수있지 확인한다
3. 각 큐를 연산에 용이하게 Queue에 넣어준다
4. 각 큐의 현재 합을 구한다
5. 합이 부족한 쪽에 숫자를 넘겨준다
6. 각 큐의 합이 같은 값이 나올 때까지 반복한다

---
실패들
1. 각 큐의 합은 int 값을 넘을 수 있으므로 long으로 해야한다
2. 무한루프(시간초과)가 돌지 않도록 cnt의 max값을 정해줘야한다
    - 예시: [1,2,4],[3,2,4]
        1: 7,9 => [1,2,4,3],[2,4]
        2: 10,6 => [2,4,3],[2,4,1]
        3: 9,7 => [4,3],[2,4,1,2]
        4: 7,9 => [4,3,2],[4,1,2]
        5: 9,7 => [3,2],[4,1,2,4]
        6: 5,11 => [3,2,4],[1,2,4]
        7: 9,7 => [2,4],[1,2,4,3]
        8: 6,10 => [2,4,1],[2,4,3]
        9: 7,9 => [2,4,1,2],[4,3]
        10: 9,7 => [4,1,2],[4,3,2]
        11: 7,9 => [4,1,2,4],[3,2]
        12: 5번째와 서로의 원소가 스위치된 상황 => 이런 상황 반복. 무한루프
    - 최대길이 설정: queue*3 (주어진 두 큐의 길이는 항상 같다)
*/

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        int total = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long q1Sum = 0;
        long q2Sum = 0;
        for(int num : queue1) {
            total += num;
            q1.offer(num);
            q1Sum += num;
        }
        for(int num : queue2) {
            total += num;
            q2.offer(num);
            q2Sum += num;
        }
        
        if(total % 2 == 1) { // 홀수는 불가능
            return -1;
        }

        int cnt = 0;
        int maxCnt = q1.size()*3;
        while(!q1.isEmpty() && !q2.isEmpty()) {
            if(q1Sum == q2Sum) {
                break;
            }
            if(cnt > maxCnt) {
                break;
            }
            if(q1Sum < q2Sum) {
                int temp = q2.poll();
                q1.offer(temp);
                q1Sum += temp;
                q2Sum -= temp;
            } else {
                int temp = q1.poll();
                q2.offer(temp);
                q1Sum -= temp;
                q2Sum += temp;
            }
            
            cnt++;
        }
        
        if(q1Sum != q2Sum) {
            return -1;
        }
        
        return cnt;
    }
}