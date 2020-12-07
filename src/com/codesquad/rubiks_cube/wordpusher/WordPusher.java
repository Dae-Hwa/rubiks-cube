package com.codesquad.rubiks_cube.wordpusher;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class WordPusher {
    private Deque<String> words;
    private int repeatCount;
    private String direction;

    private WordPusher(Deque<String> words, int repeatCount, String direction) {
        this.words = words;
        this.repeatCount = repeatCount;
        this.direction = direction;
    }

    public static WordPusher create(String input) {
        String[] inputs = input.split(" ");
        return new WordPusher(
                new ArrayDeque<>(Arrays.asList(inputs[0].split(""))),
                Integer.parseInt(inputs[1]),
                inputs[2]
        );
    }

    public WordPusher push() {
        if (direction.toUpperCase().equals("L")) {
            repeatCount = -repeatCount;
        }

        pushLeft(repeatCount);
        pushRight(repeatCount);

        return this;
    }

    private void pushLeft(int repeatCount) {
        for (int i = 0; repeatCount < i; i--) {
            words.offerLast(words.pollFirst());
        }
    }

    private void pushRight(int repeatCount) {
        for (int i = 0; i < repeatCount; i++) {
            words.offerFirst(words.pollLast());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String s : words) {
            sb.append(s);
        }

        return sb.toString();
    }
}