/**
 * 链表反转
 *
 * @author formalhaut
 */
public class ReverseList {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode iterate = iterate(node1);
        ListNode recursion = recursion(node1);
        System.out.println(iterate);
        System.out.println(recursion);


    }

    /**
     * 迭代
     *
     * @param head
     * @return
     */
    public static ListNode iterate(ListNode head) {

        ListNode newHead = null;
        while (head != null) {
            //赋值
            ListNode tmp = head.next;
            //指向
            head.next = newHead;
            //赋值
            newHead = head;
            //指向
            head = tmp;
        }
        return newHead;
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public static ListNode recursion(ListNode head) {
        //如果链表为空，则不需要反转，或者已经找到最后一个节点，直接返回该节点
        if (head == null | head.next == null) {
            return head;
        }
        //迭代，直到head.next == null，此时head为链表的最后一个节点
        //recursion(head.next)返回的是head.next，即newHead = head.next(newHead指向head.next)，此时newHead == null
        ListNode newHead = recursion(head.next);
        //当前节点（head）的下一个节点（head.next指向下一个节点）的next指向当前节点（head），指向反转
        //由于newHead = head.next, 得出newHead.next = head
        head.next.next = head;
        //当前节点的next指向null
        head.next = null;
        //返回新链表的头节点newHead
        return newHead;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
