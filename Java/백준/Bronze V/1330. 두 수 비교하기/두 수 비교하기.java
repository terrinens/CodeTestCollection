import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            String value;
            if (a>b) {
                value = ">";
            } else if (a==b) {
                value = "==";
            } else {
                value = "<";
            }

            System.out.println(value);
        }
    }
}
