import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] s = sc.nextLine().trim().split(",");
        int n = s.length;
        int[] val = new int[n];
        for (int i = 0; i < val.length; i++) {
            val[i] = Integer.parseInt(s[i]);
        }

        int maxLeft = val[0] + 0;
        int ans = 0;
        for (int j = 1; j < n; j++) {
            ans = Math.max(ans, maxLeft + val[j] - j);

            maxLeft = Math.max(maxLeft, val[j] + j);
        }

        System.out.println(ans);

    }
}
