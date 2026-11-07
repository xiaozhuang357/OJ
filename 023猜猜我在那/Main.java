import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 输入字符串 S
        String S = sc.nextLine().trim();

        // 获取所有可能的有效坐标
        List<String> result = recoverCoordinates(S);

        // 按字典序输出每个坐标
        for (String coord : result) {
            System.out.println(coord);
        }

        sc.close();
    }

    // 获取所有可能的有效坐标对
    public static List<String> recoverCoordinates(String S) {
        List<String> ans = new ArrayList<>();

        int n = S.length();

        for (int i = 1; i < n; i++) {
            String left = S.substring(0, i);
            String right = S.substring(i);

            List<String> leftNums = generatePossibleNumbers(left);
            List<String> rightNums = generatePossibleNumbers(right);

            for (String l : leftNums) {
                for (String r : rightNums) {
                    ans.add(l + ", " + r);
                }
            }
        }

        Collections.sort(ans);
        return ans;
    }

    // 生成所有有效的数字表示形式
    public static List<String> generatePossibleNumbers(String s) {
        List<String> ans = new ArrayList<>();

        if (isValidInteger(s)) {
            ans.add(s);
        }
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);

            if (isValidInteger(left) && isValidDecimal(right)) {
                ans.add(left + "." + right);
            }
        }
        return ans;
    }

    // 判断给定字符串是否为有效的整数
    public static boolean isValidInteger(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        // 自己为0和开头不为0
        return s.length() == 1 || s.charAt(0) != '0';
    }

    // 判断给定字符串是否为有效的小数部分
    public static boolean isValidDecimal(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        return s.charAt(s.length() - 1) != '0';
    }
}