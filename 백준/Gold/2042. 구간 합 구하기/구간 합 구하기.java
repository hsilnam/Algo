import java.util.*;
import java.io.*;


public class Main {
    /*
    재귀함수를 이용하는 top-down 방식으로 만들었다
    arr의 크기 7이라면, 다음과 같은 트리가 만들어진다
             0-6
           /      \
        0-3         4-6
      /    \        /  \
     0-1    2-3    4-5   6
    /   \  /   \  /   \
    0   1  2   3  4   5
     */
    static class SegmentTree {
        /*
        tree node 번호는 1부터, arr 번호는 0부터 N-1한다
        - tree node: 0부터 시작하면, 이진트리의 idx를 계산하기 어렵다. 그래서 1부터 시작
            - 부모: idx/2
            - 자식: idx*2, idx*2+1
        - arr: leaf노드에 순차적으로 들어갈 것임으로 번호를 0으로하여 계산하는 것이 더 좋다.
         */
        public long[] tree;
        public long[] arr;
        public int N;

        public SegmentTree(long[] arr) {
            this.arr = arr;

            int N = arr.length;
            this.N = N;
            /*
            - leaf 노드 개수(arr)의 4배를 하면 얼추 맞는다
            - 정확하게 하려면 트리의 높이를 구하고, 최대 노드 개수를 구하면 된다
                - 트리의 높이: h = ceil(log2(N))+1
                - 최대 노드 개수: 2^h
             */
            tree = new long[N * 4];

            init(1, 0, N - 1);
        }

        public void init(int node, int start, int end) {
            // arr은 leaf노드로 한다
            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int mid = (start + end) / 2;
            init(node * 2, start, mid); // 왼쪽 자식으로 이동
            init(node * 2 + 1, mid + 1, end); // 오른쪽 자식으로 이동

            tree[node] = rangeCalc(tree[node * 2], tree[node * 2 + 1]);

        }

        public long query(int left, int right) {
            return query(1, 0, N - 1, left, right);
        }

        /*
        start, end: 노드의 범위
        left, right: 쿼리할 범위
         */
        private long query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) { // 범위에 포함안되는 경우
                return 0;
            }
            if (left <= start && end <= right) { // 노드의 범위가 전부 포함되면 해당 노드의 값으로 대체(구간합)
                return tree[node];
            }
            int mid = (start + end) / 2;
            long q1 = query(node * 2, start, mid, left, right);
            long q2 = query(node * 2 + 1, mid + 1, end, left, right);
            return rangeCalc(q1, q2);
        }

        public void update(int idx, long newValue) {
            update(1, 0, N - 1, idx, getDiff(idx, newValue));
        }

        /*
        start, end: 노드의 범위
        idx: update할 arr의 idx
        diff: update할 값차이
         */
        private void update(int node, int start, int end, int idx, long diff) {
            if (start > idx || end < idx) { // idx가 포함되어있지 않는 범위인 경우
                return;
            }
            if (start == end) { // leaf 노드인 경우
                arr[idx] = diffCalc(arr[idx], diff);
                tree[node] = diffCalc(tree[node], diff);
                return;
            }

            tree[node] = diffCalc(tree[node], diff); // 내려가면서 diff계산해주기

            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }

        /*
        구간에 대한 계산.
        방식(합, 최소, 최대, 곱)에 따라 달라질 수 있다
         */
        public long rangeCalc(long a, long b) {
            return a + b; // 현재값(부모) 자식들 값로 더하기
        }

        public long diffCalc(long origin, long diff) {
            return origin + diff;
        }

        public long getDiff(int idx, long newValue) {
            return newValue - arr[idx];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int K = Integer.parseInt(temp[2]);

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        SegmentTree segmentTree = new SegmentTree(arr);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            temp = br.readLine().split(" ");
            if ("1".equals(temp[0])) {
                int idx = Integer.parseInt(temp[1]) - 1;
                long newValue = Long.parseLong(temp[2]);
                segmentTree.update(idx, newValue);
            } else if ("2".equals(temp[0])) {
                int left = Integer.parseInt(temp[1]) - 1;
                int right = Integer.parseInt(temp[2]) - 1;
                result.append(segmentTree.query(left, right)).append("\n");
            }
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}