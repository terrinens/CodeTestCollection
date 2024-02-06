import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int size = Integer.parseInt(br.readLine());
            for (int i = 1; i < size+1; i++) {
                String[] l = br.readLine().split(" ");
                String k = String.valueOf((Integer.parseInt(l[0]) + Integer.parseInt(l[1])));
                wr.write("Case #"+i+": " + k);
                wr.newLine();
            }
            wr.flush();
        }
    }
}