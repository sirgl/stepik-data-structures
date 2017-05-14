import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static String EMPTY = "not found";

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner scanner = new Scanner(new FileInputStream("1.txt"));
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        scanner.nextLine();
//        HashTable<Integer, String> table = new HashTable<>(count, EMPTY);
        HashMap<Integer, String> table = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            int number = Integer.parseInt(words[1]);
            switch (words[0]) {
                case "add":
                    String value = words[2];
                    table.put(number, value);
                    break;
                case "find":
                    String x = table.get(number);
                    if(x == null) {
                        System.out.println(EMPTY);
                        continue;
                    }
                    System.out.println(x);
                    break;
                case "del":
                    table.remove(number);
                    break;
            }
        }

    }

    public static class HashTable<K, V> {
        private final int maxCount;
        Entry<K, V>[] entries;
        private final V defaultValue;

        public HashTable(int maxCount, V defaultValue) {
            this.maxCount = maxCount;
            //noinspection unchecked
            entries = new Entry[maxCount];
            this.defaultValue = defaultValue;
        }

        V get(K key) {
            for (int i = 0; i < maxCount; i++) {
                Entry<K, V> target = entries[hash(key, i)];
                if(target == null) {
                    return null;
                }
                if (target.key.equals(key)) {
                    return target.value;
                }
            }
            return null;
        }

        void put(K key, V value) {
            for (int i = 0; i < maxCount; i++) {
                Entry<K, V> target = entries[hash(key, i)];
                if(target != null && target.key.equals(key)) {
                    entries[hash(key, i)] = new Entry<>(key, value);
                }
                if(target == null || target.key.equals(defaultValue)) {
                    entries[hash(key, i)] = new Entry<>(key, value);
                    return;
                }
            }
        }

        void delete(K key) {
            for (int i = 0; i < maxCount; i++) {
                Entry<K, V> target = entries[hash(key, i)];
                if(target == null) {
                    return;
                }
                if (target.key.equals(key)) {
                    entries[hash(key, i)].value = defaultValue;
                    return;
                }
            }
        }

        int hash(K key, int j) {
            return (key.hashCode() + j) % maxCount;
        }

        static class Entry<K, V> {
            K key;
            V value;

            public Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public int hashCode() {
                return key.hashCode();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Entry<?, ?> entry = (Entry<?, ?>) o;

                if (!key.equals(entry.key)) return false;
                return value != null ? value.equals(entry.value) : entry.value == null;
            }
        }
    }


}
