import java.io.*;
import java.util.*;

public class Main {
    public static class TrieNode {
        HashMap<Character, TrieNode> childNode = new HashMap<>();
    }

    public static class Trie {
        TrieNode rootNode;

        Trie() {
            rootNode = new TrieNode();
        }

        public void insert(String word) {
            TrieNode curNode = this.rootNode;
            for (char c : word.toCharArray()) {
                curNode = curNode.childNode.computeIfAbsent(c, k -> new TrieNode());
            }
        }

        public boolean contain(String word) {
            TrieNode curNode = this.rootNode;
            for (char c : word.toCharArray()) {
                TrieNode node = curNode.childNode.get(c);
                if (node == null) {
                    return false;
                }
                curNode = node;
            }
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            trie.insert(br.readLine());
        }

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (trie.contain(br.readLine())) {
                cnt++;
            }
        }

        bw.write(Integer.toString(cnt));

        br.close();
        bw.close();
    }
}