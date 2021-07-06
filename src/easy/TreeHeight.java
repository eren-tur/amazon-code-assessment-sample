import java.util.Scanner;
import java.util.*;
        import java.io.*;

class TreeHeightNode {
    TreeHeightNode left;
    TreeHeightNode right;
    int data;

    TreeHeightNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class TreeHeight {

    /*
    class TreeHeightNode
        int data;
        TreeHeightNode left;
        TreeHeightNode right;
    */
    public static int height(TreeHeightNode root) {
        int leftHeight = 0, rightHeight=0;
        if(root.left != null)
            leftHeight = Math.max(leftHeight,height(root.left)+1);
        if(root.right != null)
            rightHeight = Math.max(rightHeight,height(root.right)+1);

        return Math.max(leftHeight,rightHeight);
    }

    public static TreeHeightNode insert(TreeHeightNode root, int data) {
        if(root == null) {
            return new TreeHeightNode(data);
        } else {
            TreeHeightNode cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        TreeHeightNode root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }
}