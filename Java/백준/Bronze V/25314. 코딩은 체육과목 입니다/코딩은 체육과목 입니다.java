import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt()/4;
            StringBuilder result = new StringBuilder();
            for (;n>0;n--) {
                result.append("long ");
            }
            System.out.println(result+"int");
        }
    }
}