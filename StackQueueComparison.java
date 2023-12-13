import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Random;

public class StackQueueComparison {
    public static void main(String[] args) {
        int n = 1000; // Number of elements in the data sets
        int[] userArray = generateRandomArray(n);

        // Measure the time for stack operations
        long stackPushTime = measurePushTime(userArray);
        long stackPopTime = measurePopTime(n);

        // Measure the time for queue operations
        long queueEnqueueTime = measureEnqueueTime(userArray);
        long queueDequeueTime = measureDequeueTime(n);

        // Print details and explanation
        printDetailsAndExplanation(stackPushTime, stackPopTime, queueEnqueueTime, queueDequeueTime);
    }

    // Generate a random array of integers for testing
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }

    // Measure the time taken to push all elements into the stack
    public static long measurePushTime(int[] userArray) {
        Stack<Integer> stack = new Stack<>();
        long startTime = System.nanoTime();
        for (int data : userArray) {
            stack.push(data);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Measure the time taken to pop all elements from the stack
    public static long measurePopTime(int n) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        long startTime = System.nanoTime();
        while (!stack.isEmpty()) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Measure the time taken to enqueue all elements into the queue
    public static long measureEnqueueTime(int[] userArray) {
        Queue<Integer> queue = new LinkedList<>();
        long startTime = System.nanoTime();
        for (int data : userArray) {
            queue.offer(data);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Measure the time taken to dequeue all elements from the queue
    public static long measureDequeueTime(int n) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }

        long startTime = System.nanoTime();
        while (!queue.isEmpty()) {
            queue.poll();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Print details and explanation
    private static void printDetailsAndExplanation(long stackPushTime, long stackPopTime, long queueEnqueueTime, long queueDequeueTime) {
        System.out.println("Time for Stack Operations:");
        System.out.println("   - Push Time: " + stackPushTime + " nanoseconds");
        System.out.println("   - Pop Time: " + stackPopTime + " nanoseconds");

        System.out.println("Time for Queue Operations:");
        System.out.println("   - Enqueue Time: " + queueEnqueueTime + " nanoseconds");
        System.out.println("   - Dequeue Time: " + queueDequeueTime + " nanoseconds");

        // Explain why the final answer is considered the best
        explainEfficiency(stackPushTime, stackPopTime, queueEnqueueTime, queueDequeueTime);
    }

    // Explain why the final answer is considered the best
    private static void explainEfficiency(long stackPushTime, long stackPopTime, long queueEnqueueTime, long queueDequeueTime) {
        System.out.println("Efficiency Comparison:");

        // Determine the most efficient data structure based on the measured times
        if (stackPushTime + stackPopTime < queueEnqueueTime + queueDequeueTime) {
            System.out.println("Stack is the most efficient due to faster push and pop operations.");
        } else {
            System.out.println("Queue is the most efficient due to faster enqueue and dequeue operations.");
        }
    }
}

