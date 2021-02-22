package com.uece.hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> keyWords = new List<>();

        System.out.println("Digite as key words: ");
        Scanner in = new Scanner(System.in);
        String keyWordsString = in.nextLine();
        System.out.println();

        String[] words = keyWordsString.split(" ");
        for (int i = 0; i < words.length; i++) {
            keyWords.insert(words[i]);
        }

        List<Word> text = readTextFileKeyWords("book.txt", keyWords);

        Hash<Word> hash = new Hash<>();

        for (int i = 0; i < text.size; i++) {
            hash.insert(text.get(i));
        }

        printIndex(hash);

    }

    static void printIndex(Hash<Word> hash) {
        System.out.println("Indice remissivo -------------------");
        List<List<Word>> index = hash.getAll(SortDirection.ASC);

        for (int word = 0; word < index.size; word++) {
            System.out.print(index.get(word).get(0).value);
            System.out.print(" - ");
            for (int line = 0; line < index.get(word).size; line++) {
                System.out.print(index.get(word).get(line).line + " ");
            }
            System.out.println();
        }
    }

    static List<Word> readTextFileKeyWords(String fileName, List<String> keyWords) {
        List<Word> text = new List<>();
        try {
            System.out.println("Digite o caminho do arquivo .txt: ");
            Scanner sc1 = new Scanner(System.in);
            File file = new File(sc1.next());
            System.out.println();
            Scanner reader = new Scanner(file);
            int lineCount = 1;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    boolean wordIsKeyWord = keyWords.indexOf(words[i]) != -1;
                    if (wordIsKeyWord) text.insert(new Word(lineCount, words[i]));
                }
                lineCount++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return text;
    }
}