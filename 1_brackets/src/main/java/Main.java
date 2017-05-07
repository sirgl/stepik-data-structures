import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final int NO_ERROR = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        int error = bracketCheck(text);
        if(error == NO_ERROR) {
            System.out.println("Success");
        } else {
            System.out.println(error + 1);
        }
    }

    static Map<Character, Character> brackets = new HashMap<>();

    public static class BracketEntry {
        public int position;
        public char bracket;

        public BracketEntry(int position, char bracket) {
            this.position = position;
            this.bracket = bracket;
        }
    }

    static {
        brackets.put('}', '{');
        brackets.put(']', '[');
        brackets.put(')', '(');
    }

    public static int bracketCheck(String text) {
        ArrayDeque<BracketEntry> bracketStack = new ArrayDeque<>();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!isBracket(ch)) {
                continue;
            }
            if (isOpenBracket(ch)) {
                bracketStack.push(new BracketEntry(i, ch));
            } else {
                if (bracketStack.isEmpty()) {
                    return i;
                }
                char previous = bracketStack.pop().bracket;
                char pair = brackets.get(ch);
                if (previous != pair) {
                    return i;
                }
            }
        }
        if(!bracketStack.isEmpty()) {
            return bracketStack.pop().position;
        }
        return NO_ERROR;
    }

    private static boolean isBracket(char ch) {
        return isCloseBracket(ch) || isOpenBracket(ch);
    }

    private static boolean isCloseBracket(char ch) {
        return ch == '}' || ch == ']' || ch == ')';
    }

    private static boolean isOpenBracket(char ch) {
        return ch == '{' || ch == '[' || ch == '(';
    }

}
