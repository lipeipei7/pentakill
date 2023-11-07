package dataStructure.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
    20. Valid Parentheses

    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
    determine if the input string is valid.
    An input string is valid if:
    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Every close bracket has a corresponding open bracket of the same type.

    Example 1:
    Input: s = "()"
    Output: true

    Example 2:
    Input: s = "()[]{}"
    Output: true

    Example 3:
    Input: s = "(]"
    Output: false
* */
public class Q20 {

    public boolean validParentheses(String input) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char curr: input.toCharArray()) {
            if (curr == '(' || curr == '[' || curr == '{') {
                stack.offerFirst(curr);
            } else {
                Character prev = stack.pollFirst();
                if (prev == null || prev != getOpposite(curr)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private char getOpposite(Character parentheses) {
        Map<Character, Character> mapping = new HashMap<>() {{
            put('{', '}');
            put('[', ']');
            put('(', ')');
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        return mapping.get(parentheses);
    }

    public static void main(String[] args) {
        Q20 solution = new Q20();
        System.out.println(solution.validParentheses(""));
        System.out.println(solution.validParentheses("{}"));
        System.out.println(solution.validParentheses("{()}"));
        System.out.println(solution.validParentheses("{(})"));
        System.out.println(solution.validParentheses("(()[]"));
    }
}
