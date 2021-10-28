/**
 * 统计N以内的素数
 *
 * @author formalhaut
 */
public class Prime {
    public static void main(String[] args) {
        int count = countPrimes(100);
        int count1 = eratosthenes(100);
        System.out.println(count);
        System.out.println(count1);
    }

    /**
     * 暴力算法bf
     * 双重for循环，时间复杂度O(n²)
     *
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            //isPrime返回true，count+1
            count += isPrime(i) ? 1 : 0;
        }
        return count;
    }

    /**
     * @param x 统计x以内的素数个数
     * @return true代表x是素数
     */
    private static boolean isPrime(int x) {
        //i从2到根号x
        for (int i = 2; i * i <= x; i++) {
            //如果x能被其中一个i整除，说明x不是素数，返回false
            if (x % i == 0) {
                return false;
            }
        }
        //循环结束都没有返回false，则返回true
        return true;
    }

    /**
     * 埃筛法
     * 将合数标记为true，j = i * i 从 2 * i 优化而来，系数2会随着遍历递增（j += i，相当于递增了系数2），
     * 每一个合数都会有两个比本身要小的因子(0,1除外)，2 * i 必然会遍历到这两个因子
     * 当2递增到大于根号n时，其实后面的已经无需再判断（或者只需判断后面一段），而2到根号n、实际
     * 上在 i 递增的过程中已经计算过了，i 实际上就相当于根号n
     * 例如：n = 25 会计算以下
     * 2 * 4 = 8
     * 3 * 4 = 12
     * 但实际上8和12已经标记过，在n = 17时已经计算了 3 * 4，2 * 4
     *
     * @param n 统计n以内的素数个数
     * @return 返回n以内的素数的个数
     */
    public static int eratosthenes(int n) {
        //isPrime[n] = false 代表素数，true代表合数
        //boolean类型数组的默认值是false
        boolean[] isPrime = new boolean[n];
        //计数器统计素数个数
        int count = 0;
        for (int i = 2; i < n; i++) {
            //如果isPrime[i]为false，count+1
            if (!isPrime[i]) {
                count += 1;
                //判断合数，j是合数的标记位。j+=i 递增系数，找出i的倍数（合数），并标记为true
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        return count;
    }
}
