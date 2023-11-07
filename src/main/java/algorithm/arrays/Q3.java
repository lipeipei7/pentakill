package algorithm.arrays;

import java.util.HashMap;
import java.util.Map;

/*
3. Longest Substring Without Repeating Characters

Given a string s, find the length of the longest substring without repeating characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
public class Q3 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> index = new HashMap<>();
        int max = 0;

        for (int i = 0, j = 0; j < s.length(); j++) {
            if (index.containsKey(s.charAt(j))) {
                int lastSeen = index.get(s.charAt(j));
                while (i <= lastSeen) {
                    index.remove(s.charAt(i++));
                }

            }

            max = Math.max(max, j - i + 1);
            index.put(s.charAt(j), j);
        }

        return max;
    }

    public static void main(String[] args) {
        String s = "dvdf";
        Q3 q3 = new Q3();
        int res = q3.lengthOfLongestSubstring(s);
        System.out.println(res);
    }
}
