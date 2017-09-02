package with_leetcode;

/**
 * Created by qlf_workpc on 2017/8/14 0014.
 */
public class Kmp {
    public static void main(String[] args) {

    }
    public int kmpMatch(String source, String target){
        int[] next = getNext(target);
        while (true)
        return -1;
    }

    public int[] getNext(String target){
        int[] next = new int[target.length()];
        int k = -1;
        next[0] = -1;
        int i = 0;
        while (i < target.length() - 1){
            if (k == -1 || target.charAt(i) == target.charAt(k)) {
                k++;
                i++;
                next[i] = k;
            }
            else{
                k = next[k];
            }
        }
        return next;
    }
}
