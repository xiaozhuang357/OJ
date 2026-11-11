import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public int countDigitOne(int n) {

        long ans = 0;

        long factor = 1;
        while (factor <= n) {
            long high = n / (factor * 10);
            long cur = (n / factor) % 10;
            long low = n % factor;

            if (cur == 0) {
                ans += high * factor;
            } else if (cur == 1) {
                ans += (high * factor) + low + 1;
            } else {
                ans += (high + 1) * factor;
            }
            factor *= 10;
        }
        return (int) ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int result;
        Main main = new Main();

        result = main.countDigitOne(n);
        System.out.println(result);
        reader.close();
        return;
    }
}
