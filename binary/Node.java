package com.uece.binary;

public class Node<E extends Comparable> {
    private E info;
    private Node left;
    private Node right;

    public Node() {
    }

    public Node(E info) {
        this.info = info;
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
