
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        long ans = 0;

        if (n % 4 == 0) {
            ans = n;
        } else if (n % 4 == 1) {
            ans = 1;
        } else if (n % 4 == 2) {
            ans = n + 1;
        } else {
            ans = 0;
        }

        System.out.println(ans);
    }
}
