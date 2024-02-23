import java.util.stream.*;

class Solution {
    public long solution(int price, int money, int count) {
        long totalPrice = LongStream.rangeClosed(1L, count)
            .map(i -> i * price)
            .sum();
        
        return (totalPrice > money) ? totalPrice - money : 0;
    }
}