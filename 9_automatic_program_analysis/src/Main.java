import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(new FileInputStream("1.txt"));
        int varsCount = scanner.nextInt();
        int eqCount = scanner.nextInt();
        int ineqCount = scanner.nextInt();
        int[] parents = new int[varsCount];
        for (int i = 0; i < varsCount; i++) {
            parents[i] = i;
        }
        DisjointSet set = new DisjointSet(parents);
        for (int i = 0; i < eqCount; i++) {
            int v1 = scanner.nextInt() - 1;
            int v2 = scanner.nextInt() - 1;
            set.union(v1, v2);
        }
        for (int i = 0; i < ineqCount; i++) {
            int v1 = scanner.nextInt() - 1;
            int v2 = scanner.nextInt() - 1;
            if(set.find(v1) ==  set.find(v2)){
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);

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
            if (rank[fromTop] > rank[toTop]) {
                parent[toTop] = fromTop;
            } else {
                parent[fromTop] = toTop;
                if (rank[fromTop] == rank[toTop]) {
                    rank[fromTop]++;
                }
            }
        }
    }
}
