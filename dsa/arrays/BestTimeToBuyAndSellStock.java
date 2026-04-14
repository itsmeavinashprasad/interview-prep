package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>121. Best Time to Buy and Sell Stock</h1>
 *
 * <p>
 * You are given an array {@code prices} where {@code prices[i]} is the price
 * of a given stock on the {@code i}th day.
 * </p>
 * <p>
 * You want to maximize your profit by choosing a <b>single day</b> to buy one
 * stock
 * and choosing a <b>different day in the future</b> to sell that stock.
 * </p>
 * <p>
 * Return the maximum profit you can achieve from this transaction. If you
 * cannot achieve any profit, return {@code 0}.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> prices = [7,1,5,3,6,4]
 * <b>Output:</b> 5
 * <b>Explanation:</b> Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * <b>Input:</b> prices = [7,6,4,3,1]
 * <b>Output:</b> 0
 * <b>Explanation:</b> In this case, no transactions are done and the max profit = 0.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= prices.length <= 10^5}</li>
 * <li>{@code 0 <= prices[i] <= 10^4}</li>
 * </ul>
 *
 * @see <a href=
 *      "https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">LeetCode
 *      - Best Time to Buy and Sell Stock</a>
 */
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        // TODO: implement
        int bp = Integer.MAX_VALUE;
        int sp = Integer.MIN_VALUE;
        int profit = 0;

        for (int x : prices) {
            // update if today max profit can be booked
            sp = Math.max(sp, x);
            profit = Math.max(profit, sp - bp);

            // if lesser bp is found, then reset
            if (x < bp) {
                bp = x;
                sp = x;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock solver = new BestTimeToBuyAndSellStock();

        // Test Case 1: [7,1,5,3,6,4] → Expected: 5
        int[] prices1 = { 7, 1, 5, 3, 6, 4 };
        System.out.println("Test 1 - Input: " + Arrays.toString(prices1));
        System.out.println("Output:   " + solver.maxProfit(prices1));
        System.out.println("Expected: 5");

        // Test Case 2: [7,6,4,3,1] → Expected: 0
        int[] prices2 = { 7, 6, 4, 3, 1 };
        System.out.println("\nTest 2 - Input: " + Arrays.toString(prices2));
        System.out.println("Output:   " + solver.maxProfit(prices2));
        System.out.println("Expected: 0");
    }
}
