import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalTime time = LocalTime.of(scanner.nextInt(), scanner.nextInt());
            LocalTime timeResult = time.minusMinutes(45);

            String h = Integer.toString(timeResult.getHour());
            String m = Integer.toString(timeResult.getMinute());
            System.out.println(
                    h + " " + m
            );
        }
    }
}