package with_leetcode;

import java.awt.*;
import java.util.*;

/**
 * Created by qlf_workpc on 2017/8/12 0012.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Character, HashSet> pos = new HashMap<>();
//A C G T/
        String line = scanner.nextLine();
        for (int i = 0; i < line.length(); i++) {
            if (pos.containsKey(line.charAt(i))){
                pos.get(line.charAt(i)).add(i);
            }
            else {
                pos.put(line.charAt(i), new HashSet<Integer>());
                pos.get(line.charAt(i)).add(i);
            }
        }
        char[] cur = new char[]{'A', 'C', 'G', 'T'};
        for (Character c: cur) {
            if (!pos.containsKey(c)){
                System.out.println(1);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

            }
        }
    }
}