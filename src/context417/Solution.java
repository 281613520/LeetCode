package context417;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public char kthCharacter(int k) {
        String s = "a";
        for (int i = 0; i < k; i++) {
            String tmp = "";
            for (int j = 0; j < s.length(); j++) {
                tmp += s.charAt(j) + 1;
            }
            s = s+tmp;
        }
        return s.charAt(k);
    }

    public long countSubarrays(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        int n = nums.length;
        int count  = 0;
        int right = 0;
        int left = 0;
        int cnt = 0;

        while (right < n) {
            if (nums[right] == max) {
                cnt++;
            }
            while (cnt == k) {
                if (nums[left] == max) {
                    cnt--;
                }
                left++;
            }
            count += left;

            right++;
        }

        return count;
    }


    public long countOfSubstrings(String word, int k) {
        return help3306(word,k) - help3306(word,k+1);
    }

    private long help3306(String word, int k) {
        String aeiou = "aeiou";
        Map<Character,Integer> aeiouCount = new HashMap<>();

        int right = 0;
        int left = 0;
        long ans = 0;
        int count = 0;

        while (right < word.length()) {
            char c = word.charAt(right);
            if (aeiou.indexOf(c) >= 0){
                aeiouCount.put(c,aeiouCount.getOrDefault(c,0)+1);
            }else {
                count++;
            }
            while (aeiouCount.size()==5 && count >= k) {
                if (aeiou.indexOf(word.charAt(left)) >= 0) {
                    aeiouCount.merge(word.charAt(left),-1,Integer::sum);
                    if (aeiouCount.get(word.charAt(left)) == 0) {
                        aeiouCount.remove(word.charAt(left));
                    }
                }else {
                    count--;
                }
                left++;
            }
            ans+= left;

            right ++;
        }

        return ans;


    }


}
