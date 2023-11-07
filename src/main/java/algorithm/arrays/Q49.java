package algorithm.arrays;

import java.util.*;

/*
    Q49 Group Anagrams
    Given an array of strings strs, group the anagrams together.
    You can return the answer in any order.

    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
    typically using all the original letters exactly once.
* */
public class Q49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }

        Map<String, List<String>> anagramsMap = new HashMap<>();
        for (String str: strs) {
            char[] sortedStr = str.toCharArray();
            Arrays.sort(sortedStr);
            List<String> anagramsList = anagramsMap.getOrDefault(String.valueOf(sortedStr), new ArrayList<>());
            anagramsList.add(str);
            anagramsMap.put(String.valueOf(sortedStr), anagramsList);
        }

        res.addAll(anagramsMap.values());
        return res;
    }

    public static void main(String[] args) {
        Q49 solution = new Q49();
        solution.groupAnagrams(new String[]{"bat", "abt"});

    }
}
