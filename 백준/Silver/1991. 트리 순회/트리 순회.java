import java.util.*;
import java.io.*;


/*
- 재귀를 사용하여 순회 종류에 따라 탐색
    - parent가 '.'이면 return 한다
    - 전위(pre): 루트(문자 append), 왼, 오
    - 중위(in): 왼, 루트(문자 append), 오
    - 후위(post): 왼, 오, 루트(문자 append)
- 자식의 정보는 각 알파벳(26개)에 2차원 배열로 저장한다
    => char[][] childInfo = new char[26][2];
    - index: 알파벳 순서(A부터 시작)
    - value: left, right 자식 정보
 */
public class Main {
    public static char[][] childInfo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        childInfo = new char[26][2]; // index: 알파벳 순서(A부터 시작) / value: left, right 자식 정보

        for (int i = 1; i < N + 1; i++) {
            String[] temp = br.readLine().split(" ");
            char p = temp[0].charAt(0);
            char cl = temp[1].charAt(0);
            char cr = temp[2].charAt(0);
            int pAlphaIdx = getAlphaIdx(p);
            childInfo[pAlphaIdx][0] = cl; // left
            childInfo[pAlphaIdx][1] = cr; // right
        }

        char root = 'A';

        StringBuilder result = new StringBuilder();
        preOrder(root, result);
        result.append("\n");

        inOrder(root, result);
        result.append("\n");

        postOrder(root, result);
        result.append("\n");

        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static void preOrder(char parent, StringBuilder result) {
        if (parent == '.') {
            return;
        }
        result.append(parent);
        char[] child = childInfo[getAlphaIdx(parent)];
        preOrder(child[0], result);
        preOrder(child[1], result);

    }

    public static void inOrder(char parent, StringBuilder result) {
        if (parent == '.') {
            return;
        }
        char[] child = childInfo[getAlphaIdx(parent)];
        inOrder(child[0], result);
        result.append(parent);
        inOrder(child[1], result);
    }

    public static void postOrder(char parent, StringBuilder result) {
        if (parent == '.') {
            return;
        }
        char[] child = childInfo[getAlphaIdx(parent)];
        postOrder(child[0], result);
        postOrder(child[1], result);
        result.append(parent);
    }

    public static int getAlphaIdx(char alpha) {
        return (alpha - 'A');
    }
}