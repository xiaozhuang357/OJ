import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        long hp = 1;

        long total = 1;

        int ans = 0;

        while (sc.hasNextInt()) {
            int val = sc.nextInt();

            total += val;

            hp += val;

            if (val < 0) {
                heap.offer(val);
            }
            if (hp <= 0) {
                hp -= heap.poll();
                ans++;
            }
        }
        System.out.println(total <= 0 ? -1 : ans);
    }
}
