package com.uece.hash;

import java.util.Objects;

public class Word implements Identifiable, Comparable {
    private int line;
    private String value;

    public Word(String value) {
        this.value = value;
    }

    public Word(int line, String value) {
        this.line = line;
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getKey() {
        int key = 0;
        for (int i = 0; i < value.length(); i++) {
            int letter = (int) value.charAt(i);
            int exponent = value.length() - (i + 1);
            key = key + (letter * (int) (Math.pow(31, exponent)));
        }
        return key < 0 ? key * -1 : key;
    }

    @Override
    public int getComparable() {
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        return value.compareToIgnoreCase(((Word) o).getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return line == word.line && Objects.equals(value, word.value);
    }

    @Override
    public String toString() {
        return "Word{" +
                "line=" + line +
                ", value='" + value + '\'' +
                '}';
    }
}
