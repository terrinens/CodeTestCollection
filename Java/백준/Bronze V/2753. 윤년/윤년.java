import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            int a = scanner.nextInt();
            System.out.println(a%4==0 && a%100!=0 || a%400==0? "1" : "0");
        }
    }
}