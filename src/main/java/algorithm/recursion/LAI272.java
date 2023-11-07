package algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    272. Combinations For Telephone Pad I
    Given a telephone keypad, and an int number,
    print all words which are possible by pressing these numbers,
    the output strings should be sorted.

    {0 : "",
    1 : "",     2 : "abc",      3 : "def",
    4 : "ghi",  5 : "jkl",      6 : "mno",
    7 : "pqrs", 8 : "tuv",      9 : "wxyz"}

    Assumptions:
    The given number >= 0

    Examples:
    if input number is 231, possible words which can be formed are:
    [ad, ae, af,
     bd, be, bf,
     cd, ce, cf]
*/
public class LAI272 {
    static final String[] KEYPAD = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public String[] combinations(int number) {
        List<String> res = new ArrayList<>();
        if (String.valueOf(number).isEmpty()) {
            return res.toArray(new String[0]);
        }
        dfs(0, String.valueOf(number).toCharArray(), new StringBuilder(), res);
        return res.toArray(new String[0]);
    }

    private void dfs(int index, char[] numbers, StringBuilder sb, List<String> res) {
        if (index == numbers.length) {
            res.add(sb.toString());
            return;
        }

        if (numbers[index] == '0' || numbers[index] == '1') {
            dfs(index + 1, numbers, sb, res);
            return;
        }

        String letters = KEYPAD[numbers[index] - '0'];
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            dfs(index + 1, numbers, sb, res);
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LAI272 solution = new LAI272();
        System.out.println(Arrays.toString(solution.combinations(231)));
    }
}
