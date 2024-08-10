package hero.code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveConsecutiveChars {

    private static final String REGEX_STR = "(.)\\1{%s,}";

    public static void main(String[] args) {
        removeConsecutiveChars("aabcccbbad", 3); // Example 1
        System.out.println("Test 1 Code Test Over !");
        removeConsecutiveChars("abcccbad", 3, true); // Example 2
        System.out.println("Test 2 Code Test Over !");
        removeConsecutiveChars("aaabbcccbad", 3, true); // Example 2
        System.out.println("Test 3 Code Test Over !");
        removeConsecutiveChars("aaabbcccbad", 3, false); // Example 2
        System.out.println("Test 4 Code Test Over !");
        removeConsecutiveChars("abcc", 3); // Example 1
        System.out.println("Test 5 Code Test Over !");
        removeConsecutiveChars("abccca", 3); // Example 1
        System.out.println("Test 6 Code Test Over !");
        removeConsecutiveChars("aaabbbcccdddeeefffggg", 3);
        System.out.println("Test 7 Code Test Over !");
    }

    /**
     * 删除字符串中所有连续三个或更多的相同字符。
     * 
     * @param input 输入字符串
     * @param minLength 最小连续出现次数，默认3
     * @return 处理结果
     */
    public static String removeConsecutiveChars(String input, int minLength) {
        boolean changed;
        minLength = minLength < 3 ? 3 : minLength;

        System.out.println("\nOriginal: " + input);
        do {
            changed = false;

            String regex = String.format(REGEX_STR, minLength - 1);
            input = input.replaceAll(regex, ""); // 使用正则表达式替换连续三个或更多的相同字符
            System.out.println("--> " + input);
            if (input.length() != input.replaceAll(regex, "").length()) {
                changed = true;
            }

        } while (changed);

        System.out.println("Result: " + input);
        return input;
    }

    /**
     * 删除字符串中所有连续三个或更多的相同字符。
     * 
     * @param input 输入字符串
     * @param minLength 最小连续出现次数，默认3
     * @param replaceWithBefore 是否使用匹配前字符替换：true用，false不用
     * @return 处理后的字符串
     */
    public static String removeConsecutiveChars(String input, int minLength, boolean replaceWithBefore) {
        boolean changed;
        minLength = minLength < 3 ? 3 : minLength;
        System.out.println("\nOriginal: " + input);

        do {
            changed = false;
            // 创建 Pattern 对象
            String regex = String.format(REGEX_STR, minLength - 1);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            // 使用 Matcher 对象进行替换
            StringBuffer replaced = new StringBuffer();
            String replacement = null;
            while (matcher.find()) {
                replacement = "";
                if (replaceWithBefore) {
                    int before = input.indexOf(matcher.group()) - 1;
                    if (before != -1) {
                        replacement = input.substring(before, before + 1);
                    }
                }
                matcher.appendReplacement(replaced, replacement);
                changed = true;
            }
            matcher.appendTail(replaced);
            // 更新 input 字符串
            input = replaced.toString();
            if (changed) {
                System.out.println("--> " + input);
            }
        } while (changed);
        System.out.println("Result: " + input);
        return input;
    }
}
