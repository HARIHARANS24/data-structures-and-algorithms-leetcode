class Solution {
    public ListNode reverseList(ListNode head) {
        return helper(head, null);
    }
    private ListNode helper(ListNode curr, ListNode prev) {
        if (curr == null) return prev;
        ListNode next = curr.next;
        curr.next = prev;
        return helper(next, curr);
    }
}