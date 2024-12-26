import java.io.*;
import java.util.*;

/*
입력
- N: 트리정점의 수 (2<=N<=10^5)
- R: 루트의 번호 (1<=R<=N)
- Q: 쿼리의 수 (1<=Q<=10^5)
- U,V: 간선 정보 (1<=U,V<=N, U!=V, 1<=U<=N)
- 올바른 트리 보장

조건
- 양방향
- 가중치 없음

풀이
- 양방향 그래프의 간선의 정보를 저장한다
- 루트가 R인 tree를 만든다
    - R부터 시작하여 간선 정보를 통해 연결되는 노드들의 parent, children 정보를 저장한다
- 맨 아래의 리프노드들부터 subtree의 개수를 세나아가사서 각 서브트리의 노드 개수를 업데이트 한다
- 미리구한 각 서브트리의 노드 개수를 통해 답을 출력한다

출력
- 각 쿼리의 답 정수 하나로 출력
- 쿼리: 정점 U를 루트로 하는 서브트리에 속한 정점의 수
 */

public class Main {
    public static class Tree {
        int size;
        int root;
        Node[] nodes;
        ArrayList<Integer>[] graph;

        int[] cnts;

        public Tree(int size, int root, ArrayList<Integer>[] graph) {
            this.size = size;
            this.root = root;
            nodes = new Node[size + 1]; // 번호맞추기
            this.graph = graph;
            cnts = new int[size + 1];
            makeTree(root, 0);
            countSubtreeNodes(nodes[root]);
        }

        public void makeTree(int cur, int parent) {
            nodes[cur] = new Node(cur, parent);
            for (int child : graph[cur]) {
                if (child != parent) {
                    nodes[cur].children.add(child);
                    makeTree(child, cur);
                }
            }
        }

        public void countSubtreeNodes(Node curNode) {
            cnts[curNode.num] = 1;
            for (int child : curNode.children) {
                countSubtreeNodes(nodes[child]);
                cnts[curNode.num] += cnts[nodes[child].num];
            }
        }
    }

    public static class Node {
        int num;
        int parent;
        ArrayList<Integer> children;

        public Node(int num, int parent) {
            this.num = num;
            this.parent = parent;
            children = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            graph[U].add(V);
            graph[V].add(U);
        }

        Tree tree = new Tree(N, R, graph);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            answer.append(tree.cnts[Integer.parseInt(br.readLine())]).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}