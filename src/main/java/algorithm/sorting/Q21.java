package algorithm.sorting;

/*
* Merge Sort
* */
public class Q21 {

    ListNode mergeSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        if (l1.val <= l2.val) {
            l1.next = mergeSortedLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeSortedLists(l1, l2.next);
            return l2;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Q21 solution = new Q21();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        l1.next = new ListNode(2);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode res = solution.mergeSortedLists(l1, l2);
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }
    }
}
