import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().trim().split("\\s+");
        int money = sc.nextInt();
        int n = s.length;

        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(s[i]);
        }

        Arrays.sort(prices);
        int cost = prices[0] + prices[1];
        int left = money - cost;

        System.out.println(left >= 0 ? left : money);
    }
}
