import java.util.*;
import java.io.*;

// S에서 T를 찾는 것보다 T에서 S를 찾는게 경우의 수가 적다
public class Main {

    static class Node {
        String str;
        int len;

        public Node(String str, int len) {
            this.str = str;
            this.len = len;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        int sLen = S.length();
        int isOk = 0;

        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();

        queue.offer(new Node(T, T.length()));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            StringBuilder cSb = new StringBuilder(cur.str);
            int cLen = cur.len;
            if (cLen == sLen) {
                if (S.equals(cSb.toString())) {
                    isOk = 1;
                    break;
                }
                continue;
            }
            int nLen = cur.len - 1;

            if(cSb.substring(cur.len-1).equals("A")) { // 뒤에서 A 때기
                StringBuilder temp = new StringBuilder(cSb).deleteCharAt(cur.len-1);
                if (!visited.contains(temp.toString())) {
                    visited.add(temp.toString());
                    queue.offer(new Node(temp.toString(), nLen));
                }
            }
            if(cSb.substring(0, 1).equals("B")) { // 앞에서 B 때고 뒤집기
                StringBuilder temp = new StringBuilder(cSb).deleteCharAt(0).reverse();
                if (!visited.contains(temp.toString())) {
                    visited.add(temp.toString());
                    queue.offer(new Node(temp.toString(), nLen));
                }
            }
            
        }
        System.out.println(isOk);
    }
}