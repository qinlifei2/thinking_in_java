package with_JianzhiOffer;
import java.util.ArrayList;
import java.util.Stack;
/**
 * Created by qinlifei on 17-3-12.
 */
public class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> min = new Stack<>();
    public void push(int node) {
        if (stack.isEmpty()){
            stack.push(node);
            min.push(node);
        }
        else {
            stack.push(node);
            if (node < min.peek()){
                min.push(node);
            }
            else {
                min.push(min.peek());
            }
        }
    }

    public void pop() {
        int forPop = 0;
        if (stack.isEmpty()){
            System.out.println(false);
        }
        else {
            stack.pop();
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min.peek();
    }
}
