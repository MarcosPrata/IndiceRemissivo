package com.uece.binary;

import java.util.Objects;

class List<E extends Comparable> implements Comparable {
    Node<E> first;
    int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(E info) {
        Node<E> node = new Node<E>();
        node.info = info;
        node.right = first;
        first = node;
        size++;
    }

    public int indexOf(E element) {
        int index = 0;
        Node node = first;
        while (node != null) {
            if (node.info.equals(element)) {
                return index;
            }
            node = node.right;
            index++;
        }
        return -1;
    }

    public E search(E element) {
        return get(indexOf(element));
    }

    public E get(int index) {
        Objects.checkIndex(index, this.size);
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.right;
        }
        return (E) node.info;
    }

    public String toString() {
        StringBuilder out = new StringBuilder("[ ");
        Node node = first;
        while (node != null) {
            out.append(node.info.toString()).append(" ");
            node = node.right;
        }
        out.append("]");
        return out.toString();
    }

    public List<E> sorted() {
        Object[] array = new Object[size];
        Node node = first;
        int i = 0;
        while (node != null) {
            array[i] = node.info;
            node = node.right;
            i++;
        }

        for (i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (((E) array[j]).compareTo((E) array[j + 1]) > 0) {
                    Object aux = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = aux;
                }
            }
        }

        List<E> ordered = new List<>();
        for (i = 0; i < array.length; i++) {
            ordered.insert((E) array[i]);
        }

        return ordered;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof List)) {
            return -1;
        } else {
            if (((List) o).size == size) return 0;
            return (((List) o).size > size) ? 1 : -1;
        }
    }
}
