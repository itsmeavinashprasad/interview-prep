import java.util.*;
import java.util.Map.Entry;

/**
 * Problem: 347. Top K Frequent Elements
 *
 * Given an integer array nums and an integer k, return the k most frequent
 * elements.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Example 3:
 * Input: nums = [4,1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 * - 1 <= k <= the number of unique elements in the array
 * - It is guaranteed that the answer is unique.
 */

public class TopKFrequentElements {

    /**
     * Find the k most frequent elements in the array
     * 
     * @param nums the input array
     * @param k    the number of most frequent elements to return
     * @return array containing the k most frequent elements
     */
    public int[] topKFrequent(int[] nums, int k) {
        // count frequncies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int x : nums) {
            freqMap.computeIfPresent(x, (k1, v) -> v = v + 1);
            freqMap.putIfAbsent(x, 1);
        }

        // prepare a min heap
        PriorityQueue<Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Entry::getValue));

        // insert to the heap
        for (Entry<Integer, Integer> entry : freqMap.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                Entry<Integer, Integer> e = pq.poll();
                System.out.println("Kicked out num: " + e.getKey() + ", freq: " + e.getValue());
            }
        }

        // prepare the result
        int index = 0;
        int[] ans = new int[k];
        while (!pq.isEmpty()) {
            ans[index++] = pq.poll().getKey();
        }

        return ans;
    }
    
    
    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();

        // Test Case 1
        int[] nums1 = { 1, 1, 1, 2, 2, 3 };
        int k1 = 2;
        int[] result1 = solution.topKFrequent(nums1, k1);
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = [1,1,1,2,2,3], k = 2");
        System.out.println("Output: " + Arrays.toString(result1));
        System.out.println("Expected: [1,2] or [2,1]");
        System.out.println();

        // Test Case 2
        int[] nums2 = { 1 };
        int k2 = 1;
        int[] result2 = solution.topKFrequent(nums2, k2);
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = [1], k = 1");
        System.out.println("Output: " + Arrays.toString(result2));
        System.out.println("Expected: [1]");
        System.out.println();

        // Test Case 3
        int[] nums3 = { 4, 1, 1, 1, 2, 2, 3 };
        int k3 = 2;
        int[] result3 = solution.topKFrequent(nums3, k3);
        System.out.println("Test Case 3:");
        System.out.println("Input: nums = [4,1,1,1,2,2,3], k = 2");
        System.out.println("Output: " + Arrays.toString(result3));
        System.out.println("Expected: [1,2]");
        System.out.println();

        // Test Case 4
        int[] nums4 = { 1, 2, 2, 3, 3, 3, 4, 4, 4, 4 };
        int k4 = 3;
        int[] result4 = solution.topKFrequent(nums4, k4);
        System.out.println("Test Case 4:");
        System.out.println("Input: nums = [1,2,2,3,3,3,4,4,4,4], k = 3");
        System.out.println("Output: " + Arrays.toString(result4));
        System.out.println("Expected: [4,3,2]");
    }
}
