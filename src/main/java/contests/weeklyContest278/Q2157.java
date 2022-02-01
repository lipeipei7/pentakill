package contests.weeklyContest278;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
* https://leetcode.com/contest/weekly-contest-278/problems/groups-of-strings/
* You are given a 0-indexed array of strings words. Each string consists of lowercase English letters only.
* No letter occurs more than once in any string of words.
* Two strings s1 and s2 are said to be connected if the set of letters of s2 can be obtained from
* the set of letters of s1 by any one of the following operations:
*  - Adding exactly one letter to the set of the letters of s1.
*  - Deleting exactly one letter from the set of the letters of s1.
*  - Replacing exactly one letter from the set of the letters of s1 with any letter, including itself.
* The array words can be divided into one or more non-intersecting groups.
* A string belongs to a group if any one of the following is true:
*  - It is connected to at least one other string of the group.
*  - It is the only string present in the group.
* Note that the strings in words should be grouped in such a manner that a string belonging to a group cannot
* be connected to a string present in any other group. It can be proved that such an arrangement is always unique.
*
* Return an array ans of size 2 where:
* ans[0] is the total number of groups words can be divided into, and
* ans[1] is the size of the largest group.
*/
public class Q2157 {
    private final Map<Integer, Integer> idMap = new HashMap<>();
    //need to consider duplicate words
    private final Map<Integer, Integer> countMap = new HashMap<>();
    private final Map<Integer, Integer> deleteMap = new HashMap<>();

    public int[] groupStrings(String[] words) {
        //if we sort the words by length, then the add rule is same with delete rule because the longer word can be
        // matched with a shorter word by deleting a char, which is same with adding a char to the shorter word
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (int i = 0; i < words.length; i++) {
            int bitVal = bitmask(words[i]);
            countMap.put(bitVal, countMap.getOrDefault(bitVal, 0) + 1);
            for (int j = 0; j < words[i].length(); j++) {
                int offset = (words[i].charAt(j) - 'a');
                if (((bitVal >> offset) & 1) == 1) {
                    //delete bit j
                    int val = bitVal - (1 << offset);
                    merge(bitVal, val);

                    //replace
                    //if two words can get the same word after deleting one char, then they can be replaced
                    if (deleteMap.containsKey(val)) {
                        int id = deleteMap.get(val);
                        union(bitVal, id);
                    }
                    deleteMap.put(val, find(bitVal, true));
                }
            }
        }
        final Map<Integer, Integer> groupCntMap = new HashMap<>();
        final int[] max = new int[1];
        idMap.forEach((k, v) -> {
            //need to update group id for each bitmask
            int val = find(k, true);
            groupCntMap.put(val, groupCntMap.getOrDefault(val, 0) + countMap.getOrDefault(k, 0));
            max[0] = Math.max(max[0], groupCntMap.get(val));
        });
        return new int[]{groupCntMap.size(), max[0]};
    }

    private int bitmask(String word) {
        int x = 0;
        for (char c : word.toCharArray()) {
            x += 1 << (c - 'a');
        }
        return x;
    }

    private int find(int x, boolean set) {
        if (idMap.getOrDefault(x, x) == x) {
            if (set) {
                idMap.put(x, x);
            }
            return x;
        }
        int px = find(idMap.get(x), set);
        if (set) {
            idMap.put(x, px);
        }
        return px;
    }

    private void merge(int source, int target) {
        int ps = find(source, true);
        int pt = find(target, false);
        if (idMap.containsKey(target)) {
            union(source, target);
        }
    }

    private void union(int x, int y) {
        int px = find(x, true);
        int py = find(y, true);
        idMap.put(px, py);
    }

    public static void main(String[] args) {
        //Input: words = ["a","b","ab","cde"]
        //Output: [2,3]

        //Input: words = ["a","ab","abc"]
        //Output: [1,3]
    }
}
