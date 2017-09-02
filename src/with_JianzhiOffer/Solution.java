package with_JianzhiOffer;
import com.sun.corba.se.impl.io.ObjectStreamClass;
import com.sun.corba.se.impl.naming.cosnaming.NamingUtils;
import com.sun.jmx.remote.internal.ArrayQueue;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import com.sun.scenario.effect.Merge;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.xml.internal.ws.api.config.management.policy.ManagedClientAssertion;
import com.sun.xml.internal.ws.api.model.MEP;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import with_leetcode.ListNode;
import with_leetcode.TreeNode;


import java.io.IOException;
import java.util.*;

/**
 * Created by qinlifei on 17-3-8.
 */
public class Solution {
    public int JumpFloorII(int target) {
        int[] res = new int[40];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i < target + 1; i++) {
            int n = 1;
            while ((i - n) > 0) {
                res[i] += res[i - n];
                n++;
            }
        }
        return res[target];
    }

    public int JumpFloor(int target) {
        int[] res = new int[40];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i < target + 1; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[target];
    }

    public int Fibonacci(int n) {
        int[] res = new int[40];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }

//    public int minNumberInRotateArray(int [] array) {
//        for (int i = 0; i < array.length - 1; i++) {
//            if (array[i] > array[i + 1]){
//                return array[i + 1];
//            }
//        }
//        int ans = array.length == 0 ?0 : array[0];
//        return ans;
//    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty() && stack1.isEmpty()) {
            return -1;
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

//    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
//        TreeNode root;
//        if(pre.length >= 1){
//            root = new TreeNode(pre[0]);
//        }
//        else {
//            return null;
//        }
//        int departPoint = -1;
//        for (int i = 0; i < pre.length; i++) {
//            if (in[i] == pre[0]){
//                departPoint = i;
//            }
//        }
//        int[] preLeft = new int[departPoint];
//        int[] preRight = new int[pre.length - departPoint - 1];
//        int[] inLeft = new int[departPoint];
//        int[] inRight = new int[pre.length - departPoint - 1];
//        for (int i = 0; i < pre.length; i++) {
//            if (i < departPoint){
//                preLeft[i] = pre[i + 1];
//                inLeft[i] = in[i];
//            }
//            else if (i > departPoint){
//                preRight[i - departPoint - 1] = pre[i];
//                inRight[i - departPoint - 1] = in[i];
//            }
//        }
//        root.left = reConstructBinaryTree(preLeft, inLeft);
//        root.right = reConstructBinaryTree(preRight, inRight);
//        return root;
//    }

    public void printFromEnd(ListNode root) {
        Stack<ListNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.next;
        }
        while (!stack.isEmpty()) {
            ListNode forPrint = stack.pop();
            System.out.print(forPrint.val);
        }
    }

    public String replaceBlank(String target) {
        StringBuilder forAns = new StringBuilder();
        if (target == null || target.length() < 1) {
            return "-1";
        }
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) != ' ') {
                forAns.append(target.charAt(i));
            } else {
                forAns.append("%20");
            }
        }
        return forAns.toString();
    }

    public int replaceBlank(char[] target, int usedLength) {
        int countOfBlank = 0;
        for (int i = 0; i < usedLength; i++) {
            if (target[i] == ' ') {
                countOfBlank++;
            }
        }
        int requiredLength = countOfBlank * 2 + usedLength;
        if (requiredLength > target.length) {
            return -1;
        }
        int oldEnd = usedLength - 1;
        int newEnd = requiredLength - 1;
        while (oldEnd >= 0) {
            if (target[oldEnd] == ' ') {
                target[newEnd--] = '0';
                target[newEnd--] = '2';
                target[newEnd--] = '%';
            } else {
                target[newEnd--] = target[oldEnd];
            }
            oldEnd--;
        }
        return requiredLength;
    }

    public boolean findNumber(int[][] m, int n) {
        if (m == null) {
            return false;
        }
        if (m[0][0] > n || m[m.length - 1][m[0].length - 1] < n) {
            return false;
        }
        int upRight_x = m[0].length - 1;
        int upRight_y = 0;
        while (upRight_x >= 0 && upRight_y < m.length) {
            int x = upRight_x;
            int y = upRight_y;
            if (m[x][y] > n) {
                upRight_x = upRight_x - 1;
            } else if (m[x][y] < n) {
                upRight_y += 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return false;
        } else if (root1 == null) {
            return false;
        } else {
            while (root1 != null) {
                if (isSameTree(root1, root2)) {
                    return true;
                } else {
                    return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
                }
            }
        }
        return false;
    }

    public boolean isSameTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val == root2.val) {
            return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
        }
        return false;
    }

    public void reOrderArray(int[] array) {
        if (array.length <= 1) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = 0;
            for (j = i - 1; j >= 0; j--) {
                if (aBiggerThanB(array[j], temp)) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = temp;
        }
    }

    public boolean aBiggerThanB(int a, int b) {
        if (a % 2 == 0 && b % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void Mirror(TreeNode root) {
        if (root != null) {
            if (root.left != null || root.right != null) {
                TreeNode temp = new TreeNode(0);
                root.left = temp;
                root.left = root.right;
                root.right = temp;
                Mirror(root.left);
                Mirror(root.right);
            }
        }
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int A = matrix[0].length;
        int B = matrix.length;
        if (A == 1) {
            for (int i = 0; i < B; i++) {
                ans.add(matrix[i][0]);
            }
            return ans;
        }
        if (B == 1) {
            for (int i = 0; i < A; i++) {
                ans.add(matrix[0][i]);
            }
            return ans;
        }
        int Length = Math.min(A, B);
        int circles = 0;
        circles = Length / 2;
        int circle = 0;
        while (circle < circles) {
            for (int i = circle; i < A - circle; i++) {
                ans.add(matrix[circle][i]);
            }
            for (int i = circle + 1; i < B - circle - 1; i++) {
                ans.add(matrix[i][A - circle - 1]);
            }
            for (int i = circle; i < A - circle; i++) {
                ans.add(matrix[B - circle - 1][A - i - 1]);
            }
            for (int i = circle + 1; i < B - circle - 1; i++) {
                ans.add(matrix[B - i - 1][circle]);
            }
            circle += 1;
        }
        if (Length % 2 == 0) {
            return ans;
        } else {
            for (int i = circles; i < A - circle; i++) {
                ans.add(matrix[circle][i]);
            }
        }
        return ans;
    }

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();
        if (popA.length != pushA.length) {
            return false;
        }
        int indexPop = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            if (popA[indexPop] == stack.peek()) {
                stack.pop();
                indexPop += 1;
            }
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != popA[indexPop]) {
                return false;
            } else {
                indexPop += 1;
            }
        }
        return true;
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> nodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode dequeue = queue.poll();
            nodes.add(dequeue.val);
            if (dequeue.left != null)
                queue.add(dequeue.left);
            if (dequeue.right != null)
                queue.add(dequeue.right);
        }
        return nodes;
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length < 3) {
            return true;
        }
        int root = sequence[sequence.length - 1];
        int i = 0;
        int departIndex = 0;
        boolean startRight = false;
        while (i < sequence.length - 1) {
            if (sequence[i] < root) {
                if (startRight) {
                    return false;
                }
                departIndex += 1;
            } else {
                startRight = true;
            }
            i++;
        }
        int[] left = new int[departIndex];
        int[] right = new int[sequence.length - departIndex - 1];
        if (departIndex < 3 && sequence.length - departIndex - 1 < 3) {
            return true;
        }
        while (i < sequence.length - 1) {
            if (i < departIndex) {
                left[i] = sequence[i];
            } else {
                right[i] = sequence[i];
            }
        }
        if (departIndex < 3) {
            return VerifySquenceOfBST(right);
        }
        if (sequence.length - departIndex - 1 < 3) {
            return VerifySquenceOfBST(left);
        }
        return VerifySquenceOfBST(left) && VerifySquenceOfBST(right);
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return null;
        }
        if (root.val > target) {
            return null;
        }
        if (root.left == null && root.right == null && root.val == target) {
            ArrayList<Integer> path = new ArrayList<>();
            path.add(root.val);
            ans.add(path);
            return ans;
        }
        if (root.left != null) {
            ArrayList<ArrayList<Integer>> ansLeft = FindPath(root.left, target - root.val);
            if (ansLeft != null) {
                for (ArrayList<Integer> path : ansLeft) {
                    path.add(0, root.val);
                }
                ans.addAll(ansLeft);
            }
        }

        if (root.right != null) {
            ArrayList<ArrayList<Integer>> ansRight = FindPath(root.right, target - root.val);
            if (ansRight != null) {
                for (ArrayList<Integer> path : ansRight) {
                    path.add(0, root.val);
                }
                ans.addAll(ansRight);
            }
        }
        return ans;
    }


    public RandomListNode Clone(RandomListNode pHead) {
        RandomListNode pos = pHead;
        while (pos != null) {
            RandomListNode temp = pos.next;
            RandomListNode newNode = new RandomListNode(pos.label);
            pos.next = newNode;
            newNode.next = temp;
            pos = temp;
        }
        pos = pHead;
        while (pos != null) {
            if (pos.random != null) {
                pos.next.random = pos.random.next;
            }
            pos = pos.next.next;
        }
        RandomListNode newHead = new RandomListNode(0);
        RandomListNode tail = newHead;
        while (pHead != null) {
            pos = pHead.next;
            pHead.next = pos.next;
            tail.next = pos;
            tail = tail.next;
            pHead = pHead.next;
        }
        RandomListNode s = new RandomListNode(0);
        RandomListNode s1 = s;
        while (pHead != null) {
            RandomListNode q = pHead.next;
            pHead.next = q.next;
            s.next = q;
            s = s.next;
            pHead = pHead.next;
        }
        return newHead.next;
    }


    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        TreeNode head = new TreeNode(0);
        TreeNode pos = head;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || pRootOfTree != null) {
            while (pRootOfTree != null) {
                stack.push(pRootOfTree);
                pRootOfTree = pRootOfTree.left;
            }
            TreeNode node = stack.pop();
            pos.right = node;
            node.left = pos;
            pos = pos.right;
            pRootOfTree = node.right;
        }
        head = head.right;
        head.left = null;
        return head;
    }

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return ans;
        }
        HashSet<String> set = new HashSet<>();
        ConvertHelper(set, str.toCharArray(), 0);
        ans.addAll(set);
        Collections.sort(ans);
        return ans;
    }

    public void ConvertHelper(HashSet<String> set, char[] str, int step) {
        if (step == str.length) {
            set.add(new String(str));
            return;
        }
        for (int i = step; i < str.length; i++) {
            swap(str, i, step);
            ConvertHelper(set, str, step + 1);
            swap(str, i, step);
        }
    }

    public void swap(char[] str, int i, int j) {
        if (i != j) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }

    public int MoreThanHalfNum_Solution(int[] array) {
        int ans = array[0];
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (ans == array[i]) {
                count += 1;
            } else {
                count -= 1;
            }
            if (count == 0) {
                ans = array[i];
                count += 1;
            }
        }
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ans) {
                count++;
            }
        }
        if (count > array.length / 2) {
            return ans;
        }
        return 0;
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length < k) {
            return list;
        }
        for (int len = k / 2 - 1; len >= 0; len--) {
            adjustMaxHeapSort(input, len, k - 1);
        }
        int temp;
        for (int i = k; i < input.length; i++) {
            if (input[i] < input[0]) {
                temp = input[0];
                input[0] = input[i];
                input[i] = temp;
                adjustMaxHeapSort(input, 0, k - 1);
            }
        }
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    public void adjustMaxHeapSort(int[] input, int pos, int length) {
        int temp;
        int child;
        for (temp = input[pos]; 2 * pos + 1 <= length; pos = child) {
            child = 2 * pos + 1;
            if (child < length && input[child] < input[child + 1]) {
                child++;
            }
            if (input[child] > temp) {
                input[pos] = input[child];
            } else {
                break;
            }
        }
        input[pos] = temp;
    }

    public void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        int[] dp = new int[array.length];
        int ans = array[0];
        dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = dp[i - 1] + array[i] > array[i] ? dp[i - 1] + array[i] : array[i];
            if (dp[i] > ans) {
                ans = dp[i];
            }
        }
        return ans;
    }

    public int NumberOf1Between1AndN_Solution(int n) {
        if (n < 0) {
            return 0;
        }
        int high = n;
        int cur = 0;
        int low = 0;
        int count = 0;
        int carry = 1;
        int temp = 0;
        while (high != 0) {
            high = n / (int) Math.pow(10, carry);
            cur = n % (int) Math.pow(10, carry) / (int) Math.pow(10, carry - 1);
            low = n % (int) Math.pow(10, carry) % (int) Math.pow(10, carry - 1);
            if (cur > 1) {
                count += high * (int) Math.pow(10, carry - 1) + (int) Math.pow(10, carry - 1);
            } else if (cur == 1) {
                count += high * (int) Math.pow(10, carry - 1) + 1 + low;
            } else {
                count += high * (int) Math.pow(10, carry - 1);
            }
            carry += 1;
        }
        return count;
    }

    public int GetUglyNumber_Solution(int index) {
        int uglyNumber = 1;
        if (index < 7) {
            return index;
        }
        Queue<Integer> q_2 = new LinkedList<>();
        Queue<Integer> q_3 = new LinkedList<>();
        Queue<Integer> q_5 = new LinkedList<>();
        q_2.add(2);
        q_3.add(3);
        q_5.add(5);
        int min;
        for (int i = 1; i < index; i++) {
            if (q_2.peek() < q_3.peek() && q_2.peek() < q_5.peek()) {
                min = q_2.poll();
                uglyNumber = min;
                q_2.add(min * 2);
                q_3.add(min * 3);
                q_5.add(min * 5);
            } else if (q_3.peek() < q_2.peek() && q_3.peek() < q_5.peek()) {
                min = q_3.poll();
                uglyNumber = min;
                q_3.add(min * 3);
                q_5.add(min * 5);
            } else {
                min = q_5.poll();
                uglyNumber = min;
                q_5.add(min * 5);
            }
        }
        return uglyNumber;
    }

    public int FirstNotRepeatingChar(String str) {
        int[] alpha = new int[60];
        if (str.length() < 1) {
            return -1;
        }
        HashMap<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            alpha[str.charAt(i) - 'A'] += 1;
            if (!pos.containsKey(str.charAt(i) - 'A')) {
                pos.put(str.charAt(i) - 'A', i);
            }
        }
        boolean exist = false;
        int min = -1;
        for (int i = 0; i < 60; i++) {
            if (!exist && alpha[i] == 1) {
                exist = true;
                min = pos.get(i);
            } else if (exist && alpha[i] == 1) {
                if (min > pos.get(i)) {
                    min = pos.get(i);
                }
            } else {
                continue;
            }
        }
        return min;
    }

    public String PrintMinNumber(int[] numbers) {
        //倒过来最大
        QuickSort(numbers, 0, numbers.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            sb.append(numbers[i]);
        }
        return sb.toString();
    }


    public void QuickSort(int[] numbers, int left, int right) {
        int i = left;
        int j = right;
        if (left < right) {
            int temp = numbers[left];
            while (i < j) {
                while (i < j && bigger(numbers[j], temp)) {
                    j--;
                }
                if (i < j) {
                    numbers[i++] = numbers[j];
                }
                while (i < j && !bigger(numbers[i], temp)) {
                    i++;
                }
                if (i < j) {
                    numbers[j--] = numbers[i];
                }
            }
            numbers[i] = temp;
            QuickSort(numbers, left, i - 1);
            QuickSort(numbers, i + 1, right);
        }
    }

    public boolean bigger(int a, int b) {
        //返回a是否比b大，如果大说明a应该放在后面，即放在数组的高位置
        if (a == b) {
            return true;
        }
        while (a != 0 && b != 0) {
            if (a % 10 > b % 10) {
                return true;
            } else if (a % 10 < b % 10) {
                return false;
            } else {
                a = a / 10;
                b = b / 10;
            }
        }
        if (a == 0) {
            return true;
        }
        return false;
    }


    public int InversePairs(int[] array) {
        long count = 0;
        count = MergeSort(array, 0, array.length - 1);
        return (int) (count % 1000000007);
    }

    public long MergeSort(int[] numbers, int left, int right) {
        long count = 0;
        long rightcount = 0;
        long leftcount = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            leftcount = MergeSort(numbers, left, mid);
            rightcount = MergeSort(numbers, mid + 1, right);
            count = Merge(numbers, left, mid, right);
        }
        return leftcount + rightcount + count;
    }

    public long Merge(int[] numbers, int left, int mid, int right) {
        long count = 0;
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (numbers[i] < numbers[j]) {
                temp[k++] = numbers[i++];
            } else {
                count += (mid - i + 1) % 1000000007;
                temp[k++] = numbers[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = numbers[i++];
        }
        while (j <= right) {
            temp[k++] = numbers[j++];
        }
        for (int l = 0; l < temp.length; l++) {
            numbers[left + l] = temp[l];
        }
        return count;
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        if (p1 == null || p2 == null) {
            return null;
        }
        while (p1 != p2) {
            if (p1 == null) {
                p1 = pHead2;
            } else if (p2 == null) {
                p2 = pHead1;
            } else {
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return p1;
    }

    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length <= 1) {
            return 0;
        }
        return GetNumberOfKCore(array, 0, array.length - 1, k);
    }

    public int GetNumberOfKCore(int[] numbers, int left, int right, int k) {
        if (numbers[right] < k || numbers[left] > k) {
            return 0;
        }
        if (numbers[right] == k && numbers[left] == k) {
            return right - left + 1;
        }
        int mid = (left + right) / 2;
        int leftCount = GetNumberOfKCore(numbers, left, mid, k);
        int rightCount = GetNumberOfKCore(numbers, mid + 1, right, k);
        return leftCount + rightCount;

    }

    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if (JudgeWithDepth(root) == -1) {
            return false;
        }
        return true;
    }

    public int JudgeWithDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = JudgeWithDepth(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = JudgeWithDepth(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }


    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int temp = FindNumsAppearOnceSingle(array);
        int mask = 1;
        while (true) {
            if ((temp & mask) == mask) {
                break;
            }
            mask = mask << 1;
        }
        for (int i = 0; i < array.length; i++) {
            if ((mask & array[i]) == mask) {
                num1[0] = num1[0] ^ array[i];
            } else {
                num2[0] = num2[0] ^ array[i];
            }
        }
    }

    public int FindNumsAppearOnceSingle(int[] array) {
        int ans = 0;
        for (int i = 0; i < array.length; i++) {
            ans = ans ^ array[i];
        }
        return ans;
    }

    public String LeftRotateString(String str, int n) {
        if (str.length() <= 0) {
            return "";
        }
        n = n % str.length();
        int pos = 0;
        int i = n;
        StringBuilder ans = new StringBuilder(str.length());
        while (i < str.length()) {
            ans.append(str.charAt(i++));
        }
        i = 0;
        while (i < n) {
            ans.append(str.charAt(i++));
        }
        return ans.toString();
    }

    public String ReverseSentence(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (chars[i] == ' ') {
                continue;
            } else {
                int j = i;
                while (j < str.length() && chars[j] != ' ') {
                    j += 1;
                }
                if (j == i) {
                    i += 1;
                    continue;
                } else {
                    ReverseArray(chars, i, j - 1);
                    i = j;
                }
            }
        }
        ReverseArray(chars, 0, str.length() - 1);
        String ans = "";
        for (char c : chars) {
            ans += c;
        }
        return ans;
    }

    public void ReverseArray(char[] chars, int start, int end) {
        int j = 0;
        for (int i = start; i < start + (end - start + 1) / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[end - j];
            chars[end - j] = temp;
            j++;
        }
    }

    public boolean isContinuous(int[] numbers) {
        if (numbers.length != 5) {
            return false;
        }
        int[] flag = new int[14];
        int max = 0;
        int min = 14;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                continue;
            } else {
                if (flag[numbers[i]] != 0) {
                    return false;
                } else {
                    flag[numbers[i]] = 1;
                    if (numbers[i] > max) {
                        max = numbers[i];
                    }
                    if (numbers[i] < min) {
                        min = numbers[i];
                    }
                }
            }
        }
        return (max - min) < 5;
    }

    public int LastRemaining_Solution(int n, int m) {
        int count = 0;
        int step = 0;
        if (n == 1) {
            return 0;
        }
        if (n < 1) {
            return -1;
        }
        LinkedList<Integer> root = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            root.add(i);
        }
        while (count < n - 1) {
            step = (step + m - 1) % root.size();
            root.remove(step);
            count += 1;
        }
        return root.get(0);
    }

    public int Sum_Solution(int n) {
        int sum = n;
        boolean flag = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }

    public int Add(int num1, int num2) {
        int carry = 0;
        int value = 0;
        if (num1 == 0 || num2 == 0) {
            return num1 == 0 ? num2 : num1;
        }
        do {
            carry = (num1 & num2) << 1;
            value = num1 ^ num2;
            num1 = carry;
            num2 = value;
        } while (num1 != 0);
        return value;
    }

    public int StrToInt(String str) {
        int ans = 0;
        int neg = 1;
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int start = 0;
        if (str.charAt(0) == '-') {
            neg = -1;
            start = 1;
        }
        if (str.charAt(0) == '+') {
            start = 1;
        }
        for (int i = start; i < str.length(); i++) {
            if ((str.charAt(i) - '0') > 9 || (str.charAt(i) - '0') < 0) {
                return 0;
            } else {
                ans *= 10;
                ans += (str.charAt(i) - '0');
            }
        }
        return neg * ans;
    }

    public boolean duplicate(int numbers[], int length, int[] duplication) {
        for (int i = 0; i < length; i++) {
            while (numbers[i] != i) {
                int temp = numbers[i];
                if (temp == numbers[temp]) {
                    duplication[0] = temp;
                    return true;
                } else {
                    numbers[i] = numbers[temp];
                    numbers[temp] = temp;
                }
            }
        }
        return false;
    }

    public int[] multiply(int[] A) {
        if (A == null || A.length <= 0) {
            return null;
        }
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        if (A.length == 1) {
            return left;
        }
        left[0] = 1;
        right[A.length - 1] = 1;
        for (int i = 1; i <= A.length - 1; i++) {
            left[i] = A[i - 1] * left[i - 1];
        }
        for (int i = A.length - 2; i >= 0; i--) {
            right[i] = A[i + 1] * right[i + 1];
        }
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }

    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        int indexStr = 0;
        int indexPattern = 0;
        return matchCore(str, indexStr, pattern, indexPattern);
    }

    public boolean matchCore(char[] str, int indexStr, char[] pattern, int indexPattern) {
        if (str.length == indexStr && indexPattern == pattern.length) {
            return true;
        }
        if (str.length != indexStr && indexPattern == pattern.length) {
            return false;
        }
        if (indexPattern + 1 < pattern.length && pattern[indexPattern + 1] == '*') {
            if ((indexStr < str.length && str[indexStr] == pattern[indexPattern]) || indexStr < str.length && pattern[indexPattern] == '.') {
                return matchCore(str, indexStr, pattern, indexPattern + 2) ||
                        matchCore(str, indexStr + 1, pattern, indexPattern) ||
                        matchCore(str, indexStr + 1, pattern, indexPattern + 2);
            } else {
                return matchCore(str, indexStr, pattern, indexPattern + 2);
            }
        }
        if (indexStr < str.length && (pattern[indexPattern] == '.' || pattern[indexPattern] == str[indexStr])) {
            return matchCore(str, indexStr + 1, pattern, indexPattern + 1);
        }
        return false;
    }

    public boolean isNumeric(char[] str) {
        if (str == null || str.length <= 0) {
            return false;
        }
        boolean findE = false;
        int pos = -1;
        for (int i = 0; i < str.length; i++) {
            if (!findE && (str[i] == 'e' || str[i] == 'E')) {
                findE = true;
                pos = i;
            }
        }
        if (!findE) {
            return isNumericCore(str, 0, str.length - 1, false);
        }
        return isNumericCore(str, 0, pos - 1, false) && isNumericCore(str, pos + 1, str.length - 1, true);
    }

    public boolean isNumericCore(char[] str, int start, int end, boolean findRightPoint) {
        if (start > end) {
            return false;
        }
        if (str[start] == '+' || str[start] == '-') {
            start += 1;
        }
        for (int i = start; i <= end; i++) {
            if (str[i] == '.') {
                if (i == end) {
                    return false;
                }
                if (findRightPoint) {
                    return false;
                }
                findRightPoint = true;
            } else if (str[i] - '0' < 0 || str[i] - '0' > 9) {
                return false;
            }
        }
        return true;
    }

    public ListNode deleteDuplication(ListNode pHead) {
        ListNode root = new ListNode(0);
        root.next = pHead;
        ListNode pre = root;
        ListNode p = root.next;
        boolean dup = false;
        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                p = p.next;
                dup = true;
            } else {
                if (dup) {
                    p = p.next;
                    pre.next = p;
                } else {
                    pre = pre.next;
                    p = p.next;
                }
                dup = false;
                if (p == null) {
                    break;
                }
            }
        }
        if (dup) {
            pre.next = null;
        }
        return root.next;
    }

    StringBuilder s = new StringBuilder();
    int[] table = new int[(int) Math.pow(2, 16)];
    ;

    public void Insert(char ch) {
        s.append(ch);
        table[ch] += 1;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        for (int i = 0; i < s.length(); i++) {
            if (table[s.charAt(i)] == 1) {
                return s.charAt(i);
            }
        }
        return '#';
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode slow = pHead;
        ListNode fast = pHead;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.next == null) {
            return null;
        }
        if (pNode.right != null) {
            TreeLinkNode p = pNode.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        if (pNode.left != null) {
            return pNode.next;
        }
        if (pNode.next.left == pNode) {
            return pNode.next;
        }
        while (pNode.next != null) {
            if (pNode.next.right == pNode) {
                pNode = pNode.next;
            } else {
                return pNode.next;
            }
        }
        return null;
    }

    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return isSymmetricalCore(pRoot.left, pRoot.right);
    }

    boolean isSymmetricalCore(TreeNode p1, TreeNode p2) {
        if (p1 == null && p2 == null) {
            return true;
        } else if (p1 == null || p2 == null) {
            return false;
        } else if (p1.val != p2.val) {
            return false;
        } else {
            return isSymmetricalCore(p1.left, p2.right) && isSymmetricalCore(p1.right, p2.left);
        }
    }

//    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
//        if (pRoot == null){
//            return null;
//        }
//        Stack<TreeNode> stack = new Stack<>();
//        Stack<TreeNode> reverseStack = new Stack<>();
//        ArrayList<ArrayList<Integer> > ans = new ArrayList<>();
//        stack.push(pRoot);
//        boolean reverse = false;
//        while (!stack.isEmpty() || !reverseStack.isEmpty()){
//            ArrayList<Integer> level = new ArrayList<>();
//            if (!reverse){
//                while (!stack.isEmpty()){
//                    TreeNode p = stack.pop();
//                    if (p.left != null)
//                        reverseStack.push(p.left);
//                    if (p.right != null)
//                        reverseStack.push(p.right);
//                    level.add(p.val);
//                }
//                ans.add(level);
//                reverse = !reverse;
//            }
//            else {
//                while (!reverseStack.isEmpty()){
//                    TreeNode p = reverseStack.pop();
//                    if (p.right != null)
//                        stack.push(p.right);
//                    if (p.left != null)
//                        stack.push(p.left);
//                    level.add(p.val);
//                }
//                ans.add(level);
//                reverse = !reverse;
//            }
//        }
//        return ans;
//    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (pRoot == null) {
            return ans;
        }
        if (pRoot.left != null)
            queue.add(pRoot.left);
        if (pRoot.right != null)
            queue.add(pRoot.right);
        TreeNode mark = pRoot.left;
        if (mark == null) {
            mark = pRoot.right;
        }
        ArrayList<Integer> level = new ArrayList<>();
        level.add(pRoot.val);
        while (!queue.isEmpty()) {
            TreeNode head = queue.remove();
            if (head == mark) {
                ans.add(level);
                level = new ArrayList<>();
                mark = head.left;
            }
            if (mark == null) {
                if (head.left != null) {
                    mark = head.left;
                } else
                    mark = head.right;
            }
            level.add(head.val);
            if (head.left != null)
                queue.add(head.left);
            if (head.right != null)
                queue.add(head.right);
        }
        ans.add(level);
        return ans;
    }

    public String Serialize(TreeNode root) {
        if (root == null) {
            return "{}";
        }
        StringBuilder s = new StringBuilder();
        s.append('{');
        ArrayList<TreeNode> q = new ArrayList<>();
        q.add(root);
        for (int index = 0; index < q.size(); index++) {
            TreeNode head = q.get(index);
            if (head == null) {
                continue;
            }
            q.add(head.left);
            q.add(head.right);

        }
        while (q.get(q.size() - 1) == null) {
            q.remove(q.size() - 1);
        }
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) == null) {
                s.append("#,");
            } else {
                s.append(q.get(i).val + ",");
            }
        }
        s.deleteCharAt(s.length() - 1);
        s.append('}');
        return s.toString();
    }

    public TreeNode Deserialize(String str) {
        if (str == null || str.length() < 3) {
            return null;
        }
        String[] nodeStr = str.substring(1, str.length() - 1).split(",");
        int val = Integer.valueOf(nodeStr[0]);
        TreeNode head = new TreeNode(val);
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(head);
        boolean isLeft = true;
        int index = 0;
        for (int i = 1; i < nodeStr.length; i++) {
            if (!Objects.equals(nodeStr[i], "#")) {
                val = Integer.valueOf(nodeStr[i]);
                TreeNode p = new TreeNode(val);
                nodes.add(p);
                if (isLeft) {
                    nodes.get(index).left = p;
                    isLeft = !isLeft;
                } else {
                    nodes.get(index++).right = p;
                    isLeft = !isLeft;
                }
            } else {
                if (isLeft) {
                    nodes.get(index).left = null;
                    isLeft = !isLeft;
                } else {
                    nodes.get(index++).right = null;
                    isLeft = !isLeft;
                }
            }
        }
        return head;
    }

    TreeNode KthNode(TreeNode pRoot, int k) {
        Stack<TreeNode> stack = new Stack<>();
        if (pRoot == null || k == 0) {
            return null;
        }
        while (pRoot != null || !stack.isEmpty()) {
            while (pRoot != null) {
                stack.push(pRoot);
                pRoot = pRoot.left;
            }
            TreeNode head = stack.pop();
            k -= 1;
            if (k == 0) {
                return head;
            }
            if (head.right != null) {
                pRoot = head.right;
            }
        }
        return null;
    }

    public ArrayList<Integer> prehalf = new ArrayList<>();
    public ArrayList<Integer> latterhalf = new ArrayList<>();
    public boolean countIsOdd = false;

    public void Insert(Integer num) {
        //偶数的时候插入
        if (!countIsOdd) {
            insertHeap(latterhalf, num, false);
            int min = pollHeap(latterhalf, false);
            insertHeap(prehalf, min, true);
        } else {
            insertHeap(prehalf, num, true);
            int max = pollHeap(prehalf, true);
            insertHeap(latterhalf, max, false);
        }
        countIsOdd = !countIsOdd;

    }

    public Double GetMedian() {
        if (countIsOdd) {
            return (double) (prehalf.get(0));
        } else {
            return (prehalf.get(0) + latterhalf.get(0)) / 2.0;
        }
    }

    public void insertHeap(ArrayList<Integer> heap, int val, boolean isMaxHeap) {
        int index = heap.size();
        if (isMaxHeap) {
            heap.add(val);
            while (index != 0) {
                int parent = findParent(index);
                if (heap.get(index) > heap.get(parent)) {
                    swap(heap, index, parent);
                    index = parent;
                } else {
                    break;
                }
            }
        } else {
            heap.add(val);
            while (index != 0) {
                int parent = findParent(index);
                if (heap.get(index) < heap.get(parent)) {
                    swap(heap, index, parent);
                    index = parent;
                } else {
                    break;
                }
            }
        }
    }

    public int pollHeap(ArrayList<Integer> heap, boolean isMaxHeap) {
        int ans = heap.get(0);
        int tail = heap.get(heap.size() - 1);
        int i = 0;
        int j = 0;
        heap.set(0, tail);
        heap.remove(heap.size() - 1);
        if (isMaxHeap) {
            while (i * 2 + 2 < heap.size()) {
                j = i * 2 + 1;
                if (heap.get(i * 2 + 1) < heap.get(i * 2 + 2)) {
                    j = i * 2 + 2;
                }
                if (heap.get(j) > heap.get(i)) {
                    swap(heap, i, j);
                }
                i = j;
            }
            if (i * 2 + 1 < heap.size() && heap.get(i * 2 + 1) > heap.get(i)) {
                swap(heap, i * 2 + 1, i);
            }
        } else {
            while (i * 2 + 2 < heap.size()) {
                j = i * 2 + 1;
                if (heap.get(i * 2 + 1) > heap.get(i * 2 + 2)) {
                    j = i * 2 + 2;
                }
                if (heap.get(j) < heap.get(i)) {
                    swap(heap, i, j);
                }
                i = j;
            }
            if (i * 2 + 1 < heap.size() && heap.get(i * 2 + 1) < heap.get(i)) {
                swap(heap, i * 2 + 1, i);
            }
        }
        return ans;
    }

    public int findParent(int index) {
        if (index % 2 == 0) {
            return (index - 2) / 2;
        } else {
            return (index - 1) / 2;
        }
    }

    public void swap(ArrayList<Integer> heap, int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        LinkedList<Integer> window = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        if (num.length < size || size == 0) {
            return null;
        }
        window.add(0);
        for (int i = 1; i < size; i++) {
            int j;
            for (j = window.size() - 1; j >= 0; j--) {
                if (num[window.get(j)] > num[i]) {
                    continue;
                } else {
                    break;
                }
            }
            window.add(j + 1, i);
        }
        ans.add(num[window.get(window.size() - 1)]);
        for (int i = size; i < num.length; i++) {
            while (!window.isEmpty() && num[i] >= num[window.getFirst()]) {
                window.removeFirst();
            }
            window.addFirst(i);
            while (!window.isEmpty() && i - window.getLast() >= size) {
                window.removeLast();
            }
            ans.add(num[window.getLast()]);
        }
        return ans;
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        ArrayList<Integer> start = new ArrayList();
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == str[0]) {
                start.add(i);
            }
        }
        if (start.size() == 0) {
            return false;
        }
        boolean ans = false;
        for (int i = 0; i < start.size(); i++) {
            ans = ans || hasPathCore(matrix, rows, cols, str, start);
            if (ans) {
                return ans;
            }
        }
        return ans;
    }

    public boolean hasPathCore(char[] matrix, int rows, int cols, char[] str, ArrayList<Integer> start) {
        int newRows = rows + 2;
        int newCols = cols + 2;
        int[] mark = new int[(newRows) * (newCols)];
        char[] newMatrix = new char[(newRows) * (newCols)];
        int tmp = 0;
        for (int i = 0; i < mark.length; i++) {
            if (i % newCols == 0 || i < newCols || i >= mark.length - newCols || (i + 1) % newCols == 0) {
                mark[i] = 1;
            } else {
                newMatrix[i] = matrix[tmp++];
            }

        }
        boolean ans = false;
        for (int i = 0; i < start.size(); i++) {
            int newStart = ((start.get(i) / cols) + 1) * newCols + ((start.get(i) % cols) + 1);
            int strIndex = 1;
            ans = ans || hasPathRecursive(newMatrix, mark, newRows, newCols, str, strIndex, newStart);
            if (ans) {
                return ans;
            }
        }
        return ans;
    }

    public boolean hasPathRecursive(char[] newMatrix, int[] old_mark, int rows, int cols, char[] str, int strIndex, int newStart) {
        int[] mark = new int[old_mark.length];
        for (int i = 0; i < old_mark.length; i++) {
            mark[i] = old_mark[i];
        }
        if (strIndex == str.length) {
            return true;
        }
        mark[newStart] = 1;
        int left = newStart - 1;
        int right = newStart + 1;
        int up = newStart - cols;
        int down = newStart + cols;
        boolean ans = true;
        if (mark[left] != 1 && str[strIndex] == newMatrix[left]) {
            ans = ans && hasPathRecursive(newMatrix, mark, rows, cols, str, strIndex + 1, left);
            if (ans) {
                return ans;
            }
        }
        ans = true;
        if (mark[right] != 1 && str[strIndex] == newMatrix[right]) {
            ans = ans && hasPathRecursive(newMatrix, mark, rows, cols, str, strIndex + 1, right);
            if (ans) {
                return ans;
            }
        }
        ans = true;
        if (mark[up] != 1 && str[strIndex] == newMatrix[up]) {
            ans = ans && hasPathRecursive(newMatrix, mark, rows, cols, str, strIndex + 1, up);
            if (ans) {
                return ans;
            }
        }
        ans = true;
        if (mark[down] != 1 && str[strIndex] == newMatrix[down]) {
            ans = ans && hasPathRecursive(newMatrix, mark, rows, cols, str, strIndex + 1, down);
            if (ans) {
                return ans;
            }
        }
        return false;
    }

    public int movingCount(int threshold, int rows, int cols) {
        int[][] mark = new int[rows][cols];
        if (threshold * cols * rows == 0) {
            return 0;
        }
        return movingCountCore(threshold, rows, cols, mark, 0, 0);
    }

    public int movingCountCore(int threshold, int rows, int cols, int[][] mark, int x, int y) {
        if ((bitsum(x, y) > threshold) || x < 0 || y < 0 || x >= cols || y >= rows || (mark[y][x] == 1)) {
            return 0;
        } else {
            mark[y][x] = 1;
        }
        return (1 + movingCountCore(threshold, rows, cols, mark, x - 1, y))
                + movingCountCore(threshold, rows, cols, mark, x + 1, y)
                + movingCountCore(threshold, rows, cols, mark, x, y - 1)
                + movingCountCore(threshold, rows, cols, mark, x, y + 1);
    }

    public int bitsum(int x, int y) {
        int ans = 0;
        while (x != 0) {
            ans += x % 10;
            x = x / 10;
        }
        while (y != 0) {
            ans += y % 10;
            y = y / 10;
        }
        return ans;
    }

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (sum <= 2) {
            return ans;
        }
        for (int i = (int) Math.sqrt(2 * sum); i >= 2; i--) {
            if (i % 2 != 0) {
                if (sum % i == 0) {
                    int mid = sum / i;
                    if (mid - i / 2 <= 0) {
                        break;
                    } else {
                        ArrayList<Integer> oneAns = new ArrayList<>();
                        for (int j = mid - i / 2; j <= mid + i / 2; j++) {
                            oneAns.add(j);
                        }
                        ans.add(oneAns);
                    }
                } else {
                    continue;
                }
            } else {
                if (((2 * sum) % i) != 0) {
                    continue;
                }
                int sumOfTwo = sum / (i / 2);
                if (sumOfTwo % 2 == 0) {
                    continue;
                } else {
                    int midLeft = sumOfTwo / 2;
                    int midRight = sumOfTwo / 2 + 1;
                    if (midLeft - i / 2 + 1 <= 0) {
                        break;
                    } else {
                        ArrayList<Integer> oneAns = new ArrayList<>();
                        for (int j = midLeft - i / 2 + 1; j < midRight + i / 2; j++) {
                            oneAns.add(j);
                        }
                        ans.add(oneAns);
                    }
                }
            }
        }
        return ans;
    }

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> ans = new ArrayList<>();
        int head = 0;
        int tail = array.length - 1;
        while (head < tail) {
            if (array[head] + array[tail] < sum) {
                head++;
            } else if (array[head] + array[tail] > sum) {
                tail--;
            } else {
                ans.add(array[head]);
                ans.add(array[tail]);
                return ans;
            }
        }
        return ans;
    }

    public String replaceSpace(StringBuffer str) {
        int count = 0;
        if (str.length() == 0) {
            return "";
        }
        int l = str.length();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                count += 1;
            }
        }
        int newTail = count * 2 + str.length() - 1;
        str.setLength(newTail + 1);
        for (int i = l - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ') {
                str.setCharAt(newTail--, '0');
                str.setCharAt(newTail--, '2');
                str.setCharAt(newTail--, '%');
            } else {
                str.setCharAt(newTail--, str.charAt(i));
            }
        }
        return str.toString();
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();
        while (listNode != null) {
            stack.push(listNode.val);
        }
        while (!stack.isEmpty()) {
            ans.add(stack.pop());
        }
        return ans;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        int l = pre.length;
        TreeNode root = reConstructBinaryTreeCore(pre, in, 0, l - 1, 0, l - 1);
        return root;
    }

    public TreeNode reConstructBinaryTreeCore(int[] pre, int[] in, int i_pre, int j_pre, int i_mid, int j_mid) {
        if (i_pre > j_pre || i_mid > j_mid) {
            return null;
        }
        TreeNode root = new TreeNode(pre[i_pre]);
        if (i_pre == j_pre || i_mid == j_mid) {
            return root;
        }
        int i_pre_left, j_pre_left;
        int i_mid_left, j_mid_left;
        int i_pre_right, j_pre_right;
        int i_mid_right, j_mid_right;
        int depart = 0;
        for (int i = i_mid; i <= j_mid; i++) {
            if (in[i] == pre[i_pre]) {
                depart = i;
                break;
            }
        }
        int lenLeft = depart - i_mid;
        int lenRight = j_mid - depart;
        i_pre_left = i_pre + 1;
        j_pre_left = i_pre + lenLeft;
        i_pre_right = i_pre + lenLeft + 1;
        j_pre_right = j_pre;
        i_mid_left = i_mid;
        j_mid_left = depart - 1;
        i_mid_right = depart + 1;
        j_mid_right = j_mid;
        if (lenLeft > 0)
            root.left = reConstructBinaryTreeCore(pre, in, i_pre_left, j_pre_left, i_mid_left, j_mid_left);
        if (lenRight > 0)
            root.right = reConstructBinaryTreeCore(pre, in, i_pre_right, j_pre_right, i_mid_right, j_mid_right);
        return root;
    }

    public int answers(int N) {
        boolean[][] matrix = new boolean[N + 1][(N + 1) / 2 + 2];
        matrix[1][1] = true;
        matrix[2][1] = false;
        matrix[3][1] = true;
        for (int i = 3; i <= N; i++) {//石子数量
            for (int j = 1; j < i / 2 + 1; j++) {//取多少
                if (j * 2 >= i) {
                    matrix[i][j] = false;
                } else {
                    boolean next = false;
                    for (int k = j; k > 0; k--) {//后手的影响
                        next = next || matrix[i - j][k];
                        if (next) {
                            break;
                        }
                    }
                    matrix[i][j] = !next;
                }
            }
        }
        for (int i = N / 2 + 1; i > 0; i--) {
            if (matrix[N][i]) {
                return i;
            }
        }
        return 0;
    }

    public boolean Find(int target, int[][] array) {
        return FindCore(target, array, 0, array[0].length - 1);
    }

    public boolean FindCore(int target, int[][] array, int x, int y) {
        if (y < 0 || x >= array[0].length) {
            return false;
        }
        if (array[x][y] == target) {
            return true;
        } else if (target > array[x][y]) {
            x += 1;
            return FindCore(target, array, x, y);
        } else {
            y -= 1;
            return FindCore(target, array, x, y);
        }
    }

    public void deleteNode(ListNode node) {
        while (node != null) {
            if (node.next == null) node = null;
            else {
                node.val = node.next.val;
                node = node.next;
            }
        }
    }

    public int minNumberInRotateArray(int[] array) {
        return minNumberInRotateArrayCore(array, 0, array.length - 1);
    }

    public int minNumberInRotateArrayCore(int[] array, int start, int end) {
        if (start == end) {
            return array[start];
        }
        if (start == end - 1) {
            if (array[start] > array[end]) {
                return array[end];
            }
            return array[start];
        }
        while (start < end - 1) {
            int mid = (start + end) / 2;
            if (array[mid] > array[end]) {
                return minNumberInRotateArrayCore(array, mid + 1, end);
            } else {
                return minNumberInRotateArrayCore(array, start, mid);
            }
        }
        return 0;
    }

    public boolean findNumberInSortedArray(int[] array, int n) {
        int start = 0;
        int end = array.length - 1;
        if (n < array[start] || n > array[end]) {
            return false;
        }
        while (start < end - 1) {
            int mid = (start + end) / 2;
            if (array[mid] > n) {
                end = mid;
            } else if (array[mid] < n) {
                start = mid;
            } else {
                return true;
            }
        }
        if (array[end] == n || array[start] == n) {
            return true;
        }
        return false;
    }


    public ArrayList<Integer> showSameNumber(int[] array1, int[] array2) {
        ArrayList<Integer> ans = new ArrayList<>();
        Arrays.sort(array1);
        for (int i = 0; i < array2.length; i++) {
            if (findNumberInSortedArray(array1, array2[i])) {
                ans.add(array2[i]);
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
//        root1.left = new TreeNode(6);
        root1.right = new TreeNode(2);
//        root1.left.left = new TreeNode(5);
//        root1.left.right = new TreeNode(7);
//        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(3);
        root1.right.right.right = new TreeNode(4);
        root1.right.right.right.right = new TreeNode(5);

        TreeNode root2 = new TreeNode(8);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(2);

        int[][] matrix = new int[4][4];
        matrix[0] = new int[]{1, 2, 3, 4};
        matrix[1] = new int[]{5, 6, 7, 8};
        matrix[2] = new int[]{9, 10, 11, 12};
        matrix[3] = new int[]{13, 14, 15, 16};

        int[][] x = new int[5][1];
        x[0] = new int[]{0};
        x[1] = new int[]{1};
        x[2] = new int[]{2};
        x[3] = new int[]{3};
        x[4] = new int[]{4};

        int[] push = new int[]{1,2,3,4,5};
        int[] pop = new int[]{4,5,3,2,1};

        int[] tree = new int[]{7,4,6,5};

        ListNode pHead = new ListNode(1);
        pHead.next = new ListNode(1);
        pHead.next.next = new ListNode(1);
        pHead.next.next.next = new ListNode(1);
        pHead.next.next.next.next = new ListNode(1);
        pHead.next.next.next.next.next = new ListNode(1);
        pHead.next.next.next.next.next.next = new ListNode(1);

        RandomListNode head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        head.next.next = new RandomListNode(3);
        head.next.next.next = new RandomListNode(4);
        head.next.next.next.next = new RandomListNode(5);
        head.random = head.next.next;
        head.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next;
        RandomListNode tmp = head;

        Solution test = new Solution();
        int[] numbers = new int[]{1,2,3,4,5};
//        System.out.println(test.Insert(root1, 2));
//        test.Insert(5);
//        test.Insert(2);
//        test.Insert(3);
//        test.Insert(4);
//        test.Insert(1);
//        test.Insert(6);
//        test.Insert(7);
//        test.Insert(0);
//        test.Insert(8);
//        boolean ans = test.hasPath("ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS".toCharArray(),5,8,"SGGFIECVAASABCEHJIGQEM".toCharArray());
        System.out.println(test.answers(6));
    }
}







