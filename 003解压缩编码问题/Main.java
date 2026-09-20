import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String input = br.readLine().trim();

        System.out.println(decode(input));

    }

    static int index = 0;

    public static String decode(String input) {
        
        StringBuilder sb = new StringBuilder();

        while (index < input.length() && input.charAt(index) != ']') {

            // 如果'['index++然后计算重复次数
            if (input.charAt(index) == '[') {
                index++;

                int count = 0;
                while (Character.isDigit(input.charAt(index))) {
                    count = count * 10 + input.charAt(index) - '0';
                    index++;
                }

                // 递归解析内层重复字符串
                String inner = decode(input);
                // 跳过内层的']'
                index++;

                // 将内层重复count次
                for (int i = 0; i < count; i++) {
                    sb.append(inner);
                }

            } else {
                // 普通字母直接添加
                sb.append(input.charAt(index));
                index++;
            }
        }
        return sb.toString();
    }

}