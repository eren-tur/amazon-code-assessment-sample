package medium;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    class Node {
        int data;
        Node left;
        Node right;
    }

    List<Integer> array = new ArrayList<Integer>();
    boolean checkBST(Node root) {
        if(root == null) return true;

        boolean flag = checkBST(root.left);
        if(!flag) return false;

        array.add(root.data);

        flag = checkBST(root.right);
        if(!flag) return false;

        for (int i=0; i<array.size()-1; i++){
            if(array.get(i) >= array.get(i+1)){
                return false;
            }
        }

        return true;
    }
}

