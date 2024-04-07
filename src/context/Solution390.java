package context;

import java.util.*;

public class Solution390 {
    public int maximumLengthSubstring(String s) {
        char[] chars = s.toCharArray();
        int[] cnt = new int[26];

        int i = 0, j = 0;

        int ans = 0;

        while (j < chars.length) {
            int cur = chars[j] - 'a';
            cnt[cur]++;
            while (cnt[cur] > 2 && i < j) {
                cnt[chars[i] - 'a']--;
                i++;
            }
            ans = Math.max(ans, j - i + 1);
            j++;
        }
        return ans;
    }


    public int minOperations(int k) {
        return 1;
    }


    class Pair {
        int num;

        long count;

        public Pair(int num, long count) {
            this.num = num;
            this.count = count;
        }
    }


    public long[] mostFrequentIDs(int[] nums, int[] freq) {

        int n = nums.length;
        long[] ans = new long[n];
        Map<Integer, Long> map = new HashMap<>();
        PriorityQueue<Pair> queue = new PriorityQueue<>((o1, o2) -> (int) (o2.count - o1.count));


        for (int i = 0; i < n; i++) {
            int x = nums[i];
            long c = map.merge(x, (long) freq[i], Long::sum);
            queue.add(new Pair(x, c));

            while (queue.peek().count != map.get(queue.peek().num)) {
                queue.poll();
            }

            ans[i] = queue.peek().count;
        }

        return ans;
    }


    class Node {
        Node[] childs = new Node[26];
        int minLength = Integer.MAX_VALUE;
        int i;
    }


    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Node root = new Node();
        for (int i = 0; i < wordsContainer.length; i++) {
            char[] chars = wordsContainer[i].toCharArray();
            int l = chars.length;
            Node cur = root;

            if (l < cur.minLength) {
                cur.minLength = l;
                cur.i = i;
            }

            for (int j = chars.length - 1; j >= 0; j--) {
                int b = chars[j] - 'a';
                if (cur.childs[b] == null) {
                    cur.childs[b] = new Node();
                }
                cur = cur.childs[b];

                if (l < cur.minLength) {
                    cur.minLength = l;
                    cur.i = i;
                }
            }
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            char[] s = wordsQuery[i].toCharArray();
            Node cur = root;
            for (int j = s.length - 1; j >= 0; j--) {
                int b = s[j] - 'a';
                if (cur.childs[b] != null){
                    cur = cur.childs[b];
                    ans[i] = cur.i;
                }else {
                    ans[i] = cur.i;

                    break;
                }

            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution390 solution390 = new Solution390();

        solution390.stringIndices(new String[]{"abcd","bcd","xbcd"},new String[]{"cd","bcd","xyz"});
    }
}
