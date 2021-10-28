import java.util.Arrays;

/**
 * 数组中心下标
 * 中心下标两边总和相等
 *
 * @author formalhaut
 * 数组中某一个下标，左右两边的元素之和相等，该下标即为中心索引
 * 思路：先统计出整个数组的总和，然后从第一个元素开始叠加
 * 总和递减当前元素，叠加递增当前元素，直到两个值相等
 */
public class ArrayCenterIndex {
    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[]{1, 7, 3, 6, 5, 4, 6, 5, 2}));
    }

    private static int pivotIndex(int[] nums) {

        //sum1为右边未遍历元素总和，初始值为数组所有元素的和
        int sum1 = Arrays.stream(nums).sum();
        //sum2为左边已遍历元素总和，初始值为0
        int sum2 = 0;
        //遍历数组
        for (int i = 0; i < nums.length; i++) {
            //每遍历一个元素，都加到sum2上
            sum2 += nums[i];
            //当sum1 == sum2时，sum1已加上中心元素，sum2还未减去中心元素，所以才能相等
            if (sum1 == sum2) {
                //如果左边和sum2等于右边和sum1，当前元素就是数组中心元素，返回当前元素索引（下标）
                return i;
            }
            //如果左右两边和不等，右边和sum1减去当前元素，重新遍历下一个元素
            sum1 -= nums[i];
        }
        return -1;
    }

}
