import java.io.*;
import java.util.*;

/*
입력
- K(1<=K<=10): 깊이
- 상근이가 방문한 빌등의 번호들 (중복 X, 구간 1,2^K에 포함)
- 깊이가 K인 완전이진트리 (2^K-1개의 노드)
    - 노드: 빌딩의 번호 붙여짐
  => 노드 개수 최대 2^10-1 = 배열로 만들수 있음

로직
- 완전이진트리 일차원 배열로 값 저장
- 동작 순서
    1. 루트부터 시작
    2. 왼쪽에 들어가지 않았다면 왼쪽 자식으로 이동
    3. 왼쪽자식이 없거나, 왼쪽 자식에 방문했으면 현재 노드에 들어가고 종이에 적기 (적기)
    4. 현재 빌딩 이미 들어왔고, 오른쪽 자식을 가지고 있으면 오른쪽 자식으로 이동
    5. 현재, 왼쪽, 오른쪽 모두 방문했으면 부모 노드로 이동
    => 중위순회 (왼탐색, 가운데 방문, 오탐색)
출력
- 들어간 순서대로 번호 종이에 적음
    - K개 줄에걸쳐서 정답 출력
    - i번재줄에는 레벨이 i인 빌딩 번호 출력

 */

public class Main {

    public static int[] tree;
    public static Queue<Integer> visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine());
        tree = new int[(int) Math.pow(2, K)]; // root 1부터 시작
        visited = new ArrayDeque<>();
        String[] temp = br.readLine().split(" ");
        for (String t : temp) {
            visited.offer(Integer.parseInt(t));
        }

        explore(1);

        // 레벨별로 빌딩 순서대로 출력
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < K; i++) {
            for (int j = (int) Math.pow(2, i); j < (int) Math.pow(2, i + 1); j++) {
                answer.append(tree[j]).append(" ");
            }
            answer.append("\n");
        }
        bw.write(answer.toString());

        br.close();
        bw.close();
    }


    public static void explore(int idx) {
        if(visited.isEmpty() || tree.length <= idx ) {
            return;
        }
        explore(idx * 2);
        tree[idx] = visited.poll();
        explore(idx * 2 + 1);
    }

}