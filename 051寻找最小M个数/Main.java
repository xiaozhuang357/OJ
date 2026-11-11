import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine().trim();
        int m = sc.nextInt();

        String[] s = line.substring(1, line.length() - 1).split(",");
        int n = s.length;

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }

        Arrays.sort(nums);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < m; i++) {
            if (i != m - 1) {
                sb.append(nums[i] + ", ");
            } else {
                sb.append(nums[i]);
            }
        }
        sb.append("]");

        System.out.println(sb.toString());
    }
}
