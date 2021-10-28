import java.util.HashSet;

/**
 * 环形链表
 * 给定一个链表，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达该节点，则链表中存在环
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * @author formalhaut
 */
public class LinkCycle {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        node5.next = node3;
        System.out.println(hasCycle(node1));
        System.out.println(hasCycle2(node1));
    }

    /**
     * 双指针循环
     * 快慢指针
     * O(n)
     * O(1)
     *
     * @param head
     * @return
     */
    private static boolean hasCycle2(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        //快慢指针相等时结束循环
        while (slow != fast) {
            //快指针指向null或者指向最后一个节点时结束循环
            if (fast == null || fast.next == null) {
                return false;
            }
            //慢指针走一步
            slow = slow.next;
            //快指针走两步
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 遍历链表，存储在set中，
     * O(n)
     * O(n)
     *
     * @param head 链表的头结点
     * @return true or false
     */
    private static boolean hasCycle(ListNode head) {
        //把链表放到set中去重
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            //如果添加失败，说明是重复元素，是环形链表
            if (!set.add(head)) {
                //遇到重复元素，返回true，是循环链表
                return true;
            }
            //如果没有添加失败，接着添加下一位
            head = head.next;
        }
        //遇到null跳出循环，返回false，不是循环链表
        return false;
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
