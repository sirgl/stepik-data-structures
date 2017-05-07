import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static class Task {
        int processor;
        long endTime;

        Task(int processor, long endTime) {
            this.processor = processor;
            this.endTime = endTime;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int processorCount = scanner.nextInt();
        int taskCount = scanner.nextInt();
        long[] times = new long[taskCount];
        for (int i = 0; i < taskCount; i++) {
            times[i] = scanner.nextLong();
        }
        PriorityQueue<Task> queue = new PriorityQueue<>((t1, t2) -> {
            if(t1.endTime > t2.endTime) {
                return 1;
            }
            if (t1.endTime < t2.endTime) {
                return -1;
            }
            if(t1.processor > t2.processor) {
                return 1;
            }
            if(t1.processor < t2.processor) {
                return -1;
            }
            // proc1 = proc 2

            return 0;
        });
        if(taskCount <= processorCount) {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(i + " " + 0);
            }
            return;
        }
        for (int i = 0; i < processorCount; i++) {
            System.out.println(i + " " + 0);
            queue.add(new Task(i, times[i]));
        }
        for (int i = processorCount; i < taskCount; i++) {
            Task task = queue.poll();
            System.out.println(task.processor + " " + task.endTime);
            queue.add(new Task(task.processor, task.endTime + times[i]));
        }
    }
}
