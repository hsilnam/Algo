import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // get input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        Map<Integer, Integer> moves = new HashMap<>();
        for (int i = 0; i < N + M; i++) {
            temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]) - 1;
            int end = Integer.parseInt(temp[1]) - 1;
            moves.put(start, end);
        }

        // bfs init
        Queue<int[]> queue = new ArrayDeque<>(); // 위치, 주사위 던진 수
        queue.add(new int[]{0, 0});
        int[] rollCnts = new int[100]; // 목표: 0->99로 가기

        // bfs
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int idx = cur[0];
            int rollCnt = cur[1];

            int nRollCnt = rollCnt + 1;
            for (int i = 1; i <= 6; i++) { // 주사위
                int nIdx = idx + i;
                if (nIdx >= 100) { // 범위를 넘어가면
                    continue;
                }
                if (rollCnts[nIdx] > 0 && rollCnts[nIdx] <= nRollCnt) { // 주사위 던진 횟수가 처음이 아닌데 값이 기존 것보다 같거나 크면
                    continue;
                }
                rollCnts[nIdx] = nRollCnt;
                queue.add(new int[]{(!moves.containsKey(nIdx))?nIdx:moves.get(nIdx), nRollCnt}); // 사다리나 뱀이있으면 자리 옮기기
            }
        }

        // result
        System.out.println(rollCnts[99]);
    }

}