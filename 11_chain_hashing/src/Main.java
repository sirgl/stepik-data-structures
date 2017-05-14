import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(new FileInputStream("1.txt"));
        int bucketCount = scanner.nextInt();
        int requestCount = scanner.nextInt();

        scanner.nextLine();
        HashTable table = new HashTable(bucketCount);
        for (int i = 0; i < requestCount; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "add":
                    table.add(parts[1]);
                    break;
                case "del":
                    table.delete(parts[1]);
                    break;
                case "find":
                    if(table.contains(parts[1])){
                        System.out.println("yes");
                    } else {
                        System.out.println("no");
                    }
                    break;
                case "check":
                    System.out.println(table.getBucket(Integer.parseInt(parts[1]))
                            .stream()
                            .collect(Collectors.joining(" ")));
                    break;
            }
        }

//        System.out.println(hash("hell0"));
    }

    static class HashTable {
        private final int bucketCount;
        private List<List<String>> buckets;

        HashTable(int bucketCount) {
            this.bucketCount = bucketCount;
            buckets = new ArrayList<>(this.bucketCount);
            for (int i = 0; i < bucketCount; i++) {
                buckets.add(new LinkedList<>());
            }
        }

        void add(String str) {
            List<String> bucket = getBucket(str);
            if(bucket.contains(str)) {
                return;
            }
            bucket.add(0, str);
        }

        private List<String> getBucket(String str) {
            int bucketIndex = hash(str, bucketCount);
            return buckets.get(bucketIndex);
        }

        void delete(String str) {
            List<String> bucket = getBucket(str);
            bucket.remove(str);
        }

        boolean contains(String str) {
            return getBucket(str).contains(str);
        }

        List<String> getBucket(int i) {
            return buckets.get(i);
        }
    }

    static int hash(String s, int mod) {
        long sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i) * pow(263, i, 1000000007);
            sum = sum % 1000000007;
        }
        return (int) (sum % mod);
    }

    static long pow(long number, int pow, long mod) {
        long result = number;
        if(pow == 0) {
            return 1;
        }
        for (int i = 0; i < pow - 1; i++) {
            result = (result * number) % mod;
        }
        return result;
    }
}
