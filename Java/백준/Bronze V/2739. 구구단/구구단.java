import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int a = sc.nextInt();
            for (int i = 1; i <= 9; i++) {
                System.out.println(a + " * " + i + " = " + a*i);
            }
        }
    }
}