import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
        import java.io.*;

class TreeLevelOrderTraversalNode {
    TreeLevelOrderTraversalNode left;
    TreeLevelOrderTraversalNode right;
    int data;

    TreeLevelOrderTraversalNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class TreeLevelOrderTraversal {

    /*
    
    class TreeLevelOrderTraversalNode 
        int data;
        TreeLevelOrderTraversalNode left;
        TreeLevelOrderTraversalNode right;
    */
    public static void levelOrder(TreeLevelOrderTraversalNode root) {
        ArrayList<TreeLevelOrderTraversalNode> nexts = new ArrayList<TreeLevelOrderTraversalNode>();
        nexts.add(root);

        while (!nexts.isEmpty())
        {
            TreeLevelOrderTraversalNode current = nexts.get(0);
            nexts.remove(0);
            if(current==null) continue;

            System.out.print(current.data+" ");

            nexts.add(current.left);
            nexts.add(current.right);
        }
    }

    public static TreeLevelOrderTraversalNode insert(TreeLevelOrderTraversalNode root, int data) {
        if(root == null) {
            return new TreeLevelOrderTraversalNode(data);
        } else {
            TreeLevelOrderTraversalNode cur;
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
        TreeLevelOrderTraversalNode root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        levelOrder(root);
    }
}