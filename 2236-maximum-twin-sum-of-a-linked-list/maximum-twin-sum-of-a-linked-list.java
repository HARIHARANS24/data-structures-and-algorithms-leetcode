class Solution {
    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = null, curr = slow;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        int max = 0;
        ListNode first = head, second = prev;
        while (second != null) {
            max = Math.max(max, first.val + second.val);
            first = first.next;
            second = second.next;
        }
        return max;
    }
}