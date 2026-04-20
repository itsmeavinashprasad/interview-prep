package dsa.tree;

/**
 * <h1>208. Implement Trie (Prefix Tree)</h1>
 *
 * <p>
 * A <b>trie</b> (pronounced as "try") or <b>prefix tree</b> is a tree data
 * structure used to efficiently
 * store and retrieve keys in a dataset of strings. There are various
 * applications of this data structure,
 * such as autocomplete and spellchecker.
 * </p>
 *
 * <p>
 * Implement the Trie class:
 * </p>
 *
 * <ul>
 * <li><code>Trie()</code> Initializes the trie object.</li>
 * <li><code>void insert(String word)</code> Inserts the string
 * <code>word</code> into the trie.</li>
 * <li><code>boolean search(String word)</code> Returns <code>true</code> if the
 * string <code>word</code> is in the trie
 * (i.e. was inserted before), and <code>false</code> otherwise.</li>
 * <li><code>boolean startsWith(String prefix)</code> Returns <code>true</code>
 * if there is a previously inserted string
 * <code>word</code> that has the prefix <code>prefix</code>, and
 * <code>false</code> otherwise.</li>
 * </ul>
 *
 * <h2>Example</h2>
 *
 * <pre>
 * Input:
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 *
 * Output:
 * [null, null, true, false, true, null, true]
 *
 * Explanation:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>1 &lt;= word.length, prefix.length &lt;= 2000</li>
 * <li>word and prefix consist only of lowercase English letters.</li>
 * <li>At most 3 * 10^4 calls in total will be made to <code>insert</code>,
 * <code>search</code>, and <code>startsWith</code>.</li>
 * </ul>
 *
 * @see <a href=
 *      "https://leetcode.com/problems/implement-trie-prefix-tree/description/">LeetCode
 *      -
 *      Implement Trie (Prefix Tree)</a>
 */

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;
    int wordCount;
    int endCount;

    TrieNode() {
        children = new TrieNode[26];
        wordCount = 0;
        endCount = 0;
        isEndOfWord = false;
    }
}

public class ImplementTrie {

    private TrieNode root;

    /**
     * Initialize the Trie object
     */
    public ImplementTrie() {
        root = new TrieNode();
    }

    /**
     * Insert a word into the trie
     *
     * @param word the word to insert
     */
    public void insert(String word) {
        // start from root
        TrieNode node = root;

        // for all chars of the word, construct Trie chiildren
        for (char ch : word.toCharArray()) {
            int i = ch - 'a';

            // if any children with ch is not yet initialized, then do it
            if (node.children[i] == null) {
                node.children[i] = new TrieNode();
            }

            node = node.children[i];
            node.wordCount++;
        }

        // mark last one as a end of a word
        node.isEndOfWord = true;
        node.endCount++;
    }

    /**
     * Search for a word in the trie
     *
     * @param word the word to search for
     * @return true if the word exists in the trie, false otherwise
     */
    public boolean search(String word) {
        TrieNode lastnode = searchPrefix(word);
        return lastnode != null && lastnode.isEndOfWord == true;
    }

    /**
     * Check if there is any word in the trie that starts with the given prefix
     *
     * @param prefix the prefix to search for
     * @return true if there exists a word with this prefix, false otherwise
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    /**
     * Count how many times a word is inserted in the trie
     *
     * @param word the word to count
     * @return the number of times the word was inserted
     */
    public int countWordsEqualTo(String word) {
        TrieNode node = searchPrefix(word);
        if (node != null && node.isEndOfWord) {
            return node.endCount;
        }
        return 0;
    }

    /**
     * Count how many words in the trie start with the given prefix
     *
     * @param prefix the prefix to search for
     * @return the number of words that start with this prefix
     */
    public int countWordsStartingWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        if (node != null) {
            return node.wordCount;
        }
        return 0;
    }

    /**
     * Helper method to find if the given word is present or not
     * 
     * @param prefix the prefix to search
     * @return last node of the prefix is present, otherwise null
     */
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;

        // starting with root check all chars of the word
        for (char ch : prefix.toCharArray()) {
            int i = ch - 'a';

            // if Trie is not initialized for any char of the word, then the word is not
            // present
            if (node.children[i] != null) {
                node = node.children[i];
            } else {
                return null;
            }

        }
        return node;
    }

    public static void main(String[] args) {
        // Test case 1: Basic operations from the example
        ImplementTrie trie1 = new ImplementTrie();
        trie1.insert("apple");
        System.out.println("Test 1a - search('apple'): " + trie1.search("apple") + " (Expected: true)");
        System.out.println("Test 1b - search('app'): " + trie1.search("app") + " (Expected: false)");
        System.out.println("Test 1c - startsWith('app'): " + trie1.startsWith("app") + " (Expected: true)");
        trie1.insert("app");
        System.out.println("Test 1d - search('app'): " + trie1.search("app") + " (Expected: true)");

        // Test case 2: Multiple inserts
        ImplementTrie trie2 = new ImplementTrie();
        trie2.insert("hello");
        trie2.insert("help");
        trie2.insert("heap");
        System.out.println("Test 2a - search('hello'): " + trie2.search("hello") + " (Expected: true)");
        System.out.println("Test 2b - search('help'): " + trie2.search("help") + " (Expected: true)");
        System.out.println("Test 2c - search('heap'): " + trie2.search("heap") + " (Expected: true)");
        System.out.println("Test 2d - search('hel'): " + trie2.search("hel") + " (Expected: false)");
        System.out.println("Test 2e - startsWith('hel'): " + trie2.startsWith("hel") + " (Expected: true)");
        System.out.println("Test 2f - startsWith('hea'): " + trie2.startsWith("hea") + " (Expected: true)");

        // Test case 3: Empty string and single character
        ImplementTrie trie3 = new ImplementTrie();
        trie3.insert("a");
        System.out.println("Test 3a - search('a'): " + trie3.search("a") + " (Expected: true)");
        System.out.println("Test 3b - search('b'): " + trie3.search("b") + " (Expected: false)");
        System.out.println("Test 3c - startsWith('a'): " + trie3.startsWith("a") + " (Expected: true)");
        System.out.println("Test 3d - startsWith('b'): " + trie3.startsWith("b") + " (Expected: false)");

        // Test case 4: Prefix matching
        ImplementTrie trie4 = new ImplementTrie();
        trie4.insert("cat");
        trie4.insert("car");
        trie4.insert("dog");
        System.out.println("Test 4a - startsWith('ca'): " + trie4.startsWith("ca") + " (Expected: true)");
        System.out.println("Test 4b - startsWith('do'): " + trie4.startsWith("do") + " (Expected: true)");
        System.out.println("Test 4c - startsWith('ca'): " + trie4.startsWith("ca") + " (Expected: true)");
        System.out.println("Test 4d - startsWith('co'): " + trie4.startsWith("co") + " (Expected: false)");
        System.out.println("Test 4e - search('ca'): " + trie4.search("ca") + " (Expected: false)");

        // Test case 5: Non-existent words
        ImplementTrie trie5 = new ImplementTrie();
        trie5.insert("word");
        System.out.println("Test 5a - search('wor'): " + trie5.search("wor") + " (Expected: false)");
        System.out.println("Test 5b - search('word'): " + trie5.search("word") + " (Expected: true)");
        System.out.println("Test 5c - search('words'): " + trie5.search("words") + " (Expected: false)");
        System.out.println("Test 5d - startsWith('wor'): " + trie5.startsWith("wor") + " (Expected: true)");

        // Test case 6: Long strings
        ImplementTrie trie6 = new ImplementTrie();
        trie6.insert("programming");
        trie6.insert("program");
        System.out.println("Test 6a - search('program'): " + trie6.search("program") + " (Expected: true)");
        System.out.println("Test 6b - search('programming'): " + trie6.search("programming") + " (Expected: true)");
        System.out.println("Test 6c - startsWith('program'): " + trie6.startsWith("program") + " (Expected: true)");
        System.out.println("Test 6d - startsWith('programm'): " + trie6.startsWith("programm") + " (Expected: true)");
        System.out.println("Test 6e - search('programm'): " + trie6.search("programm") + " (Expected: false)");

        // Test case 7: countWordsEqualTo - duplicate words
        ImplementTrie trie7 = new ImplementTrie();
        trie7.insert("apple");
        trie7.insert("apple");
        trie7.insert("apple");
        trie7.insert("app");
        System.out.println(
                "Test 7a - countWordsEqualTo('apple'): " + trie7.countWordsEqualTo("apple") + " (Expected: 3)");
        System.out.println("Test 7b - countWordsEqualTo('app'): " + trie7.countWordsEqualTo("app") + " (Expected: 1)");
        System.out
                .println("Test 7c - countWordsEqualTo('appl'): " + trie7.countWordsEqualTo("appl") + " (Expected: 0)");
        System.out.println("Test 7d - countWordsEqualTo('xyz'): " + trie7.countWordsEqualTo("xyz") + " (Expected: 0)");

        // Test case 8: countWordsStartingWith - prefix counting
        ImplementTrie trie8 = new ImplementTrie();
        trie8.insert("cat");
        trie8.insert("car");
        trie8.insert("card");
        trie8.insert("care");
        trie8.insert("dog");
        System.out.println(
                "Test 8a - countWordsStartingWith('ca'): " + trie8.countWordsStartingWith("ca") + " (Expected: 4)");
        System.out.println(
                "Test 8b - countWordsStartingWith('car'): " + trie8.countWordsStartingWith("car") + " (Expected: 3)");
        System.out.println(
                "Test 8c - countWordsStartingWith('card'): " + trie8.countWordsStartingWith("card") + " (Expected: 1)");
        System.out.println(
                "Test 8d - countWordsStartingWith('do'): " + trie8.countWordsStartingWith("do") + " (Expected: 1)");
        System.out.println(
                "Test 8e - countWordsStartingWith('xyz'): " + trie8.countWordsStartingWith("xyz") + " (Expected: 0)");

        // Test case 9: countWordsEqualTo and countWordsStartingWith - mixed usage
        ImplementTrie trie9 = new ImplementTrie();
        trie9.insert("hello");
        trie9.insert("hello");
        trie9.insert("help");
        trie9.insert("helmet");
        System.out.println(
                "Test 9a - countWordsEqualTo('hello'): " + trie9.countWordsEqualTo("hello") + " (Expected: 2)");
        System.out.println(
                "Test 9b - countWordsStartingWith('hel'): " + trie9.countWordsStartingWith("hel") + " (Expected: 3)");
        System.out.println("Test 9c - countWordsEqualTo('he'): " + trie9.countWordsEqualTo("he") + " (Expected: 0)");
        System.out.println(
                "Test 9d - countWordsStartingWith('hell'): " + trie9.countWordsStartingWith("hell") + " (Expected: 2)");
    }
}
