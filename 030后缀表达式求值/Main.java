import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] s = sc.nextLine().trim().split("\\s+");

        Stack<Integer> stack = new Stack<>();

        for (String c : s) {
            if (isNumber(c)) {
                stack.push(Integer.parseInt(c));
                continue;
            }
            int num1 = stack.pop();
            int num2 = stack.pop();
            switch (c) {
                case "+":
                    stack.push(num1 + num2);
                    break;
                case "-":
                    stack.push(num2 - num1);
                    break;
                case "*":
                    stack.push(num1 * num2);
                    break;
                case "/":
                    stack.push(num2 / num1);
                    break;
                default:
                    stack.push(Integer.parseInt(c));
                    break;
            }
        }
        System.out.println(stack.pop());
    }

    static boolean isNumber(String s) {
        return !(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }
}
