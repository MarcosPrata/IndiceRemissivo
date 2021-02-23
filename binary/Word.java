package com.uece.binary;

import java.util.Objects;

public class Word implements Identifiable, Comparable {
    private List<Integer> lines = new List<>();
    private String value;

    public Word(String value) {
        this.value = value;
    }

    public Word(int line, String value) {
        this.lines.insert(line);
        this.value = value;
    }

    public List<Integer> getLines() {
        return lines;
    }

    public void setLines(List<Integer> lines) {
        this.lines = lines;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void insertLine(int i) {
        lines.insert(i);
    }

    @Override
    public int getLine() {
        return lines.get(lines.size - 1);
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
        return lines == word.lines && Objects.equals(value, word.value);
    }

    @Override
    public String toString() {
        return "Word{" +
                "line=" + lines.toString() +
                ", value='" + value + '\'' +
                '}';
    }
}
