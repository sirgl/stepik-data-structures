import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static class Entry {
        int value;
        int maxFromLowers;

        public Entry(int value, int maxFromLowers) {
            this.value = value;
            this.maxFromLowers = maxFromLowers;
        }
    }

    static int max(int a, int b) {
        if(a > b) {
            return a;
        }
        return b;
    }

    static class StackWithMax {
        Deque<Entry> stack = new ArrayDeque<>();

        void push(int value) {
            if(stack.isEmpty()) {
                stack.push(new Entry(value, value));
            } else {
                Entry peek = stack.getFirst();
                stack.push(new Entry(value, Main.max(value, peek.maxFromLowers)));
            }
        }

        int pop() {
            return stack.pop().value;
        }

        int max() {
            return stack.peek().maxFromLowers;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opsCount = scanner.nextInt();
        StackWithMax stackWithMax = new StackWithMax();
        scanner.nextLine();
        for (int i = 0; i < opsCount; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "push":
                    stackWithMax.push(Integer.parseInt(parts[1]));
                    break;
                case "pop":
                    stackWithMax.pop();
                    break;
                case "max":
                    System.out.println(stackWithMax.max());
                    break;
            }
        }
    }
}
