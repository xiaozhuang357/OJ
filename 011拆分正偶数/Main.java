import java.util.*;

public class Main {

    public static void main(String arg[]) {

        Scanner snc = new Scanner(System.in);

        int n = snc.nextInt();

        System.out.println(maximumEvenSplit(n));

    }

    public static List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if (finalSum % 2 == 1) {
            return ans;
        }
        long cur = 2;
        while (finalSum > 0) {
            if (2 * cur + 2 <= finalSum) {
                finalSum -= cur;
                ans.add(cur);
                cur += 2;
            } else {
                ans.add(finalSum);
                break;
            }
        }
        return ans;
    }

}
