import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine().trim();
        String str2 = sc.nextLine().trim();
        sc.close();

        int idx1 = 0;
        int idx2 = 0;

        int m = str1.length();
        int n = str2.length();

        while (idx1 < m && idx2 < n) {
            if (str1.charAt(idx1) == str2.charAt(idx2)) {
                idx1++;
            }
            idx2++;
        }
        System.out.println(idx1 == m ? "true" : "false");
    }
}
