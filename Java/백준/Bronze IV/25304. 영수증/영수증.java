import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int t = 0;
            int all = sc.nextInt();
            int p = sc.nextInt();

            for (; p > 0; p--) {
                t += (sc.nextInt() * sc.nextInt());
            }

            System.out.println(all!=t ? "No" : "Yes");
        }
    }
}