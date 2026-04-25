package dsa.arrays;

import java.util.*;

/**
 * <h1>15. 3Sum</h1>
 *
 * <p>
 * Given an integer array {@code nums}, return all the triplets
 * {@code [nums[i], nums[j], nums[k]]} such that {@code i != j},
 * {@code i != k}, and {@code j != k}, and
 * {@code nums[i] + nums[j] + nums[k] == 0}.
 * </p>
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums = [-1,0,1,2,-1,-4]
 * <b>Output:</b> [[-1,-1,2],[-1,0,1]]
 * <b>Explanation:</b>
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 *
 * <b>Input:</b> nums = [0,1,1]
 * <b>Output:</b> []
 * <b>Explanation:</b> The only possible triplet does not sum up to 0.
 *
 * <b>Input:</b> nums = [0,0,0]
 * <b>Output:</b> [[0,0,0]]
 * <b>Explanation:</b> The only possible triplet sums up to 0.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 3 <= nums.length <= 3000}</li>
 * <li>{@code -10^5 <= nums[i] <= 10^5}</li>
 * </ul>
 *
 * <h2>Hints:</h2>
 * <ul>
 * <li>Fix one number and reduce to two-sum problem.</li>
 * <li>Sorting can help avoid duplicates and enable two-pointer approach.</li>
 * <li>For each fixed number, use two pointers to find pairs that sum to its
 * negation.</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/3sum/">LeetCode - 3Sum</a>
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        // sort the array
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {

            // special condition to skip duplicate results
            if (i > 0 && nums[i] == nums[i - 1]) {
                // if current element is same as previous element, skip this
                continue;
            }

            int x = nums[i];
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int y = nums[l];
                int z = nums[r];
                int sum = x + y + z;
                if (sum == 0) {
                    // found the solution
                    ans.add(List.of(x, y, z));
                    l++;
                    r--;

                    // special condition to skip duplicate results
                    while (l < r && nums[l] == nums[l - 1]) {
                        // do not consider same y values
                        l++;
                    }
                    while (l < r && nums[r] == nums[r + 1]) {
                        // do not consider same z values
                        r--;
                    }
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }

    private boolean tripletListsEqual(List<List<Integer>> actual, List<List<Integer>> expected) {
        if (actual.size() != expected.size())
            return false;

        // Sort both lists of lists for comparison
        List<List<Integer>> sortedActual = new ArrayList<>(actual);
        List<List<Integer>> sortedExpected = new ArrayList<>(expected);

        sortedActual.sort((a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i).compareTo(b.get(i));
                }
            }
            return a.size() - b.size();
        });

        sortedExpected.sort((a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i).compareTo(b.get(i));
                }
            }
            return a.size() - b.size();
        });

        return sortedActual.equals(sortedExpected);
    }

    private void printResult(String testName, List<List<Integer>> actual, List<List<Integer>> expected) {
        boolean passed = tripletListsEqual(actual, expected);
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + actual);
        System.out.println("  Expected: " + expected);
    }

    public static void main(String[] args) {
        ThreeSum solver = new ThreeSum();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Standard case with duplicates
        System.out.println("==================== Test Case 1 ====================");
        int[] nums1 = { -1, 0, 1, 2, -1, -4 };
        List<List<Integer>> expected1 = Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1));
        System.out.println("Scenario: Standard case with duplicates");
        System.out.println("Input: " + Arrays.toString(nums1));
        List<List<Integer>> result1 = solver.threeSum(nums1.clone());
        solver.printResult("Test 1 (Standard)", result1, expected1);
        totalTests++;
        if (solver.tripletListsEqual(result1, expected1))
            passedTests++;

        // Test Case 2: No triplets found
        System.out.println("\n==================== Test Case 2 ====================");
        int[] nums2 = { 0, 1, 1 };
        List<List<Integer>> expected2 = new ArrayList<>();
        System.out.println("Scenario: No triplets sum to 0");
        System.out.println("Input: " + Arrays.toString(nums2));
        List<List<Integer>> result2 = solver.threeSum(nums2.clone());
        solver.printResult("Test 2 (No triplets)", result2, expected2);
        totalTests++;
        if (solver.tripletListsEqual(result2, expected2))
            passedTests++;

        // Test Case 3: All zeros
        System.out.println("\n==================== Test Case 3 ====================");
        int[] nums3 = { 0, 0, 0 };
        List<List<Integer>> expected3 = Arrays.asList(Arrays.asList(0, 0, 0));
        System.out.println("Scenario: All zeros");
        System.out.println("Input: " + Arrays.toString(nums3));
        List<List<Integer>> result3 = solver.threeSum(nums3.clone());
        solver.printResult("Test 3 (All zeros)", result3, expected3);
        totalTests++;
        if (solver.tripletListsEqual(result3, expected3))
            passedTests++;

        // Test Case 4: Single valid triplet
        System.out.println("\n==================== Test Case 4 ====================");
        int[] nums4 = { -2, 0, 1, 1, 2 };
        List<List<Integer>> expected4 = Arrays.asList(Arrays.asList(-2, 0, 2), Arrays.asList(-2, 1, 1));
        System.out.println("Scenario: Single valid triplet");
        System.out.println("Input: " + Arrays.toString(nums4));
        List<List<Integer>> result4 = solver.threeSum(nums4.clone());
        solver.printResult("Test 4 (Single triplet)", result4, expected4);
        totalTests++;
        if (solver.tripletListsEqual(result4, expected4))
            passedTests++;

        // Test Case 5: Multiple negative numbers
        System.out.println("\n==================== Test Case 5 ====================");
        int[] nums5 = { -4, -1, -1, 0, 1, 2, 3 };
        List<List<Integer>> expected5 = Arrays.asList(
                Arrays.asList(-4, 1, 3),
                Arrays.asList(-1, -1, 2),
                Arrays.asList(-1, 0, 1));
        System.out.println("Scenario: Multiple negative numbers");
        System.out.println("Input: " + Arrays.toString(nums5));
        List<List<Integer>> result5 = solver.threeSum(nums5.clone());
        solver.printResult("Test 5 (Multiple negatives)", result5, expected5);
        totalTests++;
        if (solver.tripletListsEqual(result5, expected5))
            passedTests++;

        // Test Case 6: Minimum length array
        System.out.println("\n==================== Test Case 6 ====================");
        int[] nums6 = { -1, 0, 1 };
        List<List<Integer>> expected6 = Arrays.asList(Arrays.asList(-1, 0, 1));
        System.out.println("Scenario: Minimum length array");
        System.out.println("Input: " + Arrays.toString(nums6));
        List<List<Integer>> result6 = solver.threeSum(nums6.clone());
        solver.printResult("Test 6 (Min length)", result6, expected6);
        totalTests++;
        if (solver.tripletListsEqual(result6, expected6))
            passedTests++;

        // Test Case 7: Larger values
        System.out.println("\n==================== Test Case 7 ====================");
        int[] nums7 = { -100, -50, -10, 0, 10, 50, 100 };
        List<List<Integer>> expected7 = Arrays.asList(
                Arrays.asList(-100, 0, 100),
                Arrays.asList(-100, 50, 50),
                Arrays.asList(-50, 0, 50));
        System.out.println("Scenario: Larger values");
        System.out.println("Input: " + Arrays.toString(nums7));
        List<List<Integer>> result7 = solver.threeSum(nums7.clone());
        solver.printResult("Test 7 (Large values)", result7, expected7);
        totalTests++;
        if (solver.tripletListsEqual(result7, expected7))
            passedTests++;

        // Test Case 8: Many duplicates
        System.out.println("\n==================== Test Case 8 ====================");
        int[] nums8 = { -2, -2, -2, 0, 1, 1, 1, 2, 2, 2 };
        List<List<Integer>> expected8 = Arrays.asList(
                Arrays.asList(-2, 0, 2),
                Arrays.asList(-2, 1, 1));
        System.out.println("Scenario: Many duplicates");
        System.out.println("Input: " + Arrays.toString(nums8));
        List<List<Integer>> result8 = solver.threeSum(nums8.clone());
        solver.printResult("Test 8 (Many duplicates)", result8, expected8);
        totalTests++;
        if (solver.tripletListsEqual(result8, expected8))
            passedTests++;

        // Test Case 9: Mixed positive and negative
        System.out.println("\n==================== Test Case 9 ====================");
        int[] nums9 = { -3, -2, -1, 0, 1, 2, 3, 4, 5 };
        List<List<Integer>> expected9 = Arrays.asList(
                Arrays.asList(-3, 0, 3),
                Arrays.asList(-3, 1, 2),
                Arrays.asList(-2, -1, 3),
                Arrays.asList(-2, 0, 2),
                Arrays.asList(-1, 0, 1));
        System.out.println("Scenario: Mixed positive and negative");
        System.out.println("Input: " + Arrays.toString(nums9));
        List<List<Integer>> result9 = solver.threeSum(nums9.clone());
        solver.printResult("Test 9 (Mixed)", result9, expected9);
        totalTests++;
        if (solver.tripletListsEqual(result9, expected9))
            passedTests++;

        // Test Case 10: Only duplicates and one different
        System.out.println("\n==================== Test Case 10 ====================");
        int[] nums10 = { -1, -1, -1, -1, 0, 1, 1, 1, 1 };
        List<List<Integer>> expected10 = Arrays.asList(Arrays.asList(-1, 0, 1));
        System.out.println("Scenario: Mostly duplicates");
        System.out.println("Input: " + Arrays.toString(nums10));
        List<List<Integer>> result10 = solver.threeSum(nums10.clone());
        solver.printResult("Test 10 (Mostly duplicates)", result10, expected10);
        totalTests++;
        if (solver.tripletListsEqual(result10, expected10))
            passedTests++;

        // Summary
        System.out.println("\n==================== Summary ====================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Success Rate: " + (passedTests * 100 / totalTests) + "%");
    }
}
