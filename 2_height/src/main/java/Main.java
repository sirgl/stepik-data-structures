import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            computeHeight(arr, i, heights);
        }
        System.out.println(Arrays.stream(heights).max().getAsInt());

    }

    public static int computeHeight(int[] arr, int i, int[] heights) {
        int parent = arr[i];
        if(parent == -1) {
            heights[i] = 1;
            return 1;
        }
        if(heights[i] != 0) {
            return heights[i];
        }
        int height = computeHeight(arr, parent, heights) + 1;
        heights[i] = height;
        return height;
    }
}
