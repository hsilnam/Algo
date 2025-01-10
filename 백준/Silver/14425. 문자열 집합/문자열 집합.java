import java.io.*;
import java.util.*;

/*
입력
- N,M: 문자열의 개수  (1<=N,M<=10_000)
- 문자열
    - 집합 S에 포함되어있는 문자열
    - 검사해야하는 문자열
    - 알파벳 소문자로만 이루어짐
    - 길이 500넘지 않음
    - 집합 s에 같은 문자열 여러번 주어지는 경우 없음


조건
- N개의 문자열로 이루어진 집합s
- M개 문자열중 집합 S에 포함되는 것의 개수 구하기

풀이
- trie 알고리즘을 이용하여 N을 저장해놓고, M을 검사해서 해당 문자열이 존재하는지 검사

출력
- 총 몇 개가 집합 S에 포함되어있는지 출력

 */

public class Main {
    public static class Trie {
        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String s) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        public boolean search(String s) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    return false;
                }
                node = node.children[idx];
            }
            return node.isEnd;
        }
    }

    public static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;

        public TrieNode() {
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            trie.insert(br.readLine());
        }


        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (trie.search(br.readLine())) {
                cnt++;
            }
        }

        bw.write(String.valueOf(cnt));


        br.close();
        bw.flush();
        bw.close();
    }

}