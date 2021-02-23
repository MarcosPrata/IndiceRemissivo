package com.uece.binary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> keyWords = new List<>();
        List<Word> text = new List<>();
        BinaryTree<Word> index = new BinaryTree<>();

        int function = 0;
        while (function != 5) {
            System.out.println("O que você deseja fazer?");
            System.out.println("1 - Inserir palavras chave");
            System.out.println("2 - Inserir texto");
            System.out.println("3 - Exibir índice remissivo");
            System.out.println("4 - Filtrar indice remissivo");
            System.out.println("5 - Sair");
            function = in.nextInt();
            if ((function < 1) || (function > 5)) {
                System.out.println("Valor incorreto!");
            }
            if (function == 1) {
                System.out.println("Digite as palavras ch/home/marcosprata/Documentos/uece/ED1/book.txtaves: ");
                Scanner in2 = new Scanner(System.in);
                String keyWordsString = in2.nextLine();
                System.out.println();

                keyWords = new List<>();
                String[] words = keyWordsString.split(" ");
                for (int i = 0; i < words.length; i++) {
                    keyWords.insert(words[i]);
                }
            }
            if (function == 2) {
                text = readTextFile();
            }
            if (function == 3) {
                for (int i = 0; i < text.size; i++) {
                    Word word = text.get(i);
                    boolean wordIsKeyWord = keyWords.indexOf(word.getValue()) != -1;
                    if (wordIsKeyWord) index.insert(text.get(i));
                }
                printIndex(index);
            }
            if (function == 4) {
                System.out.println("Digite as palavras que deseja buscar no indice: ");
                Scanner in2 = new Scanner(System.in);
                String keyWordsString = in2.nextLine();
                System.out.println();

                List<String> filterKeyWords = new List<>();
                String[] words = keyWordsString.split(" ");
                for (int i = 0; i < words.length; i++) {
                    filterKeyWords.insert(words[i]);
                }

                BinaryTree<Word> filteredIndex = new BinaryTree<>();
                List<Word> indexElements = new List<>();
                index.traverseInOrder(index.root, indexElements);
                for (int i = indexElements.size - 1; i >= 0; i--) {
                    if (filterKeyWords.indexOf(indexElements.get(i).getValue()) != -1) {
                        filteredIndex.insert(indexElements.get(i));
                    }
                }
                printIndex(filteredIndex);
            }
        }
    }

    static void printIndex(BinaryTree<Word> binaryIndex) {
        System.out.println("Indice remissivo -------------------");
        List<Word> wordList = new List<>();
        binaryIndex.traverseInOrder(binaryIndex.root, wordList);
        for (int word = wordList.size - 1; word >= 0; word--) {
            System.out.print(wordList.get(word).getValue());
            System.out.print(" - ");
            for (int line = 0; line < wordList.get(word).getLines().size; line++) {
                System.out.print(wordList.get(word).getLines().get(line) + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------\n");
    }

    static List<Word> readTextFile() {
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
                    text.insert(new Word(lineCount, words[i]));
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