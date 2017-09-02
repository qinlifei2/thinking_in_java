package with_leetcode;

/**
 * Created by qlf_workpc on 2016/12/18 0018.
 */
public class MinStack {
    int[] save;
    int[] min;
    int pos;
    public MinStack() {
        save = new int[10];
        min = new int[10];
        pos = -1;
    }

    public void push(int x) {
        pos ++;
        if(pos >= save.length){
            int[] save_bigger = new int[save.length * 2];
            int[] min_bigger = new int[min.length * 2];
            for (int i = 0; i < save.length; i++) {
                save_bigger[i] = save[i];
                min_bigger[i] = min[i];
            }
            this.save = save_bigger;
            this.min = min_bigger;
            pos --;
            this.push(x);
        }
        else {
            save[pos] = x;
        }
        if(pos == 0){
            min[pos] = x;
        }
        else {
            if(x < min[pos - 1]){
                min[pos] = x;
            }
            else {
                min[pos] = min[pos - 1];
            }
        }
    }

    public void pop() {
        pos --;
    }

    public int top() {
        return save[pos];
    }

    public int getMin() {
        return min[pos];
    }

    public static void main(String args[]){
        MinStack obj = new MinStack();
        obj.push(85);
        obj.push(-99);
        obj.push(67);
        System.out.print(obj.getMin());
        obj.push(-27);
        obj.push(61);
        obj.push(-97);
        obj.push(-27);
        obj.push(35);
        obj.top();
        obj.push(99);
        obj.push(-66);
        System.out.print(obj.getMin());
        obj.push(-89);
        System.out.print(obj.getMin());
        obj.push(4);
        obj.push(-70);
        System.out.print(obj.getMin());
        obj.push(52);
        obj.top();
        obj.push(54);
        System.out.print(obj.getMin());
        obj.push(94);
        obj.push(-41);
        obj.push(-75);
        obj.push(-32);
        System.out.print(obj.getMin());
        obj.push(5);
        obj.push(29);
        obj.top();
        obj.push(-78);
        obj.push(-74);
        System.out.print(obj.getMin());
        obj.pop();
        System.out.print(obj.getMin());
        obj.push(-58);
        obj.push(-44);
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        obj.push(-64);
        System.out.print(obj.getMin());
        obj.pop();
        obj.push(-45);
        obj.push(-99);
        obj.push(-27);
        System.out.print(obj.getMin());
        obj.push(-96);
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        obj.pop();
        System.out.print(obj.getMin());
        obj.push(26);
        obj.push(-58);
        System.out.print(obj.getMin());
        obj.top();
        System.out.print(obj.getMin());
        obj.push(25);
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        obj.push(33);
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        obj.push(71);
        System.out.print(obj.getMin());
        obj.push(-62);
        System.out.print(obj.getMin());
        obj.push(-78);
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        obj.pop();
        System.out.print(obj.getMin());
        obj.push(-30);
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
        obj.push(85);
        obj.push(-15);
        obj.pop();
        obj.push(-40);
        System.out.print(obj.getMin());
        obj.push(72);
        obj.top();
        obj.top();
        obj.push(18);
        obj.push(59);
        System.out.print(obj.getMin());
        obj.pop();
        System.out.print(obj.getMin());
        obj.push(-59);
        obj.top();
        obj.push(10);
        System.out.print(obj.getMin());
        obj.push(9);
        System.out.print(obj.getMin());
        System.out.print(obj.getMin());
    }
}
/**
 * ["MinStack","push","push","push","push","getMin","pop","getMin","pop","getMin","pop","getMin"]
 [[],[2],[0],[3],[0],[],[],[],[],[],[],[]]
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */