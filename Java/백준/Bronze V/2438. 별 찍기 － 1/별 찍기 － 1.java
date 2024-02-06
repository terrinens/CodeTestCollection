import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int size = Integer.parseInt(br.readLine());
            for (int i = 1; i < size+1; i++) {
                wr.write("*".repeat(i));
                wr.newLine();
            }
            wr.flush();
        }
    }
}