import java.io.*;
import java.util.Arrays;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        
        
        try (
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
            ) {
            int t = Integer.parseInt(br.readLine());

            for (int i = 1; i <= t; i++) {
                String[] input = br.readLine().split(" ");
                int sum = Arrays.stream(input, 0, 10)
                        .mapToInt(Integer::parseInt)
                        .filter(x -> x % 2 != 0).sum();

                bw.write(String.format("#%d %d", i, sum));
                bw.newLine();
            }

            bw.flush();

        } catch (IOException e) {
            throw new RuntimeException("입력 오류", new IOException());
        }	
    }
}