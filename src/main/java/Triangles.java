import java.util.Arrays;

/**
 * 三角形的最大周长
 * 给定由一些正数（代表长度）组成的数组 A ，返回由其中三个长度组成的、面积不为零的三角形的最大
 * 周长。
 * 如果不能形成任何面积不为零的三角形，返回 0 。
 * 贪心：
 * 先小到大排序，假设最长边是最后下标，另外两条边是倒数第二和第三下标，则此时三角形周长最大
 * n < (n-1) + (n-2)，如果不成立，意味着该数组中不可能有另外两个值之和大于n，此时将n左移，重新计
 * 算
 *
 * @author formalhaut
 */
public class Triangles {
    public static void main(String[] args) {
        System.out.println(LargestPerimeter(new int[]{3, 6, 2, 3}));
    }

    private static int LargestPerimeter(int[] A) {

        //排序，默认升序
        Arrays.sort(A);
        //从后往前遍历
        for (int i = A.length - 1; i >= 2; --i) {
            //判断三条边是否符合组成三角形的条件
            if (A[i - 2] + A[i - 1] > A[i]) {
                //如果是，返回周长
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        //如果没有，返回0
        return 0;
    }
}
