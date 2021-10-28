import java.util.Arrays;

/**
 * 合并两个有序数组
 * 两个有序整数数组 nums1 和 nums2，将 nu
 * 初始化 nums1 和 nums2 的元素数量分别为
 * 有足够的空间保存来自 nums2 的元素。
 *
 * @author formalhaut
 */
public class MergeSortArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 5, 7, 9, 0, 0, 0, 0};
        int[] nums2 = new int[]{2, 4, 6, 8};

        //System.out.println(Arrays.toString(merge(nums1, 5, nums2, 4)));
        System.out.println(Arrays.toString(merge1(nums1, 5, nums2, 4)));
        //System.out.println(Arrays.toString(merge2(nums1, 5, nums2, 4)));
    }

    /**
     * 合并后排序
     * 时间复杂度 : O((n+m)log(n+m))。
     * 空间复杂度 : O(1)。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     * @return
     */
    private static int[] merge(int[] nums1, int m, int[] nums2, int n) {

        //nums2拷贝到nums1
        System.arraycopy(nums2, 0, nums1, m, n);
        //排序nums1
        Arrays.sort(nums1);
        return nums1;
    }

    /**
     * 双指针 从前往后遍历
     * 将两个数组按顺序进行比较，放入新的数组
     * 时间复杂度 : O(n + m)。
     * 空间复杂度 : O(m)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static int[] merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1_copy = new int[m];
        //将nums1拷贝到nums1_copy
        System.arraycopy(nums1, 0, nums1_copy, 0, m);
        //指向nums1_copy的指针
        int p1 = 0;
        //指向nums2的指针
        int p2 = 0;
        //指向nums1
        int p = 0;
        //比较nums1_copy 和 nums2, 将较小的元素放入nums1
        while (p1 < m && p2 < n) {
            nums1[p++] = nums1_copy[p1] < nums2[p2] ? nums1_copy[p1++] : nums2[p2++];
        }

        //如果nums1_copy还有剩余的元素，拷贝进nums1
        if (p1 < m) {
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        //如果nums2还有剩余的元素，拷贝进nums1
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
        return nums1;
    }

    /**
     * 双指针优化
     * 从后往前遍历，不引入新数组
     * 时间复杂度 : O(n + m)。
     * 空间复杂度 : O(1)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static int[] merge2(int[] nums1, int m, int[] nums2, int n) {

        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] < nums2[p2] ? nums2[p2--] : nums1[p1--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        return nums1;
    }
}
