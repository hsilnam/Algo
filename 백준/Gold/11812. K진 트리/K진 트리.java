import java.util.*;
import java.io.*;

/*
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
        부모 노드번호 = (현재 노드번호-2)/K+1
        (현재 노드가 1인경우는 부모노드 1로 설정)

1. 공통 부모 노드를 찾으러 가는 과정에서의 거리를 통해 x,y 노드의 거리를 구한다
    - 깊이를 구할 때,
      depth에 따른 노드 개수의 누적합 이하일 떄의 마지막 depth
    - 부모를 구할 때,
      서로의 깊이가 같아질 때까지 부모 노드를 구한 후 (dist += 1, 한 노드만 이동을 하기 때문에),
      서로의 부모 노드가 같아질 때까지 부모 노드를 구한다. (dist += 2, 두 노드 전부 동시에 이동하기 때문에)


[실패들]
- 메모리 초과
  - 노드의 위치 (K=3 일 때)
    - 깊이에 따른, 왼쪽 첫번째 자식
        - 0: 1 (root)
        - 1: 2 (1 + 3^0);
        - 2: 5 (2 + 3^1);
        - 3: 14 (5 + 3^2);
        ...
        ===
        depthStart[0] = 1
        depthStart[d] = depthStart[d-1] + K^(d-1)
        ** d는 깊이

    - 자식 노드에 대한 부모 노드
        - 2: 1 (1 + (2-2)/3)
        - 3: 1 (1 + (3-2)/3);
        - 4: 1 (1 + (4-2)/3);
        - 5: 2 (2 + (5-5)/3);
        ...
        ===
        parent[1] = 1
        parent[n] = depthStart(nd-1) + (n - depthStart(nd))/K
        ** n은 노드 번호, nd는 n값의 깊이

    1. 입력값을 받고, 이를 이용하여 해당 트리의 depth의 시작 노드 번호를 구한다
        (N값을 포함할 수 있는 depth까지 구해 놓는다)
    2. ((x의 깊이 + y의 깊이) - 2*(x,y의 공통 부모의 깊이))를 통해 노드의 거리를 구한다
        - 깊이를 구할 때, 이진탐색을 이용한다
            (1번에서 미리 구해놓은 depth의 시작 노드 번호를 이용)
        - 부모를 구할 때,
          서로의 깊이가 같아질 때까지 부모 노드를 구한 후,
          서로의 부모 노드가 같아질 때까지 부모 노드를 구한다.
  => 원인
    1. depth를 구해서 사용하는 구간이 많다
      => 부모 노드번호 = (현재 노드번호-2)/K+1 공식을 이용하면 depth는 한번만 구하면 된다
    2. 사용되는 번호에 대해서 depth를 구할 때 들어가는 메모리 사용률이 높다
        - depth의 시작 노드 개수를 미리 ArrayList로 구해두는 것 + HashMap으로 해당 번호에 대한 depth를 저장해놓는 것
          => N이 크고 K가 작을 수록 메모리 사용률이 급격하게 올라간다
      => 원인1의 수정한 방식을 이용하면 depth는 x,y값에 대한 depth를 구할 때만 사용된다
      => depth에 따른 노드 개수의 누적합 이하일 떄의 마지막 depth로 구하는 방식으로 변경한다
    3. ((x의 깊이 + y의 깊이) - 2*(x,y의 공통 부모의 깊이))를 통해 구할 시
        - 중복으로 깊이를 조사하는 일이 발생한다
      => 공통 부모를 찾은 과정에서 거리를 계산하면 조사 및 계산 횟수를 줄일 수 있다


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
