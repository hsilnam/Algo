import java.util.*;
class Solution {
    public int solution(String s) {
        int cnt = 0;
        Queue<Character> queue = new ArrayDeque<>();
        for(char c: s.toCharArray()) {
            queue.offer(c);
        }
        for(int i = 0; i < queue.size() ; i++) {
            if(isOk(queue)) {
                cnt++;
            }
            queue.offer(queue.poll());
        }
        
        return cnt;
    }
    
    public boolean isOk(Queue<Character> queue) {
        Stack<Character> stack = new Stack<>();
        
        for(char c : queue) {
            if(c == '[' || c == '(' || c == '{'){
                stack.push(c);
            } 
            if(c == ']') {
                if(!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            }
            if(c == ')') {
                if(!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
            if(c == '}') {
                if(!stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}