import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String id = scanner.next().toLowerCase();
            System.out.println(id+"??!");
        }
    }
}
