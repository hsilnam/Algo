import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        
        int maxd = n-1;
        int maxp = n-1;
        while(true) {
            // System.out.println("----" + (maxd+1) + "----");
            for(int i = maxd; i >= 0; i--) {
                if(deliveries[i] != 0) {
                    maxd = i;
                    break;
                }
                if(i == 0 && deliveries[i] == 0) {
                    maxd = -1;
                }
            }
            for(int i = maxp; i >= 0; i--) {
                if(pickups[i] != 0) {
                    maxp = i;
                    break;
                }
                if(i == 0 && pickups[i] == 0) {
                    maxp = -1;
                }
            }
            
            
            if(maxd == -1 && maxp == -1) {
                break;
            }
            
            int d = cap;
            int dd = maxd;
            for(int i = maxd; i >= 0; i--) {
                if(d == 0) {
                    break;
                }
                if(d != 0){
                    int delivery = deliveries[i];
                    deliveries[i] -= Math.min(d, delivery);
                    d -= Math.min(d, delivery);
                    dd = i;
                }
            }
            
            int p = cap;
            int pd = maxp;
            for(int i = maxp; i >= 0; i--) {
                if(p == 0) {
                    break;
                }
                if(p != 0){
                    int pickup = pickups[i];
                    pickups[i] -= Math.min(p, pickup);
                    p -= Math.min(p, pickup);
                    pd = i;
                }
            }
            
            // System.out.println(maxd+1);
            answer += (Math.max(maxd,maxp)+1) * 2;
            maxd = dd;
            maxp = pd;
            
            // System.out.println(Arrays.toString(deliveries));
            // System.out.println(Arrays.toString(pickups));
            // System.out.println(maxd+1);
            
        }
        
        return answer;
    }
}