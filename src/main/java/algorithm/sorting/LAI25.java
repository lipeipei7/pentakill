package algorithm.sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
    25. K Smallest In Unsorted Array
    Find the K smallest numbers in an unsorted integer array A.
    The returned numbers should be in ascending order.

    Assumptions
    A is not null
    K is >= 0 and smaller than or equal to size of A

    Return
    an array with size K containing the K smallest numbers in ascending order

    Examples
    A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
 */
public class LAI25 {
    public int[] kSmallest(int[] unsorted, int k) {
        if (unsorted == null || unsorted.length == 0 ||
                k <= 0 || k > unsorted.length) {
            return new int[0];
        }
        int[] res = new int[k];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int j : unsorted) {
            minHeap.offer(j);
        }
        for (int i = 0; i < k; i++) {
            if (!minHeap.isEmpty()) {
                res[i] = minHeap.poll();
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LAI25 solution = new LAI25();
        int[] res = solution.kSmallest(new int[]{3, 4, 1, 2, 5}, 3);
        System.out.println(Arrays.toString(res));
    }
}
