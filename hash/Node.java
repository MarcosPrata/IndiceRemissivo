package com.uece.hash;

public class Node<E extends Comparable> {
    E info;
    Node next;

    public Node() {
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
