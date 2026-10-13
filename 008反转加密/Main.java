import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        boolean ans = false;
        for (int i = 0; i <= num / 2; i++) {
            int di = dnum(i);
            if (i + di == num) {
                ans = true;
                break;
            }
        }
        System.out.println(ans ? "True" : "False");
    }

    public static int dnum(int num) {
        int ans = 0;
        while (num != 0) {
            ans = ans * 10 + num % 10;
            num /= 10;
        }
        return ans;
    }
}
