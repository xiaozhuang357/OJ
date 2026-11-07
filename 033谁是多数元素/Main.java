import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        sc.close();
        s = s.substring(1, s.length() - 1);
        String[] c = s.split(",");

        int n = c.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(c[i]);
        }
        Arrays.sort(nums);
        int index1 = (n - 1) / 4;
        int index2 = (n - 1) / 2;
        if (nums[index1] == nums[index2]) {
            System.out.println(nums[index1]);
        } else {
            System.out.println(nums[(n - 1) * 3 / 4]);
        }
    }
}
