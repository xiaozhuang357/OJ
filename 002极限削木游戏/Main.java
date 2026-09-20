import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine().trim());

        System.out.println(f(X));
    }

    public static long f(int x) {
        if (x == 1) {
            return 1 + 2 + 3;
        }
        return 2*f(x - 1) + 2;
    }
}
