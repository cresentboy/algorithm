/**
 * 删除数组中的重复项，即数组中有多少不重复元素
 * 一个有序数组 nums ，原地删除重复出现的元素，使每个元素只出现一次 ，返回删除后数组的新长
 * 度。
 * 不要使用额外的数组空间，必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * @author formalhaut
 */
public class SortedArrayDuplicates {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{0, 1, 2, 2, 3, 3, 4, 4, 4, 5, 7, 10}));
    }

    /**
     * 双指针算法：
     * 数组完成排序后，我们可以放置两个指针 i 和 j，其中 i 是慢指针，而 j 是快指针。只要
     * nums[i]=nums[j]，我们就增加 j 以跳过重复项。
     * 当遇到 nums[j] ！= nums[i]时，跳过重复项的运行已经结束，必须把nums[j]）的值复制到 nums[i +
     * 1]。然后递增 i，接着将再次重复相同的过程，直到 j 到达数组的末尾为止。
     *
     * @param nums 给定的有序数组
     * @return i+1 不重复项的个数
     */
    private static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //慢指针，默认值为数组的第一个索引
        int i = 0;
        //快指针,数组的第二个索引到最后一个索引
        for (int j = 1; j < nums.length; j++) {
            //如果nums[j]和nums[i]不等，说明没有重复
            if (nums[j] != nums[i]) {
                //i先自增，nums[i]移到下一位，num[j]再覆盖nums[i]
                nums[++i] = nums[j];
            }
            //如果重复了，进入下一次循环，j++
        }
        //循环结束,nums[i]为最后一个不重复的元素，返回不重复的个数i+1
        return i + 1;
    }
}
