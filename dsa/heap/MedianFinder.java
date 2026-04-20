import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Problem: 295. Find Median from Data Stream
 *
 * The median is the middle value in an ordered integer list. If the size of the
 * list is even,
 * there is no middle value, and the median is the mean of the two middle
 * values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 *
 * Implement the MedianFinder class:
 * - MedianFinder() initializes the MedianFinder object.
 * - void addNum(int num) adds the integer num from the data stream to the data
 * structure.
 * - double findMedian() returns the median of all elements so far.
 *
 * Example 1:
 * Input: ["MedianFinder", "addNum", "addNum", "findMedian", "addNum",
 * "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output: [null, null, null, 1.5, null, 2.0]
 *
 * Explanation:
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1); // arr = [1]
 * medianFinder.addNum(2); // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3); // arr = [1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * Constraints:
 * - -10^5 <= num <= 10^5
 * - There will be at least one element in the data structure before calling
 * findMedian.
 * - At most 5 * 10^4 calls will be made to addNum and findMedian.
 */

public class MedianFinder {

    // maintains the left half of the input
    PriorityQueue<Integer> leftMaxHeap;
    // maintains the right half of the input
    PriorityQueue<Integer> rightMinHeap;

    public MedianFinder() {
        leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        rightMinHeap = new PriorityQueue<>();
    }

    /**
     * Adds a num into the data structure.
     */
    public void addNum(int num) {
        // offer to left half
        leftMaxHeap.offer(num);
        // move left top to right
        rightMinHeap.offer(leftMaxHeap.poll());

        // in case the right heap has same elements as left, then offer one from right->left
        // keeps left size = right size + 1
        if (leftMaxHeap.size() < rightMinHeap.size()) {
            leftMaxHeap.offer(rightMinHeap.poll());
        }
    }

    /**
     * Returns the median of current data stream.
     */
    public double findMedian() {
        if (leftMaxHeap.size() == rightMinHeap.size()) {
            // even length
            return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
        } else {
            return leftMaxHeap.peek() * 1.0;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Example from problem
        System.out.println("Test Case 1: Example from problem statement");
        MedianFinder mf1 = new MedianFinder();
        mf1.addNum(1);
        System.out.println("addNum(1)");
        mf1.addNum(2);
        System.out.println("addNum(2)");
        double median1 = mf1.findMedian();
        System.out.println("findMedian() = " + median1 + " (Expected: 1.5)");
        mf1.addNum(3);
        System.out.println("addNum(3)");
        double median2 = mf1.findMedian();
        System.out.println("findMedian() = " + median2 + " (Expected: 2.0)");
        System.out.println();

        // Test Case 2: Single element
        System.out.println("Test Case 2: Single element");
        MedianFinder mf2 = new MedianFinder();
        mf2.addNum(5);
        double median3 = mf2.findMedian();
        System.out.println("addNum(5), findMedian() = " + median3 + " (Expected: 5.0)");
        System.out.println();

        // Test Case 3: Even number of elements
        System.out.println("Test Case 3: Even number of elements");
        MedianFinder mf3 = new MedianFinder();
        mf3.addNum(1);
        mf3.addNum(2);
        mf3.addNum(3);
        mf3.addNum(4);
        double median4 = mf3.findMedian();
        System.out.println("After adding [1, 2, 3, 4], findMedian() = " + median4 + " (Expected: 2.5)");
        System.out.println();

        // Test Case 4: Odd number of elements
        System.out.println("Test Case 4: Odd number of elements");
        MedianFinder mf4 = new MedianFinder();
        mf4.addNum(1);
        mf4.addNum(2);
        mf4.addNum(3);
        mf4.addNum(4);
        mf4.addNum(5);
        double median5 = mf4.findMedian();
        System.out.println("After adding [1, 2, 3, 4, 5], findMedian() = " + median5 + " (Expected: 3.0)");
        System.out.println();

        // Test Case 5: Negative numbers and unordered addition
        System.out.println("Test Case 5: Negative numbers and unordered addition");
        MedianFinder mf5 = new MedianFinder();
        mf5.addNum(5);
        System.out.println("addNum(5)");
        mf5.addNum(-1);
        System.out.println("addNum(-1)");
        double median6 = mf5.findMedian();
        System.out.println("findMedian() = " + median6 + " (Expected: 2.0)");
        mf5.addNum(3);
        System.out.println("addNum(3)");
        double median7 = mf5.findMedian();
        System.out.println("findMedian() = " + median7 + " (Expected: 3.0)");
        System.out.println();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
