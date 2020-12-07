package com.codesquad.rubiks_cube.wordpusher;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class WordPusher {
    private static final String DIRECTION_LEFT = "L";
    private static final String DIRECTION_RIGHT = "R";

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

        List<String> words = Arrays.asList(inputs[0].split(""));
        int repeatCount = Integer.parseInt(inputs[1]);
        String direction = inputs[2].toUpperCase();

        if (repeatCount < 0) {
            repeatCount = -repeatCount;
            direction = getReversedDirection(direction);
        }

        return new WordPusher(
                new ArrayDeque<>(words),
                repeatCount,
                direction
        );
    }

    private static String getReversedDirection(String direction) {
        if (direction.equals(DIRECTION_LEFT)) {
            return DIRECTION_RIGHT;
        }
        return DIRECTION_LEFT;
    }


    public WordPusher push() {
        if (direction.toUpperCase().equals(DIRECTION_LEFT)) {
            pushLeft(repeatCount);
            return this;
        }

        pushRight(repeatCount);
        return this;
    }

    private void pushLeft(int repeatCount) {
        for (int i = 0; i < repeatCount; i++) {
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