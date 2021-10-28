/**
 * 斐波那契数列的第N个值
 *
 * @author formalhaut
 */
public class Fib {
    public static void main(String[] args) {
        System.out.println(calculate(10));
        System.out.println(calculate2(10));
        System.out.println(iterate(10));

    }

    /**
     * 双指针迭代
     * 基于去重递归优化，集合没有必要保存每一个下标值，只需保存前两位即可，向后遍历，得出N的值
     *
     * @param num 斐波那契数列的第n个元素的下标，即n
     * @return 返回 斐波那契数列的第num个元素值
     */
    private static int iterate(int num) {

        //斐波那契数列的第一个元素，返回0
        if (num == 0) {
            return 0;
        }
        //斐波那契数列的第二个元素，返回1
        if (num == 1) {
            return 1;
        }
        //双指针low和high（前后指针）,low初始值为第一个元素0，high初始值为第二个元素1
        int low = 0, high = 1;
        //共循环num-2次，循环结束high指针刚好指向第num个元素
        for (int i = 2; i <= num; i++) {
            //sum 保存high后面一个元素
            int sum = low + high;
            //low指针右移一位，保存high的值，原本的low值丢弃
            low = high;
            //high指针右移一位，保存sum的值，进入下一轮循环
            high = sum;
        }
        //循环结束时，high保存的就是第num个元素值
        return high;
    }

    /**
     * 暴力递归
     *
     * @param num 斐波那契数列的第n个元素的下标，即n
     * @return 返回第num个元素
     */
    private static int calculate(int num) {
        //斐波那契数列的第一个元素，返回0
        if (num == 0) {
            return 0;
        }
        //斐波那契数列的第二个元素，返回1
        if (num == 1) {
            return 1;
        }
        //返回第num个元素,即calculate(num - 1) + calculate(num - 2)
        return calculate(num - 1) + calculate(num - 2);
    }

    /**
     * 去重递归
     *
     * @param num 斐波那契数列的第n个元素的下标，即n
     * @return 返回数列第num个元素
     */
    private static int calculate2(int num) {
        //使用int数组来存储斐波那契数列，长度为num+1，因为斐波那契数列从0开始，数组中每个元素初始值都为0
        int[] arr = new int[num + 1];
        return recurse(arr, num);
    }

    /**
     * 将num对应的值保存在数组里面
     *
     * @param arr 用于保存数列
     * @param num 数列个数
     * @return 返回数组第num+1个元素
     */
    private static int recurse(int[] arr, int num) {
        //斐波那契数列的第一个元素，返回0
        if (num == 0) {
            return 0;
        }
        //斐波那契数列的第二个元素，返回1
        if (num == 1) {
            return 1;
        }

        //去重代码
        //如果数组元素不为0（默认值为0），说明被修改过，直接返回该元素
        if (arr[num] != 0) {
            return arr[num];
        }
        //recurse(arr, num - 1) = recurse(arr, num - 2)+recurse(arr, num - 3)
        // 左边的recurse(arr, num - 1)已经计算过recurse(arr, num - 2)了，
        //右边的不用再计算了
        //递归调用，并将数列中第num个元素的值保存到数组对应的位置：arr[num]
        arr[num] = recurse(arr, num - 1) + recurse(arr, num - 2);
        //返回数组的第num+1个元素（下标从0开始），即数列的第num个元素
        return arr[num];
    }
}
