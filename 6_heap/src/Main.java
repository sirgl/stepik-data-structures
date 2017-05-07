import java.util.Scanner;

public class Main {
    public static class Heap {
        private int size = 0;
        private int maxSize;
        private int[] arr;
        int[] swapsFrom;
        int[] swapsTo;
        int currentSwap = 0;

        public Heap(int maxSize) {
            this.maxSize = maxSize;
            arr = new int[this.maxSize];
            swapsFrom = new int[4 * this.maxSize];
            swapsTo = new int[4 * this.maxSize];
        }

        private int parent(int i) {
            return (i - 1) / 2;
        }

        private int left(int i) {
            return 2 * i + 1;
        }

        private int right(int i) {
            return 2 * i + 2;
        }

        private void siftUp(int i) {
            while (i > 0 && arr[parent(i)] > arr[i]) {
                swapsFrom[currentSwap] = i;
                swapsTo[currentSwap] = parent(i);
                currentSwap++;
                swap(arr, parent(i), i);
                i = parent(i);
            }
        }

        private void siftDown(int i) {
            int minIndex = i;
            int l = left(i);
            if(l < size && arr[l] < arr[minIndex]) {
                minIndex = l;
            }
            int r = right(i);
            if(r < size && arr[r] < arr[minIndex]) {
                minIndex = r;
            }
            if(i != minIndex) {
                swapsFrom[currentSwap] = i;
                swapsTo[currentSwap] = minIndex;
                currentSwap++;
                swap(arr, i, minIndex);
                siftDown(minIndex);
            }
        }

        public void insert(int value) {
            arr[size] = value;
            siftUp(size);
            size++;
        }

        public void build(int[] arr) {
            size = arr.length;
            this.arr = arr;
            for (int i = size / 2 ; i >= 0; i--) {
                siftDown(i);
            }
        }
    }

    private static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Heap heap = new Heap(n);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        heap.build(arr);
        System.out.println(heap.currentSwap);
        for (int i = 0; i < heap.currentSwap; i++) {
            System.out.println(heap.swapsFrom[i] + " " + heap.swapsTo[i]);
        }
    }
}
