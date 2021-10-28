/**
 * 排列硬币
 * 总共有 n 枚硬币，将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 * n 是一个非负整数，并且在32位有符号整型的范围内
 *
 * @author formalhaut
 */
public class ArrangeCoin {
    public static void main(String[] args) {
        System.out.println(arrangeCoins(10));
        System.out.println(arrangeCoins2(10));
    }

    /**
     * 迭代
     * 从第一行开始排列，排完一列、计算剩余硬币数，排第二列，直至剩余硬币数小于或等于行数
     *
     * @param n 总硬币数
     * @return 总行数
     */
    private static int arrangeCoins(int n) {
        //从1开始向n循环
        for (int i = 1; i <= n; i++) {
            //每循环一次，n枚硬币减一次i
            n = n - i;
            if (n <= i) {
                //当剩余硬币不足以摆出一行时结束，返回总行数
                return i;
            }
        }
        return 0;
    }

    /**
     * 二分查找
     * 假设能排 n 行，计算 n 行需要多少硬币数，如果大于 n，则排 n/2行，再计算硬币数和 n 的大小关系
     *
     * @param n 总硬币数
     * @return 总行数
     */
    private static int arrangeCoins2(int n) {
        //定义2个指针，low行和high行
        int low = 0, high = n;
        //
        while (low <= high) {
            //mid为low和high的中间行，即low <= mid <= high
            int mid = low + (high - low) / 2;
            //sum为mid行的总硬币数：   1+2+3+....+x = (x²+x)/2
            int midSum = (mid + 1) * mid / 2;
            if (midSum == n) {
                //如果mid行的总硬币数正好和总硬币数相等，说明mid就是要找的总行数
                return mid;
            } else if (midSum > n) {
                //如果mid行总硬币数大于给定的总硬币数n，说明mid比实际总行数大，把high指针移到mid左边继续二分
                high = mid - 1;
            } else {
                //如果mid行总硬币数小于给定的总硬币数n，说明mid比实际总行数小，把low指针移到mid右边继续二分
                low = mid + 1;
            }

        }
        return high;
    }

    /**
     * 牛顿迭代
     * 使用牛顿迭代求平方根，(x + n/x)/2
     * 假设能排 x 行 则 1 + 2 + 3 + ...+ x = n，即 x(x+1)/2 = n 推导出 x = 2n - x
     *
     * @param x 假设能排x行
     * @param n 总硬币数
     * @return x 总行数
     */
    public static double sqrts(double x, int n) {

        return x;
    }

}
