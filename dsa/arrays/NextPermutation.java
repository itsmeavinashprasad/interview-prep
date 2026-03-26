package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>Next Permutation</h1>
 *
 * <p>
 * A <b>permutation</b> of an array of integers is an arrangement of its
 * members into a sequence or linear order.
 * </p>
 *
 * <p>
 * The <b>next permutation</b> of an array of integers is the next
 * lexicographically greater permutation of its integers. If no such arrangement
 * is possible, the array must be rearranged as the lowest possible order
 * (i.e., sorted in ascending order).
 * </p>
 *
 * <p>
 * The replacement must be <b>in place</b> and use only <b>constant extra
 * memory</b>.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Input:</b>  nums = [1,2,3]    <b>Output:</b> [1,3,2]
 * <b>Input:</b>  nums = [3,2,1]    <b>Output:</b> [1,2,3]
 * <b>Input:</b>  nums = [1,1,5]    <b>Output:</b> [1,5,1]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= nums.length <= 100}</li>
 * <li>{@code 0 <= nums[i] <= 100}</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/next-permutation/">LeetCode -
 *      Next Permutation</a>
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        // TODO: implement

        // start with end
        // find ith element till when it is in increasing order
        // find next greater element (j) of [i-1] in i...n-1
        // swap [i-1] i...[j]..n-1
        // sort i...n-1

        int n = nums.length;
        int i = n - 1;

        // find increasing order
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }

        if (i == 0) {
            // nums is already highest
            Arrays.sort(nums);
            return;
        }
        // find next greater
        int l = i - 1;
        int j = i;
        int nextGraterIndex = j;
        int diff = nums[i] - nums[l];
        while (j < n) {
            if (nums[j] > nums[l] && nums[j] - nums[l] < diff) {
                nextGraterIndex = j;
                diff = nums[j] - nums[l];
            }
            j++;
        }

        // swap
        int t = nums[l];
        nums[l] = nums[nextGraterIndex];
        nums[nextGraterIndex] = t;

        // sort
        Arrays.sort(nums, i, n);

    }

    public static void main(String[] args) {
        NextPermutation solver = new NextPermutation();

        // Test Case 1: [1,2,3] → expected [1,3,2]
        int[] nums1 = { 1, 2, 3 };
        System.out.println("Test 1 - Input:    " + Arrays.toString(nums1));
        solver.nextPermutation(nums1);
        System.out.println("Test 1 - Output:   " + Arrays.toString(nums1));
        System.out.println("Test 1 - Expected: [1, 3, 2]");

        // Test Case 2: [3,2,1] → expected [1,2,3] (largest permutation wraps around)
        int[] nums2 = { 3, 2, 1 };
        System.out.println("\nTest 2 - Input:    " + Arrays.toString(nums2));
        solver.nextPermutation(nums2);
        System.out.println("Test 2 - Output:   " + Arrays.toString(nums2));
        System.out.println("Test 2 - Expected: [1, 2, 3]");

        // Test Case 3: [1,1,5] → expected [1,5,1]
        int[] nums3 = { 1, 1, 5 };
        System.out.println("\nTest 3 - Input:    " + Arrays.toString(nums3));
        solver.nextPermutation(nums3);
        System.out.println("Test 3 - Output:   " + Arrays.toString(nums3));
        System.out.println("Test 3 - Expected: [1, 5, 1]");

        // Test Case 4: [1,3,2] → expected [2,1,3]
        int[] nums4 = { 1, 3, 2 };
        System.out.println("\nTest 4 - Input:    " + Arrays.toString(nums4));
        solver.nextPermutation(nums4);
        System.out.println("Test 4 - Output:   " + Arrays.toString(nums4));
        System.out.println("Test 4 - Expected: [2, 1, 3]");

        // Test Case 5: [5,1,1] → expected [1,1,5]
        int[] nums5 = { 5, 1, 1 };
        System.out.println("\nTest 5 - Input:    " + Arrays.toString(nums5));
        solver.nextPermutation(nums5);
        System.out.println("Test 5 - Output:   " + Arrays.toString(nums5));
        System.out.println("Test 5 - Expected: [1, 1, 5]");
    }
}
