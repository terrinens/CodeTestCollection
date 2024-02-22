

class Solution {
    public int solution(int a, int b, int n) {        
        int count = ((int) Math.floor(n / a) * b);
        int bottle = n % a + ((int) Math.floor(n / a) * b);
        
        while (bottle >= a) {
            count += (int) Math.floor(bottle / a) * b;
            bottle = bottle % a + ((int) Math.floor(bottle / a) * b);
            if (bottle <= a) {
                count += (int) Math.floor(bottle / a);
                break;
            }
        }
        
        return count;
    }
}