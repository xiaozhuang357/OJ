import java.util.*;

public class Main {

    static int n;
    static int[] arr;
    static int num;

    static StringBuilder sb = new StringBuilder();

    static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        num = sc.nextInt();

        Arrays.sort(arr);

        dfs(0, 0);
        
        System.out.println(sb);

    }

    public static void dfs(int start, int sum) {

        if (num == sum && path.size() != 0) {
            if (sb.length() > 0) {
                sb.append("\n");
            }

            for (int i = 0; i < path.size(); i++) {
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(path.get(i));
            }
        }
        for (int i = start; i < arr.length; i++) {

            if (i > start && arr[i] == arr[i - 1]) {
                continue;
            }

            if (arr[i] + sum > num) {
                break;
            }
            path.add(arr[i]);

            dfs(i + 1, sum + arr[i]);
            path.remove(path.size() - 1);
        }
    }

}
