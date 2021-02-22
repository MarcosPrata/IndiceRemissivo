package com.uece.binary;

import java.util.Objects;

public class Word implements Comparable {
    int line;
    String value;

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
