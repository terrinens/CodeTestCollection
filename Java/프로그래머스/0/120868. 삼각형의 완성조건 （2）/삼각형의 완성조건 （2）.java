class Solution {
    public int solution(int[] sides) {
        int min = Math.abs(sides[0] - sides[1]) + 1;
        int max = sides[0] + sides[1];
        return max - min;
    }
}