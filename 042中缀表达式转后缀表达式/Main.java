import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().trim().split("\\s+");

        Deque<String> stack = new ArrayDeque<>();
        List<String> output = new ArrayList<>();

        for (String token : tokens) {
            if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop();
            } else if (isOperator(token)) {
                while (!stack.isEmpty() && !stack.peek().equals("(") && priority(token) <= priority(stack.peek())) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else {
                output.add(token);
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        for (String s : output) {
            sb.append(s).append(" ");
        }
        System.out.println(sb);
    }

    static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    static int priority(String op) {
        if (op.equals("+") || op.equals("-"))
            return 1;
        if (op.equals("*") || op.equals("/"))
            return 2;
        return 0;
    }
}
