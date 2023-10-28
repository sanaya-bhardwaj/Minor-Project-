import java.util.Arrays;
import java.util.Random;

public class SortComparison {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] leftHalf = Arrays.copyOfRange(arr, 0, mid);
            int[] rightHalf = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(leftHalf);
            mergeSort(rightHalf);

            int i = 0, j = 0, k = 0;

            while (i < leftHalf.length && j < rightHalf.length) {
                if (leftHalf[i] < rightHalf[j]) {
                    arr[k] = leftHalf[i];
                    i++;
                } else {
                    arr[k] = rightHalf[j];
                    j++;
                }
                k++;
            }

            while (i < leftHalf.length) {
                arr[k] = leftHalf[i];
                i++;
                k++;
            }

            while (j < rightHalf.length) {
                arr[k] = rightHalf[j];
                j++;
                k++;
            }
        }
    }

    public static int[] quickSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        } else {
            int pivot = arr[0];
            int[] left = new int[0];
            int[] right = new int[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < pivot) {
                    left = Arrays.copyOf(left, left.length + 1);
                    left[left.length - 1] = arr[i];
                } else {
                    right = Arrays.copyOf(right, right.length + 1);
                    right[right.length - 1] = arr[i];
                }
            }
            left = quickSort(left);
            right = quickSort(right);
            int[] result = new int[left.length + right.length + 1];
            System.arraycopy(left, 0, result, 0, left.length);
            result[left.length] = pivot;
            System.arraycopy(right, 0, result, left.length + 1, right.length);
            return result;
        }
    }

    public static void main(String[] args) {
        Random random = new Random(42);
        int[] data = new int[1000];
        for (int i = 0; i < 1000; i++) {
            data[i] = random.nextInt(1000) + 1;
        }

        long startTime, endTime;
        long bubbleTime, mergeTime, quickTime;

        startTime = System.currentTimeMillis();
        bubbleSort(data.clone());
        endTime = System.currentTimeMillis();
        bubbleTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        mergeSort(data.clone());
        endTime = System.currentTimeMillis();
        mergeTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        quickSort(data.clone());
        endTime = System.currentTimeMillis();
        quickTime = endTime - startTime;

        System.out.println("Bubble Sort Time: " + bubbleTime + " milliseconds");
        System.out.println("Merge Sort Time: " + mergeTime + " milliseconds");
        System.out.println("Quick Sort Time: " + quickTime + " milliseconds");
    }
}
