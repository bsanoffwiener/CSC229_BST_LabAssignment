package com.mycompany.csc229_bst_example;

/**
 *
 * @author MoaathAlrajab
 */
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    private BstNode root;

    public boolean isEmpty() {
        return (this.root == null);
    }

    public void insert(Integer data) {

        System.out.print("[input: " + data + "]");
        if (root == null) {
            this.root = new BstNode(data);
            System.out.println(" -> inserted: " + data);
            return;
        }
        insertNode(this.root, data);
        System.out.print(" -> inserted: " + data);
        System.out.println();
    }

    private BstNode insertNode(BstNode root, Integer data) {

        BstNode tmpNode = null;
        System.out.print(" ->" + root.getData());
        if (root.getData() >= data) {
            System.out.print(" [L]");
            if (root.getLeft() == null) {
                root.setLeft(new BstNode(data));
                return root.getLeft();
            } else {
                tmpNode = root.getLeft();
            }
        } else {
            System.out.print(" [R]");
            if (root.getRight() == null) {
                root.setRight(new BstNode(data));
                return root.getRight();
            } else {
                tmpNode = root.getRight();
            }
        }
        return insertNode(tmpNode, data);
    }

    // Public method that starts in-order traversal
    public void inOrderTraversal() {
        doInOrder(this.root);
    }

    // In-order traversal: LEFT → ROOT → RIGHT
    private void doInOrder(BstNode root) {
        if (root == null)
            return; // Base case: end of branch

        doInOrder(root.getLeft()); // Visit left subtree
        System.out.print(root.getData() + " "); // Visit current node
        doInOrder(root.getRight()); // Visit right subtree
    }

    // Public method that starts pre-order traversal
    public void preOrderTraversal() {
        doPreOrder(this.root);
    }

    // Pre-order: ROOT → LEFT → RIGHT
    private void doPreOrder(BstNode root) {
        if (root == null)
            return;

        System.out.print(root.getData() + " "); // Visit current node
        doPreOrder(root.getLeft()); // Traverse left
        doPreOrder(root.getRight()); // Traverse right
    }

    // Public height method
    public Integer findHeight() {
        return height(this.root);
    }

    // Computes height: longest path from this node to a leaf
    public int height(BstNode node) {
        if (node == null)
            return -1; // Empty tree has height -1

        int leftHeight = height(node.getLeft()); // Height of left subtree
        int rightHeight = height(node.getRight()); // Height of right subtree

        return Math.max(leftHeight, rightHeight) + 1; // Add this level
    }

    // Public method to get the depth of a node
    public int getDepth(BstNode node) {
        return getDepthHelper(this.root, node, 0);
    }

    // Computes depth (distance from root)
    private int getDepthHelper(BstNode current, BstNode target, int depth) {

        if (current == null)
            return -1; // Not found
        if (current == target)
            return depth; // Found the node

        // Search left child
        int leftDepth = getDepthHelper(current.getLeft(), target, depth + 1);
        if (leftDepth != -1)
            return leftDepth; // Found on left

        // Otherwise search right
        return getDepthHelper(current.getRight(), target, depth + 1);
    }

    // Public printing method
    public void print() {
        System.out.println("\n==== BST Print ===== \n");
        print("", root, false); // Start printing from root
    }

    // Pretty-print the BST
    public void print(String prefix, BstNode node, boolean isLeft) {

        if (node != null) {
            // Print the current node with proper tree-branch formatting
            System.out.print(prefix + (isLeft ? "├── " : "└── ") + node.getData() + "\n");

            // Recurse left with updated prefix
            print(prefix + (isLeft ? "│   " : "    "), node.getLeft(), true);

            // Recurse right
            print(prefix + (isLeft ? "│   " : "    "), node.getRight(), false);
        }
    }

}
