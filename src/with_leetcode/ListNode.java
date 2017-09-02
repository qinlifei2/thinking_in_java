package with_leetcode;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

/**
 * Created by qinlifei on 16-10-21.
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public ListNode() {

    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode p1 = start;
        ListNode p2 = start;
        int len = n;
        while (len > 0){
            p2 = p2.next;
            len--;
        }
        while(p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return start.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode start = new ListNode(0);
        ListNode p = new ListNode(0);
        start.next = p;
        while (l1 != null && l2 != null){
            if(l1.val < l2.val){
                p.next = l1;
                l1 = l1.next;
                p = p.next;
            }
            else {
                p.next = l2;
                l2 = l2.next;
                p = p.next;
            }
        }
        if (l1 != null){
            p.next = l1;
        }
        else {
            p.next = l2;
        }
        return start.next.next;
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l = new ListNode(0);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        System.out.print("ok");
    }
}
