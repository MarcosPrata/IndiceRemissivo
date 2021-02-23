package com.uece.binary;

import java.util.Objects;

class List<E extends Comparable> implements Comparable {
    Node first;
    int size;

    public List() {
    }

    public List(Object[] array, SortDirection direction) {
        for (int i = 0; i < array.length; i++) {
            if (direction.equals(SortDirection.DESC)) {
                this.insert((E) array[i]);
            } else {
                this.insertAtEnd((E) array[i]);
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(E info) {
        Node<E> node = new Node<E>();
        node.setInfo(info);
        node.setRight(first);
        first = node;
        size++;
    }

    public void insertAtEnd(E element) {
        Node node = first;
        Node aux = new Node();
        if (isEmpty()) {
            insert(element);
            return;
        }
        while (node != null) {
            if (node.getRight() == null) {
                aux.setInfo(element);
                aux.setRight(null);
                node.setRight(aux);
                size++;
                return;
            }
            node = node.getRight();
        }
    }

    public void insertAt(int index, E element) {
        Objects.checkIndex(index, this.size + 1);
        Node node = first;
        Node aux = new Node();
        if (index == 0) {
            insert(element);
            return;
        }
        int count = 0;
        while (node != null) {
            if (count == (index - 1)) {
                aux.setInfo(element);
                aux.setRight(node.getRight());
                node.setRight(aux);
                size++;
                return;
            }
            node.setRight(node.getRight());
            count++;
        }
    }

    public void removeAt(int index) {
        Objects.checkIndex(index, this.size + 1);
        Node node = first;
        if (index == 0) {
            first = first.getRight();
            return;
        }
        int count = 0;
        while (node != null) {
            if (count == (index - 1)) {
                node.setRight(node.getRight().getRight());
                return;
            }
            node = node.getRight();
            count++;
        }
    }

    public void remove(E info) {
        Node node = first;
        while (node != null) {
            if (node.getRight().getInfo().equals(info)) {
                node.setRight(node.getRight().getRight());
                break;
            }
            node = node.getRight();
        }
    }

    public int indexOf(E element) {
        int index = 0;
        Node node = first;
        while (node != null) {
            if (node.getInfo().equals(element)) {
                return index;
            }
            node = node.getRight();
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
            node = node.getRight();
        }
        return (E) node.getInfo();
    }

    public String toString() {
        StringBuilder out = new StringBuilder("[ ");
        Node node = first;
        while (node != null) {
            out.append(node.getInfo().toString()).append(" ");
            node = node.getRight();
        }
        out.append("]");
        return out.toString();
    }

    public Object[] asArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = (E) get(i);
        }
        return array;
    }

    public List<E> sorted() {
        Object[] array = new Object[size];
        Node node = first;
        int i = 0;
        while (node != null) {
            array[i] = node.getInfo();
            node = node.getRight();
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
