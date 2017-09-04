package with_leetcode;

import java.util.*;


public class Solution {


    List<String> ans = new ArrayList<>();

    public static void main(String args[]) {
        Solution s1 = new Solution();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
//        root.left.left.left = new TreeNode(4);
//        root.left.left.right = new TreeNode(5);
//        root.left.right.left = new TreeNode(4);
//        root.left.right.right = new TreeNode(4);
//        root.right.left.left = new TreeNode(4);
//        root.right.left.right = new TreeNode(4);
//        root.right.right.left = new TreeNode(4);
//        root.right.right.right = new TreeNode(4);
//        root.left.left.left.left = new TreeNode(5);
//        root.left.left.left.right = new TreeNode(5);
        System.out.println(s1.fractionToDecimal(1, 99));
    }


    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)){
            sb.append("-");
        }
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        int intPart = numerator / denominator;
        sb.append(intPart);
        numerator -= intPart;
        if (numerator == 0){
            return sb.toString();
        }
        numerator *= 10;
        sb.append(".");
        HashSet<Integer> remaindersSet = new HashSet<>();
        ArrayList<Integer> remaindersList = new ArrayList<>();
        int repeatStart = -1;
        while (numerator < denominator){
            remaindersList.add(0);
            numerator *= 10;
        }
        numerator /= 10;
        while (true){
            numerator *= 10;
            int curRemainder = numerator / denominator;
            numerator -= curRemainder * denominator;
            if (numerator == 0){
                remaindersList.add(curRemainder);
                break;
            }
            else if (remaindersSet.contains(curRemainder)){
                repeatStart = curRemainder;
                break;
            }
            else {
                remaindersSet.add(curRemainder);
                remaindersList.add(curRemainder);
            }
        }
        for (int i = 0; i < remaindersList.size(); i++) {
            if (remaindersList.get(i) == repeatStart){
                sb.append("(");
            }
            sb.append(remaindersList.get(i));
        }
        if (repeatStart != -1)
            sb.append(")");
        return sb.toString();
    }


    public int findPeakElement(int[] nums) {
        int start = 0;
        if (nums.length == 0 || nums == null){
            return -1;
        }
        if (nums.length == 1){
            return 0;
        }
        if (nums[start] > nums[start + 1]){
            return start;
        }
        int end = nums.length - 1;
        if (nums[end] > nums[end - 1]){
            return end;
        }
        start += 1;
        end -= 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]){
                return mid;
            }
            else {
                if (nums[mid] > nums[mid + 1]){
                    end = mid - 1;
                }
                else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    public String reverseWords(String s) {
        Stack<Character> stack_line = new Stack<>();
        Stack<Character> stack_word = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (Character c :s.toCharArray()) {
            if (c == ' '){
                if (stack_line.isEmpty() || stack_line.peek() == ' '){
                    continue;
                }
                else {
                    stack_line.push(c);
                }
            }
            else {
                stack_line.push(c);
            }
        }
        if (stack_line.peek() == ' '){
            stack_line.pop();
        }
        while (!stack_line.isEmpty()){
            if (stack_line.peek() == ' '){
                sb.append(' ');
                stack_line.pop();
                continue;
            }
            while (stack_line.peek() != ' '){
                stack_word.push(stack_line.pop());
            }
            while (!stack_word.isEmpty()){
                sb.append(stack_word.pop());
            }
        }
        return sb.toString();
    }

    String Serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        if (root == null) return stringBuilder.toString();
        Stack s = new Stack();
        TreeNode curNode = root;
        while (!s.empty() || curNode != null) {
            while (curNode != null) {
                s.push(curNode);
                stringBuilder.append(curNode.val).append(",");
                curNode = curNode.left;
            }
            stringBuilder.append('$').append(",");
            TreeNode temp = (TreeNode) s.pop();
            curNode = temp.right;
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0)
            return null;
        String[] nums = str.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nums[0]));
        int[] length = new int[1];
        length[0] = 1;
        root.left = helpFunc(nums, length);
        root.right = helpFunc(nums, length);
        return root;
    }
    TreeNode helpFunc(String[] nums, int[] length) {
        if (nums.length==length[0])
            return null;
        TreeNode temp = null;
        if (!nums[length[0]].equals("$")) {
            temp = new TreeNode(Integer.parseInt(nums[length[0]]));
        }
        length[0]++;
        if (temp==null) return null;
        temp.left = helpFunc(nums, length);
        temp.right = helpFunc(nums, length);
        return temp;
    }

    public int findMin(int[] nums) {
        /**
         * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
         (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
         Find the minimum element.
         You may assume no duplicate exists in the array.
         * @param s
         * @param numRows
         * @return
         */
        if (nums == null) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int left = nums[start];
        int right = nums[end];
        if (nums.length == 1) {
            return nums[0];
        }
        start += 1;
        end -= 1;
        if (left < right) {
            return left;
        }
        if (right < nums[nums.length - 2]){
            return right;
        }
        int mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] > left) {
                //这个点在左半部分
                start = mid + 1;
            } else {
                //这个点在右半部分
                if (nums[mid] < nums[mid - 1]) {
                    return nums[mid];
                } else {
                    end = mid - 1;
                }
            }
        }
        return mid;
    }

    public String convert(String s, int numRows) {
        if (numRows < 2 || numRows >= s.length())
            return s;
        StringBuilder sb = new StringBuilder(s.length());

        int origStep = numRows * 2 - 2;
        int step;
        for (int i = 0; i < numRows; i++) {
            step = i == numRows - 1 ? origStep : origStep - i * 2;
            int curr = i;
            while (curr < s.length()) {
                sb.append(s.charAt(curr));
                curr += step;
                int temp = Math.abs(step - origStep);
                step = temp == 0 ? origStep : temp; //First/last rows
            }
        }
        return sb.toString();
    }


    public int reverse(int x) {
        long y = x % 10;
        while ((x /= 10) != 0) {
            y = y * 10 + x % 10;
        }
        return (Math.abs(y) < Integer.MAX_VALUE) ? 0 : (int) y;
    }

    public int myAtoi(String str) {
        int i = 0;
        int offset = 48;
        int isMinus = 0;
        str = str.trim();
        int len = str.length();
        if (len == 0) {
            return 0;
        }

        if (i == len) {
            return 0;
        }
        if ((str.charAt(i) == '-') || str.charAt(i) == '+') {
            i++;
            isMinus = (str.charAt(i) == '-') ? -1 : 1;
        }
        long res = 0;
        int n = 0;
        for (; i < len; i++) {
            if (str.charAt(i) - offset < 10 && str.charAt(i) - offset >= 0) {
                res = res * 10 + (str.charAt(i) - offset);
                if (++n > 10) {
                    return isMinus == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
            } else {
                break;
            }
        }
        if (-res <= Integer.MIN_VALUE && isMinus == -1) {
            return Integer.MIN_VALUE;
        }
        if (res > Integer.MAX_VALUE) {
            res = Integer.MAX_VALUE;
        }
        return (int) res * isMinus;
    }

    public boolean isPalindrome(int x) {
        int len = 1;
        long res = 0;
        int pre = x;
        res += x % 10;
        x = x / 10;
        while (x != 0) {
            res *= 10;
            res += x % 10;
            x = x / 10;
        }
        return (res - pre) == 0;
    }

    public boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        String map = "()[]{}";
//        Stack<Character> stack = new Stack<>();
        StringBuilder str = new StringBuilder(1);
        for (int i = 0; i < charArray.length; i++) {
            if (map.indexOf(charArray[i]) % 2 == 0) {
//                stack.push(charArray[i]);
                str.append(charArray[i]);
            } else {
                if (str.length() == 0) {
                    return false;
                } else if (Math.abs(str.charAt(str.length() - 1) - charArray[i]) > 2) {
                    return false;
                } else {
                    str.deleteCharAt(str.length() - 1);
                }
            }
        }
        return str.length() == 0;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int len = 1;
        int p = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == p) {
                continue;
            } else {
                p = nums[i];
                nums[len] = p;
                len++;
            }
        }
        return len;
    }

    public int removeElement(int[] nums, int val) {
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] == val) {
                while (j < nums.length && nums[j] == val) {
                    j++;
                }
                if (j < nums.length) nums[i++] = nums[j++];
            } else {
                nums[i++] = nums[j++];
            }
        }
        return i;
    }

    public int strStr(String haystack, String needle) {
        char[] data = haystack.toCharArray();
        char[] target = needle.toCharArray();
        for (int i = 0; i < data.length - target.length + 1; i++) {
            int j = 0;
            for (; j < target.length; j++) {
                System.out.print(target[j]);
                System.out.print(data[i + j]);
                if (target[j] != data[i + j]) {
                    break;
                }
            }
            if (j == target.length) {
                return i;
            }
        }
        return -1;
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isValidLine(board[i])) {
                return false;
            }
            char[] line = {board[0][i], board[1][i], board[2][i],
                    board[3][i], board[4][i], board[5][i],
                    board[6][i], board[7][i], board[8][i]};
            if (!isValidLine(line)) {
                return false;
            }
        }
        for (int i : new int[]{0, 3, 6}) {
            char[] line = {board[i + 0][0]
                    , board[i + 0][1]
                    , board[i + 0][2]
                    , board[i + 1][0]
                    , board[i + 1][1]
                    , board[i + 1][2]
                    , board[i + 2][0]
                    , board[i + 2][1]
                    , board[i + 2][2]};
            if (!isValidLine(line)) {
                return false;
            }
            char[] line2 = {board[0][i + 0]
                    , board[0][i + 1]
                    , board[0][i + 2]
                    , board[1][i + 0]
                    , board[1][i + 1]
                    , board[1][i + 2]
                    , board[2][i + 0]
                    , board[2][i + 1]
                    , board[2][i + 2]};
            if (!isValidLine(line2)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidLine(char[] line) {
        int[] nums = new int[10];
        for (int i = 0; i < 9; i++) {
            if (line[i] == '.') {
                continue;
            }
            if (nums[(int) line[i]] != 0) {
                return false;
            }
            nums[(int) line[i]] += 1;
        }
        return true;
    }

    public String countAndSay(int n) {
        StringBuilder pre = new StringBuilder();
        StringBuilder aft = new StringBuilder();
        int count = 1;
        char tmp;
        for (int i = 0; i < n; i++) {
            aft.setLength(0);
            if (i == 0) {
                aft.append(1);
            } else {
                for (int j = 0; j < pre.length(); j++) {
                    tmp = pre.charAt(j);
                    count = 1;
                    while (j + 1 < pre.length()) {
                        if (pre.charAt(j + 1) == tmp) {
                            count += 1;
                            j += 1;
                        } else {
                            break;
                        }
                    }
                    aft.append(count);
                    aft.append(tmp);
                }
            }
            pre = new StringBuilder(aft);
        }
        return pre.toString();
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        } else {
            if (strs.length % 2 == 0) {
                String[] strs_new = new String[strs.length / 2];
                for (int i = 0; i < strs.length / 2; i++) {
                    strs_new[i] = toolWith2String(strs[i], strs[i + strs.length / 2]);
                    if (strs_new[i].equals("")) {
                        return "";
                    }
                }
                return longestCommonPrefix(strs_new);
            } else {
                String tmp = toolWith2String(strs[strs.length - 1], strs[strs.length - 2]);
                if (tmp.equals("")) {
                    return "";
                }
                strs[strs.length - 2] = tmp;
                String[] strs_new = new String[((strs.length - 1)) / 2];
                for (int i = 0; i < (strs.length - 1) / 2; i++) {
                    strs_new[i] = toolWith2String(strs[i], strs[i + (strs.length - 1) / 2]);
                    if (strs_new[i].equals("")) {
                        return "";
                    }
                }
                return longestCommonPrefix(strs_new);
            }
        }
    }

    public String toolWith2String(String a, String b) {
        int l = a.length() < b.length() ? a.length() : b.length();
        StringBuilder sb = new StringBuilder(l);
        for (int i = 0; i < l; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                sb.append(a.charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public String longestCommonPrefix1(String[] strs) {
        StringBuilder result = new StringBuilder();

        if (strs != null && strs.length > 0) {

            Arrays.sort(strs);

            char[] a = strs[0].toCharArray();
            char[] b = strs[strs.length - 1].toCharArray();

            for (int i = 0; i < a.length; i++) {
                if (b.length > i && b[i] == a[i]) {
                    result.append(b[i]);
                } else {
                    return result.toString();
                }
            }
        }
        return result.toString();
    }

    public int lengthOfLastWord(String s) {
        int index = s.length();
        int res = 0;
        boolean find = false;
        while (--index >= 0) {
            if (!find && s.charAt(index) == ' ') {
                continue;
            } else if (find && s.charAt(index) == ' ') {
                return res;
            } else if (!find && s.charAt(index) != ' ') {
                find = true;
                res += 1;
            } else {
                res += 1;
            }
        }
        return res;
    }

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        while (--len >= 0) {
            if (digits[len] == 9) {
                digits[len] = 0;
            } else {
                digits[len] += 1;
                return digits;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    public int lengthOfLIS(int[] nums) {
        int[] res = new int[nums.length];
        if (nums.length < 2) {
            return nums.length;
        }
        int max = 1;
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && (res[j] + 1) > res[i]) {
                    res[i] = res[j] + 1;
                }
            }
            if (res[i] > max) {
                max = res[i];
            }
        }
        return max;
    }

    public int climbStairs(int n) {
        int[] a = new int[n + 1];
        a[0] = 1;
        int i = 0;
        while (i++ < n) {
            if (i - 1 >= 0) {
                a[i] += (a[i - 1]);
            }
            if (i - 2 >= 0) {
                a[i] += (a[i - 2]);
            }
        }
        return a[n];
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        int offset;
        do {
            n -= 1;
            offset = (n) % 26;
            sb.insert(0, (char) ('A' + offset));
            n = n / 26;
        } while (n != 0);
        return sb.toString();
    }

    public int titleToNumber(String s) {
        int i = s.length();
        int res = 0;
        int level = 1;
        while (i-- > 0) {
            res += (int) (s.charAt(i) - 'A' + 1) * level;
            level *= 26;
        }
        return res;
    }

    public int trailingZeroes(int n) {
        int count = 0;

        while (n > 0) {
            n /= 5;
            count += n;
        }

        return count;
    }


    public int reverseBits(long n) {
        long num;
        long mask = 1;
        long res = 0;
        res += (n & mask);
        ;
        mask <<= 1;
        for (int i = 1; i < 32; i++) {
            num = (n & mask) >> i;
            mask <<= 1;
            res <<= 1;
            res += num;
        }
        return (int) res;
    }

    public int compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        String[] temp;
        String[] small = nums1;
        String[] bigger = nums2;
        if (nums1.length != nums2.length) {
            if (nums1.length > nums2.length) {
                small = nums2;
                bigger = nums1;
                temp = new String[nums1.length];
                for (int j = 0; j < small.length; j++) {
                    temp[j] = small[j];
                }
                for (int j = small.length; j < temp.length; j++) {
                    temp[j] = "0";
                }
                nums2 = temp;
            } else {
                temp = new String[nums2.length];
                for (int j = 0; j < small.length; j++) {
                    temp[j] = small[j];
                }
                for (int j = small.length; j < temp.length; j++) {
                    temp[j] = "0";
                }

                nums1 = temp;
            }
        }
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            ans = compareNums(nums1[i], nums2[i]);
            if (ans == 0) {
                continue;
            } else {
                return ans;
            }
        }
        return ans;
    }

    public int compareNums(String num1, String num2) {
        //num1大的话返回1，反之返回-1,相等则返回0
        String n1 = "0", n2 = "0";
        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i) == '0')
                continue;
            else {
                n1 = num1.substring(i, num1.length());
                break;
            }
        }
        for (int i = 0; i < num2.length(); i++) {
            if (num2.charAt(i) == '0')
                continue;
            else {
                n2 = num2.substring(i, num2.length());
                break;
            }
        }
        num1 = n1;
        num2 = n2;
        if (num1.length() > num2.length()) {
            return 1;
        } else if (num1.length() < num2.length()) {
            return -1;
        } else {
            for (int i = 0; i < num1.length(); i++) {
                if (num1.charAt(i) > num2.charAt(i)) {
                    return 1;
                } else if (num1.charAt(i) < num2.charAt(i)) {
                    return -1;
                } else {
                    continue;
                }
            }
            return 0;
        }
    }

    public int hammingWeight(long n) {
        if (n == 0) {
            return 0;
        }
        int i = 0;
        long max = 2;
        int ans = 0;
        double pos = (Math.log(n) / Math.log(2));
        int j = (int) pos + 1;
        while (i < j) {
            if ((n & (max / 2)) == (max / 2)) {
                ans++;
            }
            max = max * 2;
            i++;
        }
        return ans;
    }


    public int rob(int[] nums) {
        int L = nums.length;
        int[] sum = new int[L];
        if (L == 1) {
            return nums[0];
        } else if (L == 0) {
            return 0;
        } else if (L == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        } else {
            sum[0] = nums[0];
            sum[1] = nums[0] > nums[1] ? nums[0] : nums[1];
            for (int i = 2; i < L; i++) {
                sum[i] = nums[i] + sum[i - 2] > sum[i - 1] ? nums[i] + sum[i - 2] : sum[i - 1];
            }
        }
        return sum[L - 1];
    }

    public boolean isHappy(int n) {
        int[] pow2 = {0, 1, 4, 9, 16, 25, 36, 49, 64, 81};
        HashSet<Integer> pre = new HashSet<>();
        if (n == 1) {
            return true;
        } else if (n == 0) {
            return false;
        }
        int sum = n;
        do {
            int tmp = 0;
            while (sum != 0) {
                tmp += pow2[sum % 10];
                sum = sum / 10;
            }
            sum = tmp;
            if (sum == 1) {
                return true;
            }
            if (pre.contains(sum)) {
                return false;
            }
            pre.add(sum);
        }
        while (true);
    }

    public ListNode removeElements(ListNode head, int val) {
        while (true) {
            if (head == null) {
                return null;
            } else if (head.val == val) {
                head = head.next;
            } else {
                break;
            }
        }
        ListNode pre = head;
        ListNode after = head.next;
        ListNode tmp = new ListNode(0);
        while (after != null) {
            if (after.val == val) {
                pre.next = after.next;
                after = pre.next;
            } else {
                tmp = after;
                after = after.next;
                pre = tmp;
            }
        }
        return head;
    }

    public int countPrimes(int n) {
        if (n < 3)
            return 0;

        boolean[] f = new boolean[n];
        //Arrays.fill(f, true); boolean[] are initialed as false by default
        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (f[i])
                continue;

            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer sValue = mapS.put(s.charAt(i), i);
            Integer tValue = mapT.put(t.charAt(i), i);
            if (sValue != null && tValue != null) {
                if (sValue.intValue() != tValue.intValue()) {
                    return false;
                }

            } else if (sValue != tValue) {
                return false;
            }
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        ListNode newHead = new ListNode(0);
        ListNode pos = head;
        while (pos != null) {
            ListNode temp = pos.next;
            ListNode temp1 = newHead.next;
            newHead.next = pos;
            pos.next = temp1;
            pos = temp;
        }
        return newHead.next;
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        HashSet<Integer> number = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (number.add(nums[i])) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> hashSet = new HashSet<>(k + 1);
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.size() == k + 1) {
                hashSet.remove(nums[pos++]);
            }
            if (hashSet.add(nums[i])) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        if (root.left == null && root.right == null) {
            ans.add(root.val + "");
        }
        for (String path : binaryTreePaths(root.left)) {
            ans.add(root.val + "->" + path);
        }
        for (String path : binaryTreePaths(root.right)) {
            ans.add(root.val + "->" + path);
        }
        return ans;
    }

    public List<String> bTreePathsRecursive(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        } else {
            bTreeSeach(root, "", paths);
        }
        return paths;
    }

    public void bTreeSeach(TreeNode root, String path, List<String> paths) {
        if (root.left == null && root.right == null) {
            paths.add(path + root.val);
        }
        if (root.left != null) {
            bTreeSeach(root.left, path + root.val + "->", paths);
        }
        if (root.right != null) {
            bTreeSeach(root.right, path + root.val + "->", paths);
        }
    }

    public int addDigits(int num) {
        String number = Integer.toString(num);
        int L = number.length();
        int ans = number.charAt(0) - '0';
        for (int i = 1; i < L; i++) {
            int temp = ans + number.charAt(i) - '0';
            if (temp > 9) {
                ans = temp % 10 + 1;
            } else ans = temp;
        }
        return ans;
    }

    public boolean isUgly(int num) {
        if (num == 2 || num == 3 || num == 5 || num == 1) {
            return true;
        }
        if (num == 0 || (num % 2) * (num % 5) * (num % 3) != 0) {
            return false;
        }
        if (num % 2 == 0) {
            return isUgly(num / 2);
        } else if (num % 3 == 0) {
            return isUgly(num / 3);
        } else {
            return isUgly(num / 5);
        }
    }

    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }

    public String longestPalindrome(String s) {
        int maxL = 0;
        boolean maxIsOdd = false;
        int maxIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            int odd = extendPalindrome(s, i, true);
            int notOdd = extendPalindrome(s, i, false);
            if (odd > maxL) {
                maxL = odd;
                maxIsOdd = true;
                maxIndex = i;
            }
            if (notOdd > maxL) {
                maxL = notOdd;
                maxIsOdd = false;
                maxIndex = i;
            }
        }
        if (maxIsOdd) {
            int offset = (maxL - 1) / 2;
            return s.substring(maxIndex - offset, maxIndex + offset + 1);
        } else {
            int offset = maxL / 2;
            return s.substring(maxIndex - offset + 1, maxIndex + offset + 1);
        }
    }

    public int extendPalindrome(String s, int index, boolean isOdd) {
        int i = 1;
        if (isOdd) {
            while ((index - i) >= 0 && (index + i) < s.length()) {
                if (s.charAt(index - i) == s.charAt(index + i)) {
                    i += 1;
                } else {
                    break;
                }
            }
            i -= 1;
            return i * 2 + 1;
        } else {
            while (index - i + 1 >= 0 && index + i < s.length()) {
                if (s.charAt(index - i + 1) == s.charAt(index + i)) {
                    i += 1;
                } else {
                    break;
                }
            }
            i -= 1;
            return i * 2;
        }
    }

    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right++;
            }
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] > 0 ? (nums[i - 1] + nums[i]) : nums[i];
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    public int uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) {
            paths[i][n - 1] = 1;
        }
        for (int i = 0; i < n; i++) {
            paths[m - 1][i] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                paths[i][j] = paths[i + 1][j] + paths[i][j + 1];
            }
        }
        return paths[0][0];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m == 1 && n == 1) {
            return (obstacleGrid[0][0] + 1) % 2;
        }
        boolean blocked = false;
        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        obstacleGrid[m - 1][n - 1] = 0;
        for (int i = m - 2; i >= 0; i--) {
            if (blocked) {
                obstacleGrid[i][n - 1] = 0;
            } else if (obstacleGrid[i][n - 1] == 1) {
                blocked = true;
                obstacleGrid[i][n - 1] = 0;
            } else {
                obstacleGrid[i][n - 1] = 1;
            }
        }
        blocked = false;
        for (int i = n - 2; i >= 0; i--) {
            if (blocked) {
                obstacleGrid[m - 1][i] = 0;
            } else if (obstacleGrid[m - 1][i] == 1) {
                blocked = true;
                obstacleGrid[m - 1][i] = 0;
            } else {
                obstacleGrid[m - 1][i] = 1;
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i + 1][j] + obstacleGrid[i][j + 1];
                }
            }
        }
        return obstacleGrid[0][0];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[m - 1][n - 1];
    }

    public int numDecodings(String s) {
        int[] ans = new int[s.length() + 1];
        if (s.length() < 1) {
            return 0;
        }
        ans[0] = 1;
        ans[1] = 1;
        if (s.charAt(0) == '0') {
            ans[1] = 0;
        }
        for (int i = 2; i <= s.length(); i++) {
            int one = Integer.parseInt(s.substring(i - 1, i));
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (one > 0 && one < 10) {
                ans[i] += ans[i - 1];
            }
            if (two > 9 && two < 27) {
                ans[i] += ans[i - 2];
            }
        }
        return ans[s.length()];
    }

    public List<TreeNode> generateTreesRecursive(int n) {
        List<TreeNode> ans = new ArrayList<>();
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        ans = generateTreesCoreRecursive(numbers);
        return ans;
    }

    public List<TreeNode> generateTreesCoreRecursive(LinkedList<Integer> numbers) {
        List<TreeNode> ans = new ArrayList<>();
        if (numbers.isEmpty()) {
            return ans;
        }
        if (numbers.size() == 1) {
            ans.add(new TreeNode(numbers.getFirst()));
        } else {
            for (Integer rootVal : numbers) {
                LinkedList<Integer> left = new LinkedList<>();
                LinkedList<Integer> right = new LinkedList<>();
                for (Integer node : numbers) {
                    if (node > rootVal) {
                        right.add(node);
                    } else if (node < rootVal) {
                        left.add(node);
                    } else {
                        continue;
                    }
                }

                List<TreeNode> LEFT = generateTreesCoreRecursive(left);
                List<TreeNode> RIGHT = generateTreesCoreRecursive(right);
                if (LEFT.isEmpty()) {
                    for (TreeNode nodeRight : RIGHT) {
                        TreeNode root = new TreeNode(rootVal);
                        root.right = nodeRight;
                        ans.add(root);
                    }
                } else if (RIGHT.isEmpty()) {
                    for (TreeNode nodeRight : LEFT) {
                        TreeNode root = new TreeNode(rootVal);
                        root.left = nodeRight;
                        ans.add(root);
                    }
                } else {
                    for (TreeNode nodeLeft : LEFT) {
                        for (TreeNode nodeRight : RIGHT) {
                            TreeNode root = new TreeNode(rootVal);
                            root.left = nodeLeft;
                            root.right = nodeRight;
                            ans.add(root);
                        }
                    }
                }
            }
        }
        return ans;
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] dp = new List[n + 1];
        dp[0] = new ArrayList<TreeNode>();
        if (n == 0) {
            return dp[0];
        }
        dp[0].add(null);
        dp[1] = new ArrayList<TreeNode>();
        dp[1].add(new TreeNode(1));
        for (int i = 2; i <= n; i++) {
            dp[i] = new ArrayList<TreeNode>();
            for (int j = 1; j <= i; j++) {
                for (TreeNode nodeLeft : dp[j - 1]) {
                    for (TreeNode nodeRight : dp[i - j]) {
                        TreeNode root = new TreeNode(j);
                        root.left = nodeLeft;
                        root.right = addOffset(nodeRight, j);
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[n];
    }

    public TreeNode addOffset(TreeNode node, int offset) {
        if (node == null) {
            return null;
        }
        TreeNode nodeCopy = new TreeNode(node.val + offset);
        nodeCopy.left = addOffset(node.left, offset);
        nodeCopy.right = addOffset(node.right, offset);
        return nodeCopy;
    }

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        if (n == 0) {
            return 1;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[i - j] * dp[j - 1];
            }
        }
        return dp[n];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();
        int width = triangle.get(triangle.size() - 1).size();
        int[] dp = new int[width];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = triangle.get(triangle.size() - 1).get(i);
        }
        for (int i = height - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[][] ans = new boolean[s.length() + 1][s.length()];
        int n = s.length() + 1;
        int m = s.length();

        for (int j = 0; j < m; j++) {
            ans[0][j] = true;//j开头长度为0的字符串是否存在
        }
        for (int i = 0; i < wordDict.size(); i++) {
            int offset = 0;
            int index = 0;
            while (offset < s.length()) {
                index = s.substring(offset).indexOf(wordDict.get(i));
                if (index > -1) {
                    ans[wordDict.get(i).length()][index + offset] = true;
                    offset = index + offset + 1;
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < n; i++) {//字符串长度为i
            for (int j = 0; j <= m - i; j++) {//从J开头 j, j+1, j+2, .k, k + 1...j+i.
                if (ans[i][j])
                    continue;
                for (int k = j; k < j + i; k++) {//从K分割，前半段长度为k - j + 1，从j开头；后半段长度为i + j - k - 1,从k + 1开头
                    ans[i][j] = (ans[k - j + 1][j] && ans[i + j - k - 1][k + 1]);
                    if (ans[i][j])
                        break;
                }
            }
        }
        return ans[n - 1][0];
    }

    public int maxProduct(int[] nums) {
        int ans = nums[0];
        int ans_ = nums[0];
        int max = ans;
        for (int i = 1; i < nums.length; i++) {
            int temp = ans;
            int temp_ = ans_;
            ans = Math.max(Math.max(temp * nums[i], temp_ * nums[i]), nums[i]);
            ans_ = Math.min(Math.min(temp * nums[i], temp_ * nums[i]), nums[i]);
            if (ans > max) {
                max = ans;
            }
        }
        return max;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        int m = nums1.length;
        int n = nums2.length;
        if (n == 0) {
            return -1;
        }
        if (m == 0) {
            if (n % 2 == 0) {
                return (nums2[n / 2] + nums2[n / 2 - 1]) / 2.0;
            } else {
                return nums2[n / 2];
            }
        }
        int i = 0;
        int j = 0;
        int left = 0;
        int right = 0;
        int iMin = 0;
        int iMax = m;
        while (iMin < iMax) {
            i = (iMax + iMin) / 2;
            j = (m + n) / 2 - i;
            if (j == 0) {
                break;
            }
            if (nums2[j - 1] > nums1[i]) {
                iMin = i + 1;
                i = iMin;
            } else if (i != 0 && nums1[i - 1] > nums2[j]) {
                iMax = i;
            } else {
                break;
            }
        }
        j = (m + n) / 2 - i;
        if (i == m) {
            right = nums2[j];
        } else if (j == n) {
            right = nums1[i];
        } else {
            right = Math.min(nums1[i], nums2[j]);
        }
        if ((m + n) % 2 == 1) {
            return right;
        }
        if (i == 0) {
            left = nums2[j - 1];
        } else if (j == 0) {
            left = nums1[i - 1];
        } else {
            left = Math.max(nums1[i - 1], nums2[j - 1]);
        }


        return (left + right) / 2.0;
    }

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        if (s == null || p == null) {
            return false;
        }
        dp[0][0] = true;
        for (int i = 2; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*' && dp[0][i - 2]) {
                dp[0][i] = true;
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j] || dp[i - 1][j - 2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int start = 0; start < nums.length - 2; start++) {
            if (start == 0 || nums[start] != nums[start - 1]) {
                int low = start + 1;
                int high = nums.length - 1;
                while (low < high) {
                    if (nums[low] + nums[high] + nums[start] == 0) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[start]);
                        tmp.add(nums[low]);
                        tmp.add(nums[high]);
                        ans.add(tmp);
                        while ((low < high) && nums[low] == nums[low + 1]) {
                            low++;
                        }
                        while ((high > low) && nums[high] == nums[high - 1]) {
                            high--;
                        }
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] + nums[start] < 0) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return ans;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = nums[0] + nums[1] + nums[2];
        if (min == target) {
            return target;
        }
        int sum;
        for (int start = 0; start < nums.length - 2; start++) {
            if (start == 0 || nums[start] != nums[start - 1]) {
                int low = start + 1;
                int high = nums.length - 1;
                while (low < high) {
                    sum = nums[low] + nums[high] + nums[start];
                    if (sum == target) {
                        return target;
                    } else if (sum < target) {
                        low++;
                        if (target - sum < Math.abs(min - target)) {
                            min = sum;
                        }
                    } else {
                        high--;
                        if (sum - target < Math.abs(min - target)) {
                            min = sum;
                        }
                    }
                }
            }
        }
        return min;
    }

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if (digits.length() == 0) {
            return ans;
        }
        ans.add("");
        int index = 0;
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for (int i = 0; i < digits.length(); i++) {
            index = (digits.charAt(i)) - '0';
            int count = ans.size();
            while (count-- > 0) {
                String head = ans.poll();
                for (int j = 0; j < mapping[index].length(); j++) {
                    ans.add(head + mapping[index].charAt(j));
                }
            }
        }

        return ans;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int start = 0;
        int next = 0;
        int subTarget = 0;
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                start = nums[i];
                if (start * 4 > target) {
                    return ans;
                }
                if (start + nums[nums.length - 1] * 3 < target) {
                    continue;
                }
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        next = nums[j];
                        if (next * 3 > target - start) {
                            break;
                        }
                        if (next + nums[nums.length - 1] * 2 < target - start) {
                            continue;
                        }
                        subTarget = target - (start + next);
                        int low = j + 1;
                        int high = nums.length - 1;
                        while (low < high) {
                            if (nums[low] + nums[high] == subTarget) {
                                List<Integer> tmp = new ArrayList<>();
                                tmp.add(start);
                                tmp.add(next);
                                tmp.add(nums[low]);
                                tmp.add(nums[high]);
                                ans.add(tmp);
                                while (low < high && nums[low + 1] == nums[low]) {
                                    low++;
                                }
                                while (low < high && nums[high - 1] == nums[high]) {
                                    high--;
                                }
                                low++;
                                high--;
                            } else if (nums[low] + nums[high] > subTarget) {
                                high--;
                            } else {
                                low++;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    public List<String> generateParenthesis(int n) {
        //n means how many pairs of parentheses
        int lcount = 0;
        int rcount = 0;
        helperParenthesis("", lcount, rcount, n);
        return ans;
    }

    public void helperParenthesis(String str, int lcount, int rcount, int n) {
        if (lcount > n || rcount > n || rcount > lcount) {
            return;
        } else {
            if (lcount == rcount && rcount == n) {
                ans.add(str);
                return;
            } else {
                helperParenthesis(str + "(", lcount + 1, rcount, n);
                helperParenthesis(str + ")", lcount, rcount + 1, n);
            }
        }
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean pos = true;
        if (dividend <= 0) {
            dividend = Math.abs(dividend);
            pos = !pos;
        }
        if (divisor < 0) {
            divisor = Math.abs(divisor);
            pos = !pos;
        }
        if (divisor > dividend) {
            return 0;
        }
        int ans = 0;
        int pretmp = divisor;
        int tmp = divisor;
        while (true) {
            int res = 1;
            while ((tmp <<= 1) < dividend && tmp > 0) {
                res += res;
                pretmp = tmp;
            }
            ans += res;
            int restDividend = dividend - (pretmp);
            tmp = divisor;
            if (restDividend < divisor) {
                if (pos)
                    return ans;
                else
                    return -ans;
            } else if (restDividend == divisor) {
                ans += 1;
                if (pos)
                    return ans;
                else
                    return -ans;
            } else {
                dividend = restDividend;
            }
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int K = lists.length;
        if (K < 1) {
            return null;
        }
        ListNode newRoot = new ListNode(0);
        PriorityQueue<ListNode> maxHeap = new PriorityQueue<>(K, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (int i = 0; i < K; i++) {
            if (lists[i] != null)
                maxHeap.add(lists[i]);
        }
        ListNode tail = newRoot;
        while (!maxHeap.isEmpty()) {
            ListNode top = maxHeap.poll();
            if (top.next != null) {
                maxHeap.add(top.next);
            }
            tail.next = top;
            tail = tail.next;
        }
        return newRoot.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newRoot = new ListNode(0);
        newRoot.next = head;
        ListNode pre = newRoot;
        ListNode start = head;
        ListNode root = head;
        while (root != null) {
            int count = k;
            ListNode tmpRoot = new ListNode(0);
            while (count > 0) {

                if (root == null) {
                    break;
                }
                count--;
                root = root.next;
            }
            if (root != null || count == 0) {
                ListNode end = root;
                ListNode tmp4pre = start;
                while (start != end) {
                    ListNode tmp1 = tmpRoot.next;
                    ListNode tmp2 = start.next;
                    tmpRoot.next = start;
                    tmpRoot.next.next = tmp1;
                    start = tmp2;
                }
                tmp4pre.next = end;
                pre.next = tmpRoot.next;
                pre = tmp4pre;
            } else {
                pre.next = start;
            }
        }
        return newRoot.next;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> path1 = pathToNode(root, p);
        LinkedList<TreeNode> path2 = pathToNode(root, q);
        TreeNode ans = null;
        while (!path1.isEmpty() && !path2.isEmpty() && path1.getFirst() == path2.getFirst()) {
            ans = path1.getFirst();
            path1.removeFirst();
            path2.removeFirst();
        }
        return ans;
    }

    public LinkedList<TreeNode> pathToNode(TreeNode root, TreeNode target) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<TreeNode> ans = new LinkedList<>();
        boolean find = false;
        if (root == null) {
            return null;
        }
        stack.push(root);
        ans.add(root);
        TreeNode preVisit = root;
        if (root == target) {
            return ans;
        }
        if (root.right != null) {
            stack.push(root.right);
        }
        if (root.left != null) {
            stack.push(root.left);
        }
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            while (ans.getLast().left != top && ans.getLast().right != top) {
                ans.removeLast();
            }
            ans.add(top);
            if (top == target) {
                return ans;
            }
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }
        return ans;
    }

    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        if (target < nums[0]) {
            return 0;
        }
        if (target > nums[end]) {
            return end + 1;
        }
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                if (mid < nums.length - 1 && nums[mid + 1] > target) {
                    return mid + 1;
                }
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return 0;
    }

    public int mySqrt(int x) {
        if (x <= 0) {
            return 0;
        }
        long start = x;
        while (start * start > x) {
            start = (start + x / start) / 2;
        }
        return (int) start;
    }

    public boolean isSymmetric_no_recursive(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode left_top = stack.pop();
            TreeNode right_top = stack.pop();
            if (left_top.val != right_top.val) {
                return false;
            }
            if (left_top.right != null) {
                if (right_top.left == null) {
                    return false;
                } else {
                    stack.push(right_top.left);
                    stack.push(left_top.right);
                }
            }
            if (left_top.left != null) {
                if (right_top.right == null) {
                    return false;
                } else {
                    stack.push(right_top.right);
                    stack.push(left_top.left);
                }
            }
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        if (left == null || right == null)
            return left == right;
        if (left.val != right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int height1 = maxDepth(root.left);
            int height2 = maxDepth(root.right);
            return 1 + (height1 > height2 ? height1 : height2);
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int depth = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = null;
        TreeNode cur;
        while (root != null) {
            stack.push(root);
            root = root.left;
            if (stack.size() > ans.size()) {
                ans.add(new ArrayList<>());
            }
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (cur.right != null && cur.right != last) {
                stack.push(cur);
                if (stack.size() > ans.size()) {
                    ans.add(new ArrayList<>());
                }
                cur = cur.right;
                while (cur != null) {
                    stack.push(cur);
                    if (stack.size() > ans.size()) {
                        ans.add(new ArrayList<>());
                    }
                    cur = cur.left;
                }
            } else {
                ans.get(stack.size()).add(cur.val);
                last = cur;
            }
        }
        Collections.reverse(ans);
        return ans;
    }

    public List<List<Integer>> levelOrderBottom_DFS(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        levelMaker(wrapList, root, 0);
        return wrapList;
    }

    public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) return;
        if (level >= list.size()) {
            list.add(0, new LinkedList<Integer>());
        }
        levelMaker(list, root.left, level + 1);
        levelMaker(list, root.right, level + 1);
        list.get(list.size() - level - 1).add(root.val);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        } else if (start > end) {
            return null;
        } else {
            int mid = (start + end) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = sortedArrayToBSTHelper(nums, start, mid - 1);
            root.right = sortedArrayToBSTHelper(nums, mid + 1, end);
            return root;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode preSlow = head;
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        while (fast != null && fast.next != null) {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        preSlow.next = null;
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }

    public String reverseVowels(String s) {
        HashSet<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        LinkedList<Integer> index = new LinkedList<>();
        Stack<Character> chars = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            if (vowels.contains(sb.charAt(i))) {
                index.add(i);
                chars.push(s.charAt(i));
            }
        }
        for (Integer i : index) {
            sb.setCharAt(i, chars.pop());
        }
        return sb.toString();
    }

    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root) != -1;
    }

    public int isBalancedHelper(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = isBalancedHelper(root.left);
            if (left == -1) {
                return -1;
            }
            int right = isBalancedHelper(root.right);
            if (right == -1) {
                return -1;
            }
            if (Math.abs(left - right) >= 2) {
                return -1;
            }
            return Math.max(left, right) + 1;
        }
    }

    public int minDepth(TreeNode root) {
        int min = 0;
        int count = 1;
        int nextCount = 0;
        int level = 1;
        boolean findMin = false;
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count -= 1;
            if (node.left == null && node.right == null) {
                return level;
            }
            if (node.left != null) {
                queue.add(node.left);
                nextCount += 1;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextCount += 1;
            }
            if (count == 0) {
                count = nextCount;
                level += 1;
                nextCount = 0;
            }
        }
        return level;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        LinkedList<TreeNode> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        int partSum = 0;
        if (root == null) {
            return false;
        }
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                partSum += root.val;
                if (partSum == sum && root.left == null && root.right == null) {
                    return true;
                }
                ans.add(root);
                root = root.left;
            }
            while (root == null) {
                if (stack.isEmpty()) {
                    return false;
                }
                root = stack.pop().right;
            }
            while (ans.getLast().left != root && ans.getLast().right != root) {
                partSum -= ans.removeLast().val;
            }
        }
        return false;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        LinkedList<TreeNode> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        int partSum = 0;
        if (root == null) {
            return paths;
        }
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                partSum += root.val;
                if (partSum == sum && root.left == null && root.right == null) {
                    List<Integer> path = new LinkedList<>();
                    for (TreeNode node : ans) {
                        path.add(node.val);
                    }
                    path.add(root.val);
                    paths.add(path);
                }
                ans.add(root);
                root = root.left;
            }
            while (root == null) {
                if (stack.isEmpty()) {
                    return paths;
                }
                root = stack.pop().right;
            }
            while (ans.getLast().left != root && ans.getLast().right != root) {
                partSum -= ans.removeLast().val;
            }
        }
        return paths;
    }

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            ArrayList<Integer> ans = new ArrayList<Integer>(1);
            ans.add(1);
            return ans;
        } else if (rowIndex < 0) {
            return null;
        } else if (rowIndex == 1) {
            ArrayList<Integer> ans = new ArrayList<Integer>(2);
            ans.add(1);
            ans.add(1);
            return ans;
        } else {
            ArrayList<Integer> ans = new ArrayList<Integer>(rowIndex + 1);
            List<Integer> lastAns = getRow(rowIndex - 1);
            ans.add(1);
            for (int i = 0; i < rowIndex - 1; i++) {
                ans.add(lastAns.get(i) + lastAns.get(i + 1));
            }
            ans.add(1);
            return ans;
        }
    }

    public List<Integer> getRow_noneRecursive(int rowIndex) {
        if (rowIndex < 0) {
            return null;
        }
        ArrayList<Integer> ans = new ArrayList<>(rowIndex + 1);
        ans.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--) {
                int tmp = ans.get(j - 1) + ans.get(j);
                ans.set(j, tmp);
            }
            ans.add(1);
        }
        return ans;
    }

    public int[] twoSum(int[] numbers, int target) {
        int pos = 0;
        int lastEnd = numbers.length - 1;
        while (true) {
            int threshold = target - numbers[pos];
            int start = pos + 1;
            int end = lastEnd;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (numbers[mid] == threshold) {
                    return new int[]{pos + 1, mid + 1};
                } else if (numbers[mid] < threshold) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            lastEnd = end;
            pos += 1;
        }
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int buy = 0;
        int sail = 0;
        while (buy < prices.length - 1 && sail < prices.length - 1) {
            while (buy < prices.length - 1 && prices[buy] > prices[buy + 1]) {
                buy++;
            }
            if (buy == prices.length - 1) {
                break;
            }
            maxProfit -= prices[buy];
            sail = buy + 1;
            while (sail < prices.length - 1 && prices[sail] < prices[sail + 1]) {
                sail++;
            }
            maxProfit += prices[sail];
            buy = sail + 1;
        }
        return maxProfit;
    }

    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    public int romanToInt(String s) {
        HashMap<Character, Integer> charTbale = new HashMap<>();
        charTbale.put('M', 1000);
        charTbale.put('D', 500);
        charTbale.put('C', 100);
        charTbale.put('L', 50);
        charTbale.put('X', 10);
        charTbale.put('V', 5);
        charTbale.put('I', 1);
        int ans = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (charTbale.get(s.charAt(i)) < charTbale.get(s.charAt(i + 1))) {
                ans += charTbale.get(s.charAt(i + 1)) - charTbale.get(s.charAt(i));
                i += 1;
            } else {
                ans += charTbale.get(s.charAt(i));
            }
        }
        if (s.length() == 1 || (s.length() >= 2 && charTbale.get(s.charAt(s.length() - 2)) >= charTbale.get(s.charAt(s.length() - 1))))
            ans += charTbale.get(s.charAt(s.length() - 1));
        return ans;
    }

    public int guess(int n) {
        int N = 1702766719;
        if (n > N) {
            return 1;
        } else if (n < N) {
            return -1;
        } else {
            return 0;
        }
    }

    public int guessNumber(int n) {
        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == 1) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return 0;
    }

    public void nextPermutation(int[] nums) {
        int max = nums[nums.length - 1];
        int min;
        int pos = -1;
        int swap = 0;
        int left, right;
        for (int i = nums.length - 2; i >= 0; i--) {
            //找第一个能够比右边的数小的数字
            if (nums[i] < max) {
                pos = i;
                break;
            } else {
                max = nums[i];
            }
        }
        if (pos == -1) {
            left = 0;
            right = nums.length - 1;
            while (left < right) {
                swap = nums[left];
                nums[left] = nums[right];
                nums[right] = swap;
                left++;
                right--;
            }
        } else {
            min = max;
            left = pos;//先将此位置的元素和右侧大于它的最小元素交换，然后从此位置右边开始调整至升序
            for (int i = left + 1; i < nums.length; i++) {
                if (nums[i] > nums[left]) {
                    if (nums[i] <= min) {
                        min = nums[i];
                        pos = i;
                    }
                }
            }
            right = pos;
            swap = nums[left];
            nums[left] = nums[right];
            nums[right] = swap;
            quicksort(nums, left + 1, nums.length - 1);
        }
        for (int num : nums
                ) {
            System.out.println(num);
        }
    }

    public void quicksort(int nums[], int start, int end) {
        int pivot = 0;
        if (start < end) {
            pivot = partition(nums, start, end);
            quicksort(nums, start, pivot - 1);
            quicksort(nums, pivot + 1, end);
        }
    }

    public int partition(int nums[], int low, int high) {
        int pivot = nums[low];
        while (low < high) {
            while (low < high && nums[high] > pivot) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] <= pivot) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }

    public boolean StringContain(String source, String target) {
        long hash = 0;
        for (int i = 0; i < source.length(); i++) {
            int pos = source.charAt(i) - 'A';
            long mask = 1;
            while (pos > 0) {
                mask <<= 1;
                pos -= 1;
            }
            hash = hash | mask;
        }
        for (int i = 0; i < target.length(); i++) {
            int pos = target.charAt(i) - 'A';
            long mask = 1;
            while (pos > 0) {
                mask <<= 1;
                pos -= 1;
            }
            if ((mask & hash) == 0) {
                return false;
            }
        }
        return true;
    }

    public RandomListNode Clone(RandomListNode pHead) {
        Construct(pHead);
        ConnectRandomNodes(pHead);
        RandomListNode newHead = Reconstruct(pHead);
        return newHead;
    }

    public void Construct(RandomListNode pHead) {
        RandomListNode curNode = pHead;
        while (curNode != null) {
            RandomListNode newNode = new RandomListNode(curNode.label);
            RandomListNode temp = curNode.next;
            curNode.next = newNode;
            newNode.next = temp;
            curNode = temp;
        }
    }

    public void ConnectRandomNodes(RandomListNode pHead) {
        RandomListNode curNode = pHead;
        while (curNode != null && curNode.next != null) {
            if (curNode.random != null) {
                curNode.next.random = curNode.random.next;
            }
            curNode = curNode.next.next;
        }
    }

    public RandomListNode Reconstruct(RandomListNode pHead) {
        RandomListNode newHead = pHead.next;
        RandomListNode curNode = newHead;
        while (curNode != null && curNode.next != null) {
            curNode.next = curNode.next.next;
            curNode = curNode.next;
        }
        return newHead;
    }
}