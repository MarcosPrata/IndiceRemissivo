package com.uece.binary;

public class BinaryTree<E extends Identifiable & Comparable> {
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

    public void traverseInOrder(Node node, List<E> list) {
        if (node != null) {
            traverseInOrder(node.getLeft(), list);
            list.insert((E) node.getInfo());
            traverseInOrder(node.getRight(), list);
        }
    }

    public void traversePreOrder(Node node, List<E> list) {
        if (node != null) {
            list.insert((E) node.getInfo());
            traversePreOrder(node.getLeft(), list);
            traversePreOrder(node.getRight(), list);
        }
    }

    public void traversePostOrder(Node node, List<E> list) {
        if (node != null) {
            traversePostOrder(node.getLeft(), list);
            traversePostOrder(node.getRight(), list);
            list.insert((E) node.getInfo());
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node addRecursive(Node current, E value) {
        if (current == null) {
            return new Node(value);
        }
        if (((E) value).compareTo((E) current.getInfo()) < 0) {
            current.setLeft(addRecursive(current.getLeft(), value));
        } else if (((E) value).compareTo((E) current.getInfo()) > 0) {
            current.setRight(addRecursive(current.getRight(), value));
        } else {
            ((Identifiable) current.getInfo()).insertLine(value.getLine());
            return current;
        }

        return current;
    }

    private boolean containsNodeRecursive(Node current, E value) {
        if (current == null) {
            return false;
        }
        if (((E) value).equals((E) current.getInfo())) {
            return true;
        }
        return ((E) value).compareTo((E) current.getInfo()) < 0
                ? containsNodeRecursive(current.getLeft(), value)
                : containsNodeRecursive(current.getRight(), value);
    }

    private Node deleteRecursive(Node current, E value) {
        if (current == null) {
            return null;
        }

        if (((E) value).equals((E) current.getInfo())) {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }
            if (current.getRight() == null) {
                return current.getLeft();
            }

            if (current.getLeft() == null) {
                return current.getRight();
            }
        }
        if (((E) value).compareTo((E) current.getInfo()) < 0) {
            current.setLeft(deleteRecursive(current.getLeft(), value));
            return current;
        }
        current.setRight(deleteRecursive(current.getRight(), value));
        return current;
    }

    private E findSmallestValue(Node root) {
        return root.getLeft() == null ? (E) root.getInfo() : findSmallestValue(root.getLeft());
    }
}