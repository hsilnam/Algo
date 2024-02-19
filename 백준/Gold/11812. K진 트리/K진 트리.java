import java.util.*;
import java.io.*;

/*
- 각 위치에서 공통 부모를 기준으로 한 depth를 구하고 더하면 거리가 나온다
- N이 10^15이기 때문에 Long으로 받아야한다
- 노드의 위치 (K=3 일 때)
    - 깊이에 따른 노드 번호
        - 0: 1 / 3^0 (root)
        - 1: 2~4 / 3^0 + 3^1
        - 2: 5~13 / 3^0 + 3^1 + 3^2
        ...
        ===
        depth에 따른 노드 개수의 누적합 이하인 것

    - 자식 노드에 대한 부모 노드
        - 1: 2,3,4 => 0,1,2 (+2)
        - 2: 5,6,7 => 3,4,5 (+2)
        - 3: 8,9,10 => 6,7,8 (+2)
        ...
        ===
        부모 노드 idx = (현재 노드 idx-2)/K+1
        (현재 노드가 1인경우는 부모노드 1로 설정)

1. ((x의 깊이 + y의 깊이) - 2*(x,y의 공통 부모의 깊이))를 통해 노드의 거리를 구한다
    - 깊이를 구할 때,
    - 부모를 구할 때,
      서로의 깊이가 같아질 때까지 부모 노드를 구한 후,
      서로의 부모 노드가 같아질 때까지 부모 노드를 구한다.


[실패들]
- 70% (시간초과)
  => K=1 인 경우에는 일자로 되어있기 때문에 도 노드 사이의 거리를 바로 계산하면 된다
*/
public class Main {
    static long N;
    static int K;
    static HashMap<Long, Integer> depths = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Long.parseLong(temp[0]);
        K = Integer.parseInt(temp[1]);
        int Q = Integer.parseInt(temp[2]);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            temp = br.readLine().split(" ");
            long x = Long.parseLong(temp[0]);
            long y = Long.parseLong(temp[1]);
            result.append((K == 1) ? (Math.abs(x - y)) : getDist(x, y)).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static long getDist(long a, long b) {
        long dist = 0;

        int ad = getDepth(a);
        int bd = getDepth(b);

        if (bd < ad) { // 깊이가 큰게 무조건 오른쪽(b위치)으로 가게하기
            long tempA = a;
            int tempAd = ad;

            a = b;
            b = tempA;

            ad = bd;
            bd = tempAd;
        }

        while (ad != bd) {
            b = getParent(b);
            bd -= 1;
            dist++;
        }

        while (a != b) {
            a = getParent(a);
            b = getParent(b);
            dist += 2;
        }
        return dist;
    }

    public static long getParent(long num) {
        if (num == 1) {
            return 1;
        }
        return ((num - 2) / K) + 1;
    }

    public static int getDepth(long num) {
        int depth = 0;
        long lastIdx = 0;
        while (num > lastIdx) {
            lastIdx += Math.pow(K, depth);
            depth += 1;
        }
        return depth - 1;
    }
}