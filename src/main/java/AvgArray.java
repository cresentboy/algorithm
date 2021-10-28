/**
 * 给一个整数数组，找出平均数最大且长度为 k 的下标连续的子数组，并输出该最大平均数
 * 滑动窗口：
 * 窗口移动时，窗口内的和等于sum加上新加进来的值，减去出去的值
 *
 * @author formalhaut
 */
public class AvgArray {
    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }

    private static double findMaxAverage(int[] nums, int k) {
        //长度为k的子数组的和
        int sum = 0;
        //给定数组的长度
        int n = nums.length;
        //求子数组的和
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        //子数组和的最大值
        int maxSum = sum;
        //向后移动子数组
        for (int i = k; i < n; i++) {
            //减去移除的一位，加上移进的一位
            sum = sum - nums[i - k] + nums[i];
            //比较比较已有的最大值和当前子数组哪一个大
            maxSum = Math.max(maxSum, sum);
        }
        //返回最大值的平均值，并转化为double类型
        return 1.0 * maxSum / k;
    }

}
