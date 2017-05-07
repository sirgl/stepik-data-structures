import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static class PackageData {
        int arrivalTime;
        int duration;
        int status = -1;

        PackageData(int arrivalTime, int duration) {
            this.arrivalTime = arrivalTime;
            this.duration = duration;
        }
    }

    enum EventType {
        EndOfTask,
        Arrival
    }

    static class Task {
        EventType eventType;
        PackageData packageData;

        public Task(EventType eventType, PackageData packageData) {
            this.eventType = eventType;
            this.packageData = packageData;
        }
    }

    static class State {
        int currentTime = 0;
        PackageData currentPackage = null;
        int durationLeft = 0;
        Queue<PackageData> queue = new ArrayDeque<>();
        int size;
        PackageData[] packages;
        int nextArrival = 0;

        State(int size, PackageData[] packages) {
            this.size = size;
            this.packages = packages;
        }

        void startHandlePackage(PackageData packageData) {
            currentPackage = packageData;
            packageData.status = currentTime;
            durationLeft = currentPackage.duration;
        }

        //null on end
        Task getNextTask() {
            if (nextArrival == packages.length && queue.isEmpty() && currentPackage == null) {
                return null;
            }
            if (currentPackage == null && queue.isEmpty()) {
                PackageData nextPackage = packages[nextArrival];
                return new Task(EventType.Arrival, nextPackage);
            }
            if(nextArrival == packages.length) { // no more arrivals
                return new Task(EventType.EndOfTask, currentPackage);
            } else {
                PackageData nextPackage = packages[nextArrival];
                int endOfCurrentTaskTime = currentTime + durationLeft;
                if (endOfCurrentTaskTime <= nextPackage.arrivalTime) {
                    return new Task(EventType.EndOfTask, currentPackage);
                } else {
                    return new Task(EventType.Arrival, nextPackage);
                }
            }

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int n = scanner.nextInt();
        PackageData[] packages = new PackageData[n];
        for (int i = 0; i < n; i++) {
            int arrivalTime = scanner.nextInt();
            int duration = scanner.nextInt();
            packages[i] = new PackageData(arrivalTime, duration);
        }
        State state = new State(size, packages);
        while (true) {
            Task nextTask = state.getNextTask();
            if (nextTask == null) {
                break;
            }
            if (nextTask.eventType == EventType.EndOfTask) {
                state.currentTime += state.durationLeft;
                state.durationLeft = 0;
                state.currentPackage = null;
                if(!state.queue.isEmpty()) {
                    state.startHandlePackage(state.queue.poll());
                }
            } else {
                state.nextArrival++;
                if(state.currentPackage != null) {
                    state.durationLeft -= nextTask.packageData.arrivalTime - state.currentTime;
                }
                state.currentTime = nextTask.packageData.arrivalTime;

                int add = state.currentPackage != null ? 1 : 0;
                if (state.queue.size() + add != size) {
                    state.queue.add(nextTask.packageData);
                }
            }
        }
        for (PackageData aPackage : packages) {
            System.out.println(aPackage.status);
        }
    }
}
