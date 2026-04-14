package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>Maximum Subarray (Kadane's Algorithm)</h1>
 *
 * <p>
 * Given an integer array {@code nums}, find the subarray with the largest sum,
 * and return its sum.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Input:</b>  nums = [-2,1,-3,4,-1,2,1,-5,4]
 * <b>Output:</b> 6
 * <b>Explanation:</b> The subarray [4,-1,2,1] has the largest sum 6.
 *
 * <b>Input:</b>  nums = [1]
 * <b>Output:</b> 1
 * <b>Explanation:</b> The subarray [1] has the largest sum 1.
 *
 * <b>Input:</b>  nums = [5,4,-1,7,8]
 * <b>Output:</b> 23
 * <b>Explanation:</b> The subarray [5,4,-1,7,8] has the largest sum 23.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= nums.length <= 10^5}</li>
 * <li>{@code -10^4 <= nums[i] <= 10^4}</li>
 * </ul>
 *
 * <h2>Follow up:</h2>
 * <ul>
 * <li>If you have figured out the O(n) solution, try coding another solution
 * using the divide and conquer approach, which is more subtle.</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/maximum-subarray/">LeetCode -
 *      Maximum Subarray</a>
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        // TODO: implement
        int maxsum = Integer.MIN_VALUE;
        int currentsum = 0;
        int i = 0;
        while (i < nums.length) {
            currentsum += nums[i];
            maxsum = Math.max(currentsum, maxsum);
            if (currentsum <= 0) {
                // then break the seq
                currentsum = 0;
            }
            i++;
        }
        return maxsum;
    }

    public static void main(String[] args) {
        MaximumSubarray solver = new MaximumSubarray();

        // Test Case 1: [-2,1,-3,4,-1,2,1,-5,4] → Expected: 6
        int[] nums1 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println("Test 1 - Input: " + Arrays.toString(nums1));
        System.out.println("Output:   " + solver.maxSubArray(nums1));
        System.out.println("Expected: 6");

        // Test Case 2: [1] → Expected: 1
        int[] nums2 = { 1 };
        System.out.println("\nTest 2 - Input: " + Arrays.toString(nums2));
        System.out.println("Output:   " + solver.maxSubArray(nums2));
        System.out.println("Expected: 1");

        // Test Case 3: [5,4,-1,7,8] → Expected: 23
        int[] nums3 = { 5, 4, -1, 7, 8 };
        System.out.println("\nTest 3 - Input: " + Arrays.toString(nums3));
        System.out.println("Output:   " + solver.maxSubArray(nums3));
        System.out.println("Expected: 23");

        // Test Case 4: [-1,-2,-3] → Expected: -1
        int[] nums4 = { -1, -2, -3 };
        System.out.println("\nTest 4 - Input: " + Arrays.toString(nums4));
        System.out.println("Output:   " + solver.maxSubArray(nums4));
        System.out.println("Expected: -1");
    }
}
