package stack;


import java.util.Stack;

/**
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 */
public class ValidateStackSequences {
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (popped.length == 0 || pushed.length == 0) return true;
        Stack<Integer> stack = new Stack<>();

        int j = 0;

        for (int value : pushed) {
            if (stack.isEmpty() || stack.peek() != popped[j]) {
                stack.push(value);
            }
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }

        }

        return j == popped.length;
    }

    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}));
    }
}
