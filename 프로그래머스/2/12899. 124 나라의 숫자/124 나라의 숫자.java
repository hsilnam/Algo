class Solution {
    public String solution(int n) {
        int[] arr = {0,1,2,4}; // index 쉽게 맞춰주기 위해
        
        StringBuilder result = new StringBuilder();
        int tempN = n;
        while(tempN >= 3) {
            if(tempN%3==0) {
                result.append(arr[3]);
                tempN = (tempN/3)-1;
            } else{
                result.append(arr[tempN%3]);
                tempN = (tempN/3);
            }
        }
        if(tempN> 0){
            if(tempN%3==0) {
                result.append(arr[3]);
                tempN = (tempN/3)-1;
            } else{
                result.append(arr[tempN%3]);
                tempN = (tempN/3);
            }
        }
        result.reverse();
        return result.toString();
    }
}