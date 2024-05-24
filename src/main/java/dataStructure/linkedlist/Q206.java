package dataStructure.linkedlist;
/*
    206. Reverse Linked List
    Given the head of a singly linked list, reverse the list, and return the reversed list.
*/
public class Q206 {
    private static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }


    public ListNode reverseList(ListNode head) {
        ListNode prev = null;

        for (ListNode curr = head, next; curr != null; curr = next) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
        }

        return prev;
    }

    private ListNode reverseListCopy(ListNode head) {
        ListNode prev = null; 

        for (ListNode curr = head, next; curr != null; curr = next) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
        }

        return prev;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null? l2 : l1;
        }

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public void reorderList(ListNode head) {

    }



}

