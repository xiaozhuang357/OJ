import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] prices = new int[n];

        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        long ans = maxWind(n, prices);

        System.out.print(ans);
    }

    private static long maxWind(int n, int[] prices) {
        long sum = prices[0];
        long max = prices[0];

        for (int i = 1; i < n; i++) {
            sum = Math.max(prices[i], sum + prices[i]);

            max = Math.max(max, sum);
        }
        return max;
    }
}
