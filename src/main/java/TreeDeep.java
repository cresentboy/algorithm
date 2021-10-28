import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * @author formalhaut
 */
public class TreeDeep {

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, node7, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
        System.out.println(minDepth(node1));
        System.out.println(minDepth1(node1));
    }

    /**
     * 解法一：深度优先
     * 遍历整颗数，找到每一个叶子节点，从叶子节点往上开始计算，左右子节点都为空则记录深度为1
     * 左右子节点只有一边，深度记录为子节点深度+1
     * 左右两边都有子节点，则记录左右子节点的深度较小值+1
     * 时间复杂度：O(N)
     * 空间复杂度：O(logN) 取决于树的高度
     *
     * @param root 遍历到的每一个当前变量
     * @return
     */
    public static int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        //最小值默认为最大整数
        int min = Integer.MAX_VALUE;
        //如果当前节点还有左子节点，则递归遍历左子树，并和min比较
        if (root.left != null) {
            min = Math.min(minDepth(root.left), min);
        }
        //如果当前节点还有右子节点，则递归右子树，并和min比较
        if (root.right != null) {
            min = Math.min(minDepth(root.right), min);
        }
        //返回root节点最小子树的深度加上root节点本身
        return min + 1;
    }

    /**
     * 解法二：广度优先
     * 从上往下，找到一个节点时，标记这个节点的深度。查看该节点是否为叶子节点，如果是直接返回深度
     * 如果不是叶子节点，将其子节点标记深度(在父节点深度的基础上加1)，再判断该节点是否为叶子节点
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param root
     * @return
     */
    public static int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        root.depth = 1;
        //root节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            //出队一个元素
            TreeNode node = queue.poll();
            //如果当前节点的左右子节点都为空，表示是叶子节点
            if (node.left == null && node.right == null) {
                return node.depth;
            }
            //如果当前节点有左子节点，标记左子节点的深度并入队
            if (node.left != null) {
                node.left.depth = node.depth + 1;
                queue.offer(node.left);
            }
            // //如果当前节点有右子节点，标记右子节点的深度并入队
            if (node.right != null) {
                node.right.depth = node.depth + 1;
                queue.offer(node.right);
            }
        }
        return 0;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        //广度优先
        int depth;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
