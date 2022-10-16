package contest301;

public class Solution {

    public boolean canChange(String start, String target) {
        if (!start.replaceAll("_", "").equals(target.replaceAll("_", ""))) {
            return false;
        }
        int n = start.length();
        int i = 0;
        int j = 0;

        while (i < n && j < n) {
            while (i < n && start.charAt(i) == '_') {
                i++;
            }

            while ( j < n && target.charAt(j) == '_') {
                j++;
            }

            if (i < n && j < n) {
                if (start.charAt(i) != target.charAt(j)) {
                    return false;
                }

                if (start.charAt(i) == 'L' && j > i) {
                    return false;
                }

                if (start.charAt(i) == 'R' && j < i) {
                    return false;
                }
                i++;
                j++;
            }
        }
        return true;
    }

    public int idealArrays(int n, int maxValue) {
        return 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.canChange("__", "__");
    }
}


