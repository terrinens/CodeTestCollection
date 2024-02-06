import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            LocalTime time = LocalTime.of(sc.nextInt(), sc.nextInt());
            LocalTime timeResult = time.plusMinutes(sc.nextInt());
            System.out.println(timeResult.getHour() + " " + timeResult.getMinute());
        }
    }
}