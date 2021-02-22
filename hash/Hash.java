package com.uece.hash;

public class Hash<E extends Identifiable & Comparable> {
    private static final int INIT_CAPACITY = 1024;
    private int size;
    private int capacity;
    private Object[] array;

    Hash() {
        this(INIT_CAPACITY);
    }

    Hash(int capacity) {
        size = 0;
        this.capacity = capacity;
        array = new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = null;
        }
    }

    public void insert(E element) {
        if (size > capacity / 2) resize(capacity * 2);

        int key = element.getKey() % capacity;
        if (array[key] == null) {
            array[key] = element;
        } else {
            boolean notInserted = true;
            for (int i = (element.getKey() + 1) % capacity; notInserted; i++) {
                if (array[i] == null) {
                    array[i] = element;
                    notInserted = false;
                }
            }
        }
        size++;
    }

    public E search(E element) {
        int key = element.getKey() % capacity;
        if (array[key] == null) {
            return null;
        } else {
            for (int i = key; array[i] == null; i = key + 1) {
                if (((E) array[i]).compareTo(element) == 0) return (E) array[i];
            }
        }
        return null;
    }

    public List<List<E>> getAll(SortDirection direction) {
        List<List<E>> values = new List<>();
        List<Integer> keys = getKeys();
        for (int i = 0; i < keys.size; i++) {
            List<E> value = new List<>();
            int key = ((E) array[keys.get(i)]).getKey();
            for (int j = keys.get(i); array[j] != null && (((E) array[j]).getKey() == key); j++) {
                value.insert(((E) array[j]));
            }
            values.insert(value);
        }
        Object[] sortedArray = values.asArray();

        //Bubble sort
        for (int i = 0; i < sortedArray.length; i++) {
            for (int j = 0; j < sortedArray.length - 1; j++) {
                if (((List<E>) sortedArray[j]).get(0).compareTo(((List<E>) sortedArray[j + 1]).get(0)) > 0) {
                    List<E> aux = (List<E>) sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = aux;
                }
            }
        }
        return new List<>(sortedArray, direction);
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < capacity; i++) {
            if (array[i % capacity] != null) {
                out += "" + i + ": ";
                out += array[i % capacity] + "\n";
            }
        }
        return out;
    }

    private void resize(int capacity) {
        Hash<E> temp = new Hash<E>(capacity);
        for (int i = 0; i < this.capacity; i++) {
            if (array[i] != null) {
                temp.insert((E) array[i]);
            }
        }
        this.capacity = temp.capacity;
        this.array = temp.array;
    }

    private List<Integer> getKeys() {
        List<Integer> keys = new List<>();
        for (int i = 0; i < this.capacity; i++) {
            if (array[i] != null) {
                int key = ((E) array[i]).getKey() % capacity;
                if (keys.indexOf(key) == -1) keys.insert(key);
            }
        }
        return keys;
    }
}