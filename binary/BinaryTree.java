package com.uece.binary;

public class BinaryTree<E extends Comparable> {
    Node root;

    BinaryTree() {
    }

    public void insert(E value) {
        root = addRecursive(root, value);
    }

    public boolean containsNode(E value) {
        return containsNodeRecursive(root, value);
    }

    public void delete(E value) {
        root = deleteRecursive(root, value);
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.info.toString());
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.info.toString());
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.info.toString());
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node addRecursive(Node current, E value) {
        if (current == null) {
            return new Node(value);
        }

        if (((E) value).compareTo((E) current.info) < 0) {
            current.left = addRecursive(current.left, value);
        } else if (((E) value).compareTo((E) current.info) > 0) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }

        return current;
    }

    private boolean containsNodeRecursive(Node current, E value) {
        if (current == null) {
            return false;
        }
        if (((E) value).equals((E) current.info)) {
            return true;
        }
        return ((E) value).compareTo((E) current.info) < 0
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    private Node deleteRecursive(Node current, E value) {
        if (current == null) {
            return null;
        }

        if (((E) value).equals((E) current.info)) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }
        }
        if (((E) value).compareTo((E) current.info) < 0) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private E findSmallestValue(Node root) {
        return root.left == null ? (E) root.info : findSmallestValue(root.left);
    }
}