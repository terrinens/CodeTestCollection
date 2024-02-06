import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int a = 0;
            for (int i = 1, t = sc.nextInt(); i <= t; i++) {
                a += i;
            }
            System.out.println(a);
        }
    }
}