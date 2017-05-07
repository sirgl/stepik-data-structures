import java.util.*;

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


    interface Stack<T> {
        void push(T t);
        T pop();
        List<T> getAll();
        int size();
        default boolean isEmpty() {
            return size() == 0;
        }
    }


    class ArrayStack<T> implements Stack<T> {
        private List<T> list = new ArrayList<>();

        @Override
        public void push(T t) {
            list.add(t);
        }

        @Override
        public T pop() {
            return list.remove(list.size() - 1);
        }

        @Override
        public List<T> getAll() {
            List<T> ts = list;
            list = new ArrayList<>();
            return ts;
        }

        @Override
        public int size() {
            return list.size();
        }
    }

//    class QueueWithMax {
//        private StackWithMax frontStack;
//        private StackWithMax backStack;
//
//        void push(int value){
//            frontStack.push(value);
//        }
//
//        int pop(){
//
//        }
//
//        int max(){
//
//        }
//    }



    //NOT DONE :(
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int size = scanner.nextInt();
//        StackWithMax stack = new StackWithMax();
        for (int i = 0; i < size; i++) {
//            stack.push(arr[i]);
        }
        for (int windowPosition = 0; windowPosition < n - size; windowPosition++) {

        }
    }
}
