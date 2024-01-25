class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        int remain = n;
        while(a <= remain) {
            int newCola = (remain/a)*b;
            remain = (remain%a) + newCola;
            answer += newCola;
        }
        
        return answer;
    }
}