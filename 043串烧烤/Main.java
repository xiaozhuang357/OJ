import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] values = scanner.nextLine().split(",");
        int[] position = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
        System.out.print(minCostToMoveChips(position));
    }

    static int minCostToMoveChips(int[] pos) {
        int odd = 0, even = 0;
        for (int p : pos) {
            if (p % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return Math.min(odd, even);
    }
}
