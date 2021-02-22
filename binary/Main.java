package com.uece.binary;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> keyWords = new List<>();
        keyWords.insert("que");
        keyWords.insert("com");
        keyWords.insert("como");

        List<Word> text = readText("book.txt", keyWords);
        //text = text.sorted();

        BinaryTree<Word> binaryTree = new BinaryTree<>();

        for (int i = 0; i < text.size; i++) {
            binaryTree.insert(text.get(i));
        }

        binaryTree.traverseInOrder(binaryTree.root);

       // printRemissiveIndex(binaryTree);

    }

    static void printRemissiveIndex(BinaryTree<Word> index) {
//        //List<List<Word>> remissiveIndex = index.getAll();
//
//        for (int word = 0; word < remissiveIndex.size; word++) {
//            System.out.print(remissiveIndex.get(word).get(0).value);
//            System.out.print(" - ");
//            for (int line = 0; line < remissiveIndex.get(word).size; line++) {
//                System.out.print(remissiveIndex.get(word).get(line).line + " ");
//            }
//            System.out.println();
//        }
    }

    static List<Word> readText(String fileName, List<String> keyWords) {
        List<Word> text = new List<>();
        try {
            var path = Paths.get("src/com/uece/hash/", fileName);
            Scanner reader = new Scanner(path.toFile());
            int lineCount = 1;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    boolean tem = keyWords.indexOf(words[i]) != -1;
                    if (tem) text.insert(new Word(lineCount, words[i]));
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