package com.codesquad.rubiks_cube.wordpusher;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class WordPusher {
    private Deque<String> words;
    private int repeatCount;
    private Direction direction;

    private WordPusher(Deque<String> words, int repeatCount, Direction direction) {
        this.words = words;
        this.repeatCount = repeatCount;
        this.direction = direction;
    }

    public static WordPusher create(String input) {
        String[] inputs = input.split(" ");

        List<String> words = Arrays.asList(inputs[0].split(""));
        int repeatCount = Integer.parseInt(inputs[1]);
        Direction direction = Direction.getInstanceBy(inputs[2], repeatCount);

        return new WordPusher(
                new ArrayDeque<>(words),
                Math.abs(repeatCount), // 부호에 따라 direction 결정되어 있으므로 절대값 사용
                direction
        );
    }

    public WordPusher push() {
        if (direction.equals(Direction.LEFT)) {
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