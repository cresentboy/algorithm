import java.util.Arrays;

/**
 * 数组中三个数的最大乘积
 * 一个整型数组 nums ，在数组中找出由三个数字组成的最大乘积，并输出这个乘积。
 * 乘积不会越界
 * 如果数组中全是非负数，则排序后最大的三个数相乘即为最大乘积；如果全是非正数，则最大的三个数
 * 相乘同样也为最大乘积。
 * 如果数组中有正数有负数，则最大乘积既可能是三个最大正数的乘积，也可能是两个最小负数（即绝对
 * 值最大）与最大正数的乘积。
 * 分别求出三个最大正数的乘积，以及两个最小负数与最大正数的乘积，二者之间的最大值即为所求答
 * 案
 *
 * @author formalhaut
 */
public class MaxProduct {
    public static void main(String[] args) {
        System.out.println(sort(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(getMaxMIn(new int[]{1, 2, 3, 4, 5, 6}));

    }

    /**
     * 基于排序
     * 时间复杂度
     * O(nlog(n))
     * 空间复杂度
     * O(1)
     *
     * @param nums
     * @return
     */
    private static int sort(int[] nums) {

        //先给数组排序（排序算法），增加了时间复杂度
        Arrays.sort(nums);
        int length = nums.length;
        //最小的2个负数（绝对值最大）和最大正数的乘积  vs    3个最大正数的乘积
        return Math.max(nums[0] * nums[1] * nums[length - 1], nums[length - 1] * nums[length - 2] * nums[length - 3]);
    }

    /**
     * 基于线性扫描,求最值
     * 时间复杂度
     * O(n)
     * 空间复杂度
     *
     * @param nums
     * @return
     */
    public static int getMaxMIn(int[] nums) {

        //假设min1为最小值，min2为倒数第二小
        int min1 = 0, min2 = 0;
        //假设max1最大，max2其次，max3第三
        int max1 = 0, max2 = 0, max3 = 0;
        //遍历整型数组
        for (int x : nums) {
            //如果数组中有元素比min1还小，把该元素赋给min1，min1的值赋给min2
            if (x < min1) {
                min2 = min1;
                min1 = x;
                //如果有x<min1则不会进入else if，如果没有，则表明min1就是最小值，然后看数组中是否有元素小于min2
            } else if (x < min2) {
                min2 = x;
            }

            //如果数组中有元素大于max1，把该元素赋给max1，max1原来的值赋给max2，max2原来的值赋给max3
            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
                //如果数组中没有元素大于max1，但是有元素大于max2，把该元素赋给max2，max2原来的值赋给max3
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
                //如果数组中没有元素大于max1和max2，但有元素大于max3，把该元素赋给max3
            } else if (x > max3) {
                max3 = x;
            }

        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}
