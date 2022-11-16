package daily;

import javax.swing.*;
import java.util.*;

public class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] dislike : dislikes) {
            if (graph.containsKey(dislike[0])) {
                graph.get(dislike[0]).add(dislike[1]);
            } else {
                graph.put(dislike[0], new HashSet<Integer>() {{
                    add(dislike[1]);
                }});
            }
            if (graph.containsKey(dislike[1])) {
                graph.get(dislike[1]).add(dislike[0]);
            } else {
                graph.put(dislike[1], new HashSet<Integer>() {{
                    add(dislike[0]);
                }});
            }
        }

        // 默认丢在set1 中
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            if (!set1.contains(i) && !set2.contains(i)) {
                if (!dfs(i, set1, set2, graph, 1)) {
                    return false;
                }
            }
        }


        return true;
    }

    private boolean dfs(int num, Set<Integer> set1, Set<Integer> set2, Map<Integer, Set<Integer>> graph, int status) {
        Set<Integer> tmp = graph.getOrDefault(num, new HashSet<>());

        if (status == 1) {
            set1.add(num);
        } else if (status == 2) {
            set2.add(num);
        }

        for (int value : tmp) {
            if ((set1.contains(value) && set1.contains(num)) || (set2.contains(value) && set2.contains(num))) {
                return false;
            }

            if (!set1.contains(value) && !set2.contains(value)) {
                if (!dfs(value, set1, set2, graph, 3 - status)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        backtrace(s, 0, ans, "");
        return ans;
    }

    private void backtrace(String s, int location, List<String> ans, String tmp) {
        StringBuilder tmpBuilder = new StringBuilder(tmp);
        while (location < s.length() && Character.isDigit(s.charAt(location))) {
            tmpBuilder.append(s.charAt(location));
            location++;
        }
        tmp = tmpBuilder.toString();

        if (location == s.length()) {
            ans.add(tmp);
            return;
        }

        tmp += Character.toLowerCase(s.charAt(location));
        backtrace(s, location + 1, ans, tmp);
        tmp = tmp.substring(0, tmp.length() - 1) + Character.toUpperCase(s.charAt(location));
        backtrace(s, location + 1, ans, tmp);
    }


    public int reachNumber(int target) {
        target = Math.abs(target);
        int tmp = target;
        int k = 1;

        while (tmp > 0) {
            tmp -= k;
            k++;
        }

        if (tmp == 0) {
            return k - 1;
        } else {
            int diff = target - tmp;
            return k - 2 + diff * 2;
        }
    }


    public List<String> ambiguousCoordinates(String s) {
        List<String> ans = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }

        String after = sb.toString();


        for (int i = 1; i <= after.length(); i++) {
            String first = after.substring(0, i);
            String second = after.substring(i);
            if (second.equals("")) {
                continue;
            }
            if (Integer.parseInt(first) == 0 && first.length() > 1 || Integer.parseInt(second) == 0 && second.length() > 1) {
                continue;
            } else {
                List<String> a = new ArrayList<>();
                List<String> b = new ArrayList<>();

                decode(first, a);
                decode(second, b);

                for (String item : a) {
                    for (String value : b) {
                        ans.add("(" + item + ", " + value + ")");
                    }
                }

            }
        }

        return ans;
    }
    //(0,1.23)

    private void decode(String str, List<String> list) {
        for (int i = 1; i <= str.length(); i++) {
            String first = str.substring(0, i);
            String second = str.substring(i);
            if (second.equals("")) {
                if (!(first.startsWith("0") && first.length() > 1)) {
                    list.add(first);
                }
            } else {
                if (first.startsWith("0") && first.length() > 1 || second.endsWith("0")) {
                } else {
                    list.add(first + "." + second);
                }
            }
        }
    }

    public boolean isIdealPermutation(int[] nums) {
        int countAll = 0;
        int countPart = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int num = nums[i];
            if (num > i) {
                countAll += num - i;
            }
            if (num > nums[i + 1]) {
                if(num == i){
                    countAll ++;
                }
                countPart++;
            }
        }

        return countPart == countAll;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.isIdealPermutation(new int[]{1, 0, 2});
    }
}
