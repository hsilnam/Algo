import java.util.*;
import java.io.*;


public class Main {
    static class Trie {
        Trie[] child;
        int cnt;

        public Trie() {
            child = new Trie[26]; // 알파벳 크기
        }

        public String insert(String str) {
            StringBuilder prefix = new StringBuilder();

            Trie root = this; // 현재 노드부터 시작

            boolean flag = true;
            for (char c : str.toCharArray()) {
                if (flag) { // prefix에 추가하기
                    prefix.append(c);
                }

                int alpaIdx = c - 'a'; // 알파벳 위치

                if (root.child[alpaIdx] == null) { // 새로 방문하면 새로 만들기
                    flag = false; // 새로운 분기점까지만 prefix 처리
                    root.child[alpaIdx] = new Trie();
                }
                root = root.child[alpaIdx]; // 다음 노드로 가기
            }
            root.cnt += 1;
            if(root.cnt > 1) {
                prefix.append(root.cnt);
            }
            return prefix.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            result.append(trie.insert(br.readLine())).append("\n");
        }
        System.out.println(result.toString());
    }
}