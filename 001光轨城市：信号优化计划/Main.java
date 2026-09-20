import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        // 读取输入
        String s = br.readLine().trim();

        int numOps = Integer.parseInt(br.readLine().trim());

        System.out.println(minLength(s, numOps));
    }

    public static int minLength(String s, int numOps) {
        // 二分查找答案
        int n = s.length();

        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 通过，说明这个数可以接受,继续下探
            if (check(s, numOps, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static boolean check(String s, int numOps, int k) {
        //当k==1时变为0101010
        if (k == 1) {
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                char expect = (i % 2) == 0 ? '0' : '1';
                if (s.charAt(i) != expect) {
                    cnt++;
                }
            }
            int minOps = Math.min(cnt, s.length() - cnt);
            return minOps <= numOps;
        }
        
        //当k>1时
        int sameLength = 1;
        int needOps = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                sameLength++;
            } else {
                needOps += sameLength / (k + 1);
                if (needOps > numOps) {
                    return false;
                }
                sameLength = 1;
            }
        }
        needOps += sameLength / (k + 1);
        return needOps<=numOps;
    }
}
