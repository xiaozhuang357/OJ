import java.util.Scanner;

public class Main {
    static final int MOD = 1337;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int n = sc.nextInt();

        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }

        long aMod = a % MOD;
        long result = 1;

        for (int digit : b) {
            result = pow(result, 10) * pow(aMod, digit) % MOD;
        }
        System.out.println(result);
    }

    static long pow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }
}
