import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] reward1 = readArray(sc);

        int[] reward2 = readArray(sc);

        int n = reward1.length;

        int k = sc.nextInt();

        int ans = 0;

        // int[] diff = new int[n];

        // for (int i = 0; i < diff.length; i++) {
        // diff[i] = reward1[i] - reward2[i];
        // }

        // Arrays.sort(diff);

        // for (int i = n - k; i < n; i++) {
        // ans += diff[i];
        // }

        // for (int i = 0; i < n; i++) {
        // ans += reward2[i];
        // }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            ans += reward2[i];

            int diff = reward1[i] - reward2[i];

            if (queue.size() < k) {
                queue.offer(diff);
            } else if (k > 0 && diff > queue.peek()) {
                queue.poll();
                queue.offer(diff);
            }
        }

        while (!queue.isEmpty()) {
            ans += queue.poll();
        }

        System.out.println(ans);

    }

    public static int[] readArray(Scanner sc) {
        String[] s = sc.nextLine().trim().split("\\s+");

        int nums[] = new int[s.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }
        return nums;
    }
}
