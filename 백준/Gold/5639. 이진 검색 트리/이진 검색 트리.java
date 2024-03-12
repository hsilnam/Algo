import java.util.*;
import java.io.*;

/*
- Node클래스를 만들어서 현재값(parent), 왼쪽자식값, 오른쪽자식값 저장하기
- insert, 후위 순화 결과 모두 재귀 사용
- 현재 넣으려는 값이 부모값보다 작으면 왼쪽으로, 부모값보다 크면 오른쪽으로 넣기

[실패]
- 1%(null pointer)
    입력값을 끝까지 받는 과정에서 str.equals("")로 했을 때 str이 null인 경우가 있어서 발생하는 문제
    => 앞에 str이 null인지 체크하는 부분 추가
 */
public class Main {
    public static class Node {
        int parent;
        Node lChild, rChild;

        public Node(int parent) {
            this.parent = parent;
        }

        public Node(int parent, Node lChild, Node rChild) {
            this.parent = parent;
            this.lChild = lChild;
            this.rChild = rChild;
        }

        public void insert(int num) {
            if (num < parent) {
                if (this.lChild == null) {
                    this.lChild = new Node(num);
                } else {
                    this.lChild.insert(num);
                }
            } else {
                if (this.rChild == null) {
                    this.rChild = new Node(num);
                } else {
                    this.rChild.insert(num);
                }
            }
        }
    }

    public static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Node root = new Node(Integer.parseInt(br.readLine()));
        String temp = br.readLine();
        while (temp != null && !temp.equals("")) {
            int num = Integer.parseInt(temp);
            root.insert(num);
            temp = br.readLine();
        }

        postPrint(root);
        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static void postPrint(Node node) {
        if (node == null) {
            return;
        }
        postPrint(node.lChild);
        postPrint(node.rChild);
        result.append(node.parent).append("\n");
    }
}