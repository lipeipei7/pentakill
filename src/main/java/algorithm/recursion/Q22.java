package algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/*
* Generate Parentheses
*/
public class Q22 {
    List<String> pgen(int n) {
        List<String> res = new ArrayList<>();
        pgenRecursion(n, res, 0, 0, new StringBuilder());
        return res;
    }

    private void pgenRecursion(int n, List<String> res, int left, int right, StringBuilder sb) {
        if (right > left) {
            return;
        }
        if (left == right && left == n) {
            res.add(sb.toString());
            return;
        }
        if (left < n) {
            pgenRecursion(n, res, left + 1, right, sb.append("("));
            sb.setLength(sb.length() - 1);
        }
        if (right < left) {
            pgenRecursion(n, res, left, right + 1, sb.append(")"));
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Q22 solution = new Q22();
        System.out.println(solution.pgen(2));
        System.out.println(solution.pgen(3));
    }

}
