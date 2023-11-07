package algorithm.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
* Add two lists
*
* example
* 999 + 99 = 1098
* */
public class Q2 {
    ListNode addTwoLists(ListNode l1, ListNode l2) {
        int total = l1.val + l2.val;
        int carry = total / 10;
        ListNode res = new ListNode(total % 10);
        if (l1.next != null ||
                l2.next != null ||
                carry != 0) {
            l1 = Objects.requireNonNullElseGet(l1.next, () -> new ListNode(0));
            l2 = Objects.requireNonNullElseGet(l2.next, () -> new ListNode(0));

            l1.val += carry;
            res.next = addTwoLists(l1, l2);
        }
        return res;
    }

    private static class ListNode {
        int val;
        ListNode next;
        public ListNode (int val) {
            this.val = val;
        }
    }

    private ListNode revertListHelper(List<ListNode> root, ListNode l1) {
        if (l1.next == null) {
            root.add(l1);
            return l1;
        }
        revertListHelper(root, l1.next).next = l1;
        return l1;
    }

    private void setUp(ListNode l1, ListNode l2) {
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);

        l2.next = new ListNode(9);
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9), l2 = new ListNode(9);
        Q2 solution = new Q2();
        solution.setUp(l1, l2);
        ListNode res = solution.addTwoLists(l1, l2);
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }

        solution.setUp(l1, l2);
        System.out.println();
        List<ListNode> rootArr = new ArrayList<>();
        solution.revertListHelper(rootArr, solution.addTwoLists(l1, l2)).next = null;
        ListNode root = rootArr.get(0);
        while (root != null) {
            System.out.print(root.val);
            root = root.next;
        }
    }
}
