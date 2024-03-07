import java.util.*;
import java.io.*;

/*
- TreeMap 사용
    - 키를 정렬된 순서로 저장
    - firstEntry(), lastEntry()라는 메소드를 지원헤서 최소, 최대값을 추출하기 좋음
    - 이중 우선순위 큐를 대체할 수 있음
        (** Priority Queue는 Heap으로 구현되어 있고
          최상단 루트를 통해서 최대/최소값을 알 수 있지만,
          최상단 루트를 제외한 다른 노드들에 대한 우선순위의 순서는 보장되지 않는다
          그래서 값 추출하는 메서드도 poll() 밖에 없음)
- TreeMap의 key로는 숫자값, value로는 해당 숫자의 개수를 저장하여(중복 개수),
    넣어주고 빼낼 때 개수를 줄이고 늘려서, 개수가 0이되면 사라지게 만든다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> treeMap = new TreeMap();

            for (int k = 0; k < K; k++) {
                String[] temp = br.readLine().split(" ");
                String cmd = temp[0];
                int num = Integer.parseInt(temp[1]);
                if (cmd.equals("I")) {
                    treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
                } else if (cmd.equals("D")) {
                    if (treeMap.isEmpty()) {
                        continue;
                    }
                    int out = (num == 1) ? treeMap.lastKey() : treeMap.firstKey(); // max, min
                    if (treeMap.get(out) == 1) { // 하나 남은 것을 뺀 경우
                        treeMap.remove(out);
                    } else {
                        treeMap.put(out, treeMap.get(out) - 1);
                    }
                }
            }
            if (treeMap.isEmpty()) {
                result.append("EMPTY").append("\n");
            } else {
                result.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
            }
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}