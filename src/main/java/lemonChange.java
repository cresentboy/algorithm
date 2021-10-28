/**
 * 柠檬水找零
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 * 顾客排队购买你的产品，一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。必须给每个顾客正确找零
 * 注意，一开始你手头没有任何零钱。
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 * 输入：[5,5,5,10,20]
 * 输出：true
 * 输入：[10,10]
 * 输出：false
 *
 * @author formalhaut
 */
public class lemonChange {

    public static void main(String[] args) {
        System.out.println(change(new int[]{5, 5, 10}));
    }

    /**
     * 贪心算法
     * 局部最优达到整体最优
     *
     * @param bills
     * @return
     */
    private static boolean change(int[] bills) {

        int five = 0, ten = 0;
        //顾客是排队购买，数组代表购买顺序
        for (int bill : bills) {
            //如果遍历到的是5（给的是5元的）
            if (bill == 5) {
                //加一张5元
                five++;
                //给的是10元的
            } else if (bill == 10) {
                //判断手上有没有5元
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
                //如果给的是20
            } else {
                //判断手上有没有5元和10元
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                    //判断手上有没有3张5元
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
