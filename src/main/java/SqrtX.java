/**
 * x的平方根
 * 在不使用 sqrt(x) 函数的情况下，得到 正整数x的平方根的整数部分
 *
 * @author formalhaut
 */
public class SqrtX {
    public static void main(String[] args) {
        System.out.println(binarySearch(24));
        System.out.println(newton(24));
    }

    //二分循环查找
    private static int binarySearch(int x) {

        //定义左右指针
        int index = -1, left = 0, right = x;
        //一直循环到左右指针相等
        while (left <= right) {
            //假设mid就是要找的数
            int mid = left + (right - left) / 2;
            //如果mid小于等于x的平方根，说明x的平方根在mid右边，循环在mid由半区二分
            if (mid * mid <= x) {
                //先保存mid，如果x的平方根不是整数，当前的index就是要找的整数部分
                index = mid;
                left = mid + 1;
                //如果mid大于x的平方根，说明x的平方根在mid左边，循环在mid左半区二分
            } else {
                right = mid - 1;
            }
        }
        return index;
    }

    /**
     * 牛顿迭代
     * x/n 和n的均值比x和n更趋近于根号x
     *
     * @param x
     * @return
     */
    public static int newton(int x) {
        if (x == 0) {
            return 0;
        }
        return (int) sqrts(x, x);
    }

    public static double sqrts(double i, int x) {
        double result = (i + x / i) / 2;
        if (result == i) {
            return i;
        } else {
            return sqrts(result, x);
        }
    }
}
