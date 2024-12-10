import java.io.*;
import java.util.*;

/*
입력
- N: 가입한 유저의 수 (1<=N<=100_000)
- 가입한 닉네임 순서대로
    - 알파벳 소문자, 길이 10 이하

조건
- 별칭 Prefix 중 가장 길이가 짧은 것으로
- prefix 중복 불가능
- 가능한 별칭이 없는 경우, 가입한 사람의 수 붙이기 (1 제외, 1은 닉네임으로)


풀이
- Trie 활용


출력
- 유저가 가입한 순으로 별칭 한줄 씩 출력

 */

public class Main {

    public static class Trie {
        private class TrieNode {
            TrieNode[] node = new TrieNode[26]; // 알파벳
            boolean isEnd;
            int cnt;
        }

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public String add(String s) {
            TrieNode cur = root;
            boolean flag = false;
            StringBuilder prefix = new StringBuilder();
            for (char c : s.toCharArray()) {
                int idx = c - 'a';
                if(!flag) {
                    prefix.append(c);
                }
                if (cur.node[idx] == null) {
                    cur.node[idx] = new TrieNode();
                    flag = true;
                }
                cur = cur.node[idx];
            }
            cur.isEnd = true;
            cur.cnt++;
            if(cur.cnt > 1) {
                prefix.append(cur.cnt);
            }
            return prefix.toString();
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Trie trie = new Trie();
        int N = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            answer.append(trie.add(name)).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}