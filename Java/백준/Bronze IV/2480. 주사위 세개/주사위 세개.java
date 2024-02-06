import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int[] dice = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
            Arrays.sort(dice);
            OptionalInt max = Arrays.stream(dice).max();

            int count = 1;
            int equalsNum = 0;
            int t = 0;
            for (int die : dice) {
                if (die == t) {
                    count++;
                    equalsNum = t;
                }
                t = die;
            }

            int result;
            switch (count) {
                case 3 : result = 10000 + equalsNum * 1000; break;
                case 2 : result = 1000 + equalsNum * 100; break;
                default : result = max.getAsInt() * 100;
            }
            System.out.println(result);
        }
    }
}