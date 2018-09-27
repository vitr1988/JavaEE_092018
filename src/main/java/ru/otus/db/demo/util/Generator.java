package ru.otus.db.demo.util;

import java.util.Random;
import java.util.stream.IntStream;

public class Generator {

    public static String generateName(){
        return generateLetter(1).toUpperCase() +  generateLetter(9).toLowerCase();
    }

    public static String generateLetter(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, length).forEach(i -> sb.append(alphabet.charAt(new Random().nextInt(alphabet.length()))));
        return sb.toString();
    }

    public static Long generateDigits(int length){
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, length).forEach(i -> sb.append(new Random().nextInt(10)));
        return Long.valueOf(sb.toString());
    }
}
