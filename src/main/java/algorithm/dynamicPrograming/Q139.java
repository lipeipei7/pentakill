package algorithm.dynamicPrograming;

import java.util.*;

/*
139. Word Break
Given a string s and a dictionary of strings wordDict,
return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
*/
public class Q139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> dp = new HashMap<>();
        Set<String> dict = new HashSet<>(wordDict);

        return wordBreakDP(dict, dp, s);
    }

    private boolean wordBreakDP(Set<String> dict, Map<String, Boolean> dp, String s) {
        if (s.isEmpty()) {
            return true;
        }
        if (dp.containsKey(s)) {
            return dp.get(s);
        }

        for (int i = 0; i < s.length(); i++) {
            String curr = s.substring(0, i + 1);
            if (dict.contains(curr)) {
                if (wordBreakDP(dict, dp, s.substring(i + 1))) {
                    dp.put(s, true); //after return from bottom, mark entire string
                    return true;
                }
            }
        }

        dp.put(s, false); //mark entire string
        return false;
    }
}
