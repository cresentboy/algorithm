import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 * 给定一个升序排列的整数数组 numbers ，从数组中找出两个数满足相加之和等于目标数 target 。
 * 假设每个输入只对应唯一的答案，而且不可以重复使用相同的元素。
 * 返回两数的下标值，以数组形式返回
 *
 * @author formalhaut
 */
public class TwoNumbersSum {

    public static void main(String[] args) {
        System.out.println("bf1" + Arrays.toString(twoSumBF1(new int[]{1, 2, 3, 4, 5, 6}, 10)));
        System.out.println("bf2" + Arrays.toString(twoSumBF1(new int[]{1, 2, 3, 4, 5, 6}, 10)));
        System.out.println("twoSearch" + Arrays.toString(twoSearch(new int[]{1, 2, 3, 4, 5, 6}, 10)));
        System.out.println("twoPoint" + Arrays.toString(twoPoint(new int[]{1, 2, 3, 4, 5, 6}, 10)));
    }


    /**
     * 暴力双循环解法 不考虑升序排列
     * 时间复杂度 O(n²)
     * 空间复杂度O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSumBF1(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                //暴力循环，把数组中所有的2对整数都加一遍
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }


    /**
     * 暴力单循环解法  不考虑升序排列
     * 时间复杂度 O(n)
     * 空间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSumBF2(int[] nums, int target) {
        //key为数组元素，value为元素对应的下标
        HashMap<Integer, Integer> map = new HashMap<>();
        //遍历数组
        for (int i = 0; i < nums.length; ++i) {
            //如果map中包含一个元素与元素nums[i]相加等于target，返回这两个元素的下标所组成的数组
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            //如果不包含，则把该元素及其下标放到map中
            map.put(nums[i], i);
        }
        //如果遍历完数组没有找到这两个元素，返回默认数组
        return new int[0];
    }

    /**
     * 二分循环查找
     * 先固定一个值(从下标0开始)，再用二分查找查另外一个值，找不到则固定值向右移动，继续二分查找
     * 时间复杂度
     * O(nlog(n))   for循环为n,二分查找为log(n)
     * 空间复杂度
     * O(1)
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSearch(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {
            //假设左指针low就是其中一个符合条件的元素的索引
            int low = i;
            //使用二分法查找数组中是否有符合条件的另一个元素numbers[mid],high用来二分查找mid
            int high = numbers.length - 1;
            //一直循环到左右指针相等
            while (low <= high) {
                //二分查找low和high之间是否有符合条件的mid
                int mid = (high - low) / 2 + low;
                //如果numbers[i] + numbers[mid] == target,返回两个元素的下标
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i, mid};
                    //如果numbers[i] + numbers[mid] > target,二分查找mid左半区
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                    //如果numbers[i] + numbers[mid] < target,二分查找mid右半区
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }


    /**
     * 双指针循环查找
     * 左指针指向数组head，右指针指向数组tail，head+tail > target 则tail 左移，否则head右移
     * 时间复杂度 O(N)
     * 空间复杂度O(1)
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoPoint(int[] numbers, int target) {

        //定义左右指针，左指针右移，右指针左移
        int low = 0, high = numbers.length - 1;
        //一直循环到左右指针重合
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low, high};
            } else if (sum < target) {
                low++;
            } else {
                high--;
            }
        }
        return new int[]{-1, -1};
    }

}
