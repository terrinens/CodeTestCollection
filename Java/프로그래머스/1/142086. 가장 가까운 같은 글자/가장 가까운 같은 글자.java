
class Solution {
    public int[] solution(String s) {
        String[] ss = s.split("");
        int len = ss.length;
        int[] answer = new int[len];
        
        for (int i = 0; i < len; i++) {
            int count = -1;
            String pice = ss[i];
            
            for(int j = i, k = 1; j > 0; j--, k++) {
                if (pice.equals(ss[j-1])) {
                    count = k;
                    break;
                };
            }
            
            answer[i] = count;
            count = -1;
        }
        return answer;
    }
}