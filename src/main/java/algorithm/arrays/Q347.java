package algorithm.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
    347. Top K Frequent Elements
    Given an integer array nums and an integer k,
    return the k most frequent elements.
    You may return the answer in any order.

    Example 1:

    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]
    Example 2:

    Input: nums = [1], k = 1
    Output: [1]
*/
public class Q347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<Integer> maxHeapFrequency = new PriorityQueue<>(((o1, o2) -> count.get(o2) - count.get(o1)));
        maxHeapFrequency.addAll(count.keySet());

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = maxHeapFrequency.poll();
        }

        return res;
    }
}
