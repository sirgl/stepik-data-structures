import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(new FileInputStream("1.txt"));
        int tableCount = scanner.nextInt();
        int requestCount = scanner.nextInt();
        int[] sizes = new int[tableCount];
        int[] pointers = new int[tableCount];
        for (int i = 0; i < tableCount; i++) {
            sizes[i] = scanner.nextInt();
            pointers[i] = i;
        }
        int max = Arrays.stream(sizes).max().getAsInt();
        DisjointSet set = new DisjointSet(pointers);
        for (int i = 0; i < requestCount; i++) {
            int destination = scanner.nextInt() - 1;
            int source = scanner.nextInt() - 1;
            int destOrigin = set.find(destination);
            int sourceOrigin = set.find(source);
            if(destOrigin == sourceOrigin) {
                System.out.println(max);
                continue;
            }
            sizes[destOrigin] += sizes[sourceOrigin];
            sizes[sourceOrigin] = 0;
            set.union(destOrigin, sourceOrigin);
            max = max > sizes[destOrigin] ? max : sizes[destOrigin];
            System.out.println(max);
//            System.out.println("!");
        }

    }

    static class DisjointSet {
        final int[] parent;
        final int[] rank;


        DisjointSet(int[] parent) {
            this.parent = parent;
            rank = new int[parent.length];
        }

        int find(int x) {
            int parent = this.parent[x];
            if (x != parent) {
                this.parent[x] = find(this.parent[x]);
            }
            return this.parent[x];
        }

        void union(int from, int to) {
            int fromTop = find(from);
            int toTop = find(to);
            if (fromTop == toTop) {
                return;
            }
            parent[toTop] = fromTop;
//            if (rank[fromTop] > rank[toTop]) {
//                parent[toTop] = fromTop;
//            } else {
//                parent[fromTop] = toTop;
//                if (rank[fromTop] == rank[toTop]) {
//                    rank[fromTop]++;
//                }
//            }
        }
    }
}
