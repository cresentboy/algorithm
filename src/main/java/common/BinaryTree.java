package common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 从根节点往下查找，先找左子树、直至左子树为空(左子节点逐个入栈、直至左子节点为空)，再找右子
 * 树(出栈找右子节点)
 * 前序遍历：根左右，第一次经过节点即打印，直到打印null，往回溯，打印右子树
 * 中序遍历：左根右，第二次经过该节点时进行打印，即左边回溯时
 * 后序遍历：左右根，第三次经过该节点时进行打印，即右边回溯时
 * 层序遍历：按照层级，从上往下，从左到右。使用广度优先搜索算法。
 *
 * @author formalhaut
 */
public class BinaryTree {
    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, node6, node7);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
        //recursionOrder(node1);

        /*ArrayList<Integer> list = new ArrayList<>();
        levelOrder(node1, 1, list);
        System.out.println(Arrays.toString(list.toArray()));*/

//        iterateOrderPre(node1);

        //iterateOrderMid(node1);

//        iterateOrderPost(node1);

//        levelTraverse(node1);

        //morrispre(node1);

        //       morrismid(node1);

        morrispos(node1);

    }

    /**
     * 递归前中后序
     *
     * @param root
     */
    private static void recursionOrder(TreeNode root) {

        if (root == null) {
            return;
        }
        //前序 第一次成为栈顶就打印 1245673
//        System.out.println(root.val);
        recursionOrder(root.left);
        //中序 第二次成为栈顶元素时打印  4265713
//        System.out.println(root.val);
        recursionOrder(root.right);
        //后序 第三次成为栈顶元素才打印 4675231
        System.out.println(root.val);
    }

    /**
     * 递归层序
     *
     * @param root 遍历到的节点
     * @param i    节点下标
     * @param list 将遍历到的节点存入list
     */
    public static void levelOrder(TreeNode root, int i, ArrayList list) {
        if (root == null) {
            return;
        }
        int length = list.size();
        //用null扩充list
        if (length <= i) {
            for (int j = 0; j <= i - length; j++) {
                list.add(length + j, null);
            }
        }
        //修改下标为i的节点的值
        list.set(i, root.val);
        levelOrder(root.left, 2 * i, list);
        levelOrder(root.right, 2 * i + 1, list);

    }

    /**
     * 前序：使用stack记录递归路径，左子节点后添加保证先出栈
     * 1245673
     *
     * @param head 根节点/遍历到的每一个节点
     */
    public static void iterateOrderPre(TreeNode head) {
        if (head != null) {
            //LinkedList<TreeNode> stack = new LinkedList<>();

            //FILO先进后出
            Stack<TreeNode> stack = new Stack<>();
            //和push差不多，都是添加一个元素
            stack.add(head);
            while (!stack.isEmpty()) {
                //弹出一个元素
                head = stack.pop();
                if (head != null) {
                    //按照出栈的顺序进行打印
                    System.out.println(head.val);
                    stack.push(head.right);
                    stack.push(head.left);
                }
            }
        }
    }

    /**
     * 中序：将左子节点入栈，出栈打印值，然后添加右子节点
     * 4265713
     *
     * @param head 根节点/遍历到的每一个节点
     */
    public static void iterateOrderMid(TreeNode head) {
        if (head != null) {
            //LinkedList<TreeNode> stack = new LinkedList<>();

            //FILO先进后出
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.val);
                    head = head.right;
                }
            }
        }
    }

    /**
     * 后序遍历
     * 4675231
     *
     * @param head 根节点/遍历到的每一个节点
     */
    public static void iterateOrderPost(TreeNode head) {
        if (head == null) {
            return;
        }

        //LinkedList<TreeNode> stack = new LinkedList<>();

        //FILO先进后出
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (head != null || !stack.isEmpty()) {
            //一直遍历到左叶子节点
            while (head != null) {
                //将当前节点入栈
                stack.push(head);
                //将左子节点置为当前节点
                head = head.left;
            }

            //弹出当前节点
            head = stack.pop();
            //右子节点为null，或者右子节点已打印，则打印当前节点
            if (head.right == null || head.right == prev) {
                System.out.println(head.val);
                prev = head;
                //把父节点的左子节点置为null
                head = null;
                //如果有右子节点
            } else {
                //当前节点重新入栈
                stack.push(head);
                //并将右子节点置为当前节点
                head = head.right;
            }
        }
    }

    /**
     * 层序遍历
     *
     * @param head 根节点/遍历到的每一个节点
     */
    public static void levelTraverse(TreeNode head) {
        //先进先出 FIFO
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp != null) {
                System.out.println(temp.val);
                queue.add(temp.left);
                queue.add(temp.right);
            }
        }
    }

    /*
    线索二叉树：
            在N个节点的二叉树中，每个节点有2个指针，所以一共有2N个指针，除了根节点以外，每一个节点都
        有一个指针从它的父节点指向它，所以一共使用了N-1个指针，所以剩下2N-(N-1)也就是N+1个空指
        针；
            如果能利用这些空指针域来存放指向该节点的直接前驱或是直接后继的指针，则可由此信息直接找到在
        该遍历次序下的前驱节点或后继节点，从而比递归遍历提高了遍历速度，节省了建立系统递归栈所使用
        的存储空间；
            这些被重新利用起来的空指针就被称为线索（Thread），加上了线索的二叉树就是线索二叉树
        实现思路：按某种次序遍历二叉树，在遍历过程中用线索取代空指针即可。以中序遍历为例，首先找到
        中序遍历的开始节点，然后利用线索依次查找后继节点即可。
            由于它充分利用了空指针域的空间（等于节省了空间），又保证了创建时的一次遍历就可以终生受用前
        驱、后继的信息（这意味着节省了时间），所以在实际问题中，如果所使用的二叉树需要经常遍历或查
        找节点时需要某种遍历中的前驱和后继，那么采用线索二叉链表的存储结构就是不错的选择
        morris遍历：构建中序线索二叉树的过程中，如果发现前驱节点的右指针指向自身，则将指针（线索）
        删除
     */
    public static void morrispre(TreeNode current) {
        if (current == null) {
            return;
        }
        TreeNode mostRight = null;
        while (current != null) {
            //mostRight指向当前节点的左子节点
            mostRight = current.left;
            //如果当前节点有左子节点
            if (mostRight != null) {
                //如果mostRight有右子节点，一直循环到最右
                while (mostRight.right != null && mostRight.right != current) {
                    mostRight = mostRight.right;
                }
                //如果mostRight已经到最右，并且右指针指向null
                if (mostRight.right == null) {
                    //mostRight的右指针指向current节点，建立线索指针
                    mostRight.right = current;
                    //打印当前节点
                    System.out.println(current.val + "");
                    //current左移
                    current = current.left;
                    continue;
                    //如果mostRight右指针指向的是current节点
                } else {
                    //断开该线索指针
                    mostRight.right = null;
                }
            } else {
                //打印最左边的节点
                System.out.println(current.val + "");
            }
            //current节点右移
            current = current.right;
        }
    }

    /**
     * Morris中序
     *
     * @param current
     */
    public static void morrismid(TreeNode current) {
        if (current == null) {
            return;
        }
        TreeNode mostRight = null;
        while (current != null) {
            //mostRight指向当前节点的左子节点
            mostRight = current.left;
            //如果当前节点有左子节点
            if (mostRight != null) {
                //如果mostRight有右子节点，一直循环到最右
                while (mostRight.right != null && mostRight.right != current) {
                    mostRight = mostRight.right;
                }
                //如果mostRight已经到最右，并且右指针指向null
                if (mostRight.right == null) {
                    //mostRight的右指针指向current节点，建立线索指针
                    mostRight.right = current;
                    //打印当前节点
                    //System.out.println(current.val + "");
                    //current左移
                    current = current.left;
                    continue;
                    //如果mostRight右指针指向的是current节点
                } else {
                    //断开该线索指针
                    mostRight.right = null;
                }
            } else {
                //打印最左边的节点
                //System.out.println(current.val + "");
            }
            System.out.println(current.val + "");
            //current节点右移
            current = current.right;
        }
    }


    /**
     * morris后序遍历
     *
     * @param cur
     */
    public static void morrispos(TreeNode cur) {
        if (cur == null) {
            return;
        }
        //保存当前节点
        TreeNode root = cur;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    //断开线索指针的时候反序打印节点
                    printNode(cur.left);
                }
            }
            //打印当前节点
            cur = cur.right;
        }
        printNode(root);

    }

    private static void printNode(TreeNode head) {
        //反转链表
        TreeNode tail = reverse(head);
        while (tail != null) {
            System.out.println(tail.val);
            tail = tail.right;
        }
        //再次反转，保持二叉树结构
        reverse(tail);
    }

    //反转链表
    private static TreeNode reverse(TreeNode head) {
        TreeNode prev = null, cur, next;
        cur = head;
        while (cur != null) {
            next = cur.right;
            cur.right = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

}
