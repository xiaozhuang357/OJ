import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long result = n;
        // 计算result

        long speed = 1;
        for (int i = 0; i <= 31; i++) {
            long downloadMin = (n + speed - 1) / speed;

            result = Math.min(downloadMin + i, result);

            speed <<= 1;
        }

        System.out.print(result);
        scanner.close();
    }
}
