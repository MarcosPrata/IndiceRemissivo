package com.uece.binary;

public class Node<E extends Comparable> {
    E info;
    Node left;
    Node right;

    public Node() {
    }

    Node(E element) {
        this.info = element;
        right = null;
        left = null;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
