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

        System.out.println("Stack Push Time: " + stackPushTime + " nanoseconds");
        System.out.println("Stack Pop Time: " + stackPopTime + " nanoseconds");
        System.out.println("Queue Enqueue Time: " + queueEnqueueTime + " nanoseconds");
        System.out.println("Queue Dequeue Time: " + queueDequeueTime + " nanoseconds");
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
}

