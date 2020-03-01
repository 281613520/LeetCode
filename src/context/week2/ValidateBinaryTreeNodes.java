package context.week2;

public class ValidateBinaryTreeNodes {
    public static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] tree = new int[n];

        for (int i = 0 ; i < n ; i++){
            int left = leftChild[i];
            int right = rightChild[i];
            if (left != -1){
                tree[left]++;
            }
            if (right != -1){
                tree[right]++;
            }
        }

        int count = 0;
        int count2 = 0;
        for (int tmp : tree){
            if (tmp == 0) count++;
            if (tmp>1) count2++;
        }

        return count == 1 && count2 ==0;
    }

    public static void main(String[] args) {
        validateBinaryTreeNodes(4,new int[]{1,-1,3,-1},new int[]{2,-1,-1,-1});
    }
}
