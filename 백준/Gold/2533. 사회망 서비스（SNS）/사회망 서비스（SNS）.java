import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

/*
입력
- N: 트리의 정점 개수 (2<=N<=1_000_000)
- 정점: 일련번호 (1~N)
- 친구 관계트리의 에지(u,v)

조건
- 에지: u,v 서로 친구 관계
- 얼리 아답터: 새로운 아이디어 먼저 받아드린 사람
- 아답터: 자신의 모든 친구들이 얼리 어답터여야함

풀이
- 트리 활용
    - 트리를 만들 때, 루트를 어디에 두든지 결과는 동일하다
        -> 1로 고정
    - 저장값: 노드 id, 부모 노드, 자식 노드, 얼리 어답터 개수, 얼리 어답터인지 True/false
- 탐색: dfs의 후위 탐색(바텀 업) + dp(전 결과 더하기)
    - 왼,오를 먼저 탐색하고 결과를 바탕으로 자신이 얼리어뎁터인이 아닌지 판단해야한다
        (한번 거친 결과는 변동없음)
    - 그리고 자신을 포함해서 자식들의 얼리 어답터 개수를 자신의 값으로 저장한다
    - 반드시 얼리 어답터가 되어야 하는 것만 카운트 한다
        - 자기자신이 이미 얼리 어뎁터이면,
            개수를 (자식의 얼리어뎁터 개수합 +1)로 업데이트하고 그대로 다음으로 넘어간다
        - 자기자신이 얼리 어뎁터가 아니면
            - 자신이 리프노드라면, (자식 개수 0)
                부모가 무조건 얼리 어답터가 되어야한다 (그게 최소)
                    - 부모 얼리 어뎁터 true로 변경
                - 개수를 (자식의 얼리어뎁터 개수합 +1)로 업데이트
            - 자신의 자식이 모두 얼리 어뎁터이면,
                자기자신은 얼리 어댑터가 아니고, 부모는 어뎁터로 만든다
                - 부모 얼리 어뎁터 true로 변경
                - 개수를 자식의 자식의 얼리어뎁터 개수합으로 업데이트
            - 자신의 자식이 하나라도 얼리 어뎁터가 아니면,
                자기자신을 얼리 어뎁터로 만들어야한다
                - 자신 얼리 어뎁터 true로 변경
                - 개수를 (자식의 얼리어뎁터 개수합 +1)로 업데이트

- 마지막 root의 얼리 어답터의 수를 출력하면 된다

출력
- 주어진 친구 관계 그래프에서 아이디어 전파하는데 필요한 얼리 어답터의 최소 수
 */

public class Main {

    public static class Tree {
        TreeNode root;
        int size;

        public Tree(int size, ArrayList<Integer>[] graph) {
            this.size = size;
            makeTree(1, graph);
        }

        private void makeTree(int rootNum, ArrayList<Integer>[] graph) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            root = new TreeNode(rootNum, null);
            queue.offer(root);
            boolean[] visited = new boolean[size + 1]; // 1~
            visited[rootNum] = true;

            while (!queue.isEmpty()) {
                TreeNode curNode = queue.poll();
                for (int nextId : graph[curNode.id]) {
                    if (visited[nextId]) {
                        continue;
                    }
                    TreeNode nextNode = new TreeNode(nextId, curNode);
                    curNode.children.add(nextNode);
                    visited[nextId] = true;

                    queue.offer(nextNode);
                }
            }
        }

        public class TreeNode {
            int id;
            TreeNode parent;
            ArrayList<TreeNode> children = new ArrayList<>();
            boolean isEarly;
            int minCnt;

            public TreeNode(int id, TreeNode parent) {
                this.id = id;
                this.parent = parent;
            }

            public TreeNode(TreeNode parent, ArrayList<TreeNode> children) {
                this.parent = parent;
                this.children = children;
            }
        }

        public void explore(TreeNode treeNode) {
            for (TreeNode child : treeNode.children) {
                explore(child);
            }

            // 자기자신 검사
            if (treeNode.parent == null) { // root라면
                if (treeNode.isEarly) {
                    treeNode.minCnt = getChildrenMinCnt(treeNode) + 1;
                } else {
                    treeNode.minCnt = getChildrenMinCnt(treeNode);
                }
                return;
            }
            if (treeNode.isEarly) { // 이미 얼리 어답터인 경우 (루트의 경우도 포함)
                treeNode.minCnt = getChildrenMinCnt(treeNode) + 1;
                return;
            }

            if (treeNode.children.size() == 0) { // 리프 노드인경우
                treeNode.parent.isEarly = true;
                return;
            }

            // 자식 검사
            boolean isAllChildEarly = isAllChildEarly(treeNode);
            if (isAllChildEarly) { // 자식이 모두 얼리 어답터라면
                treeNode.parent.isEarly = true;
                treeNode.minCnt = getChildrenMinCnt(treeNode);
            } else {
                treeNode.isEarly = true;
                treeNode.minCnt = getChildrenMinCnt(treeNode) + 1;
            }
        }

        public int getChildrenMinCnt(TreeNode treeNode) {
            int sum = 0;
            for (TreeNode child : treeNode.children) {
                sum += child.minCnt;
            }
            return sum;
        }

        public boolean isAllChildEarly(TreeNode treeNode) {
            for (TreeNode child : treeNode.children) {
                if (!child.isEarly) {
                    return false;
                }
            }
            return true;
        }

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N + 1]; // 1~
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        Tree tree = new Tree(N + 1, graph);
        tree.explore(tree.root);

//        printTree(tree.root);

        bw.write(String.valueOf(tree.root.minCnt));

        br.close();
        bw.flush();
        bw.close();
    }


    public static void printTree(Tree.TreeNode cur) {
        System.out.println(cur.id + ": " + cur.minCnt + " " + cur.isEarly);
        for (Tree.TreeNode child : cur.children) {
            printTree(child);
        }
    }
}