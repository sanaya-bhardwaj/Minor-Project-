import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ArrayLinkedListComparison {
    public static void main(String[] args) {
        int n = 1000; // Number of elements in the data sets
        int[] userArray = generateRandomArray(n);
        LinkedList<Integer> linkedList = createLinkedList(userArray);
        ArrayList<Integer> arrayList = createArrayList(userArray);

        // Measure the time for searching in a linked list
        long linkedListSearchTime = measureSearchTime(linkedList, userArray);

        // Measure the time for searching in an array list
        long arrayListSearchTime = measureSearchTime(arrayList, userArray);

        System.out.println("Search Time for Linked List: " + linkedListSearchTime + " nanoseconds");
        System.out.println("Search Time for Array List: " + arrayListSearchTime + " nanoseconds");
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

    // Create a linked list and insert data
    public static LinkedList<Integer> createLinkedList(int[] userArray) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int data : userArray) {
            linkedList.add(data);
        }
        return linkedList;
    }

    // Create an array list and insert data
    public static ArrayList<Integer> createArrayList(int[] userArray) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int data : userArray) {
            arrayList.add(data);
        }
        return arrayList;
    }

    // Measure the time taken to search all elements in the data set
    public static long measureSearchTime(LinkedList<Integer> list, int[] userArray) {
        long startTime = System.nanoTime();
        for (int data : userArray) {
            list.contains(data);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Measure the time taken to search all elements in the data set
    public static long measureSearchTime(ArrayList<Integer> list, int[] userArray) {
        long startTime = System.nanoTime();
        for (int data : userArray) {
            list.contains(data);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}

