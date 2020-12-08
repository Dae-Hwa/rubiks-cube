package com.codesquad.rubiks_cube.wordpusher;

import java.util.Deque;

public class WordPusher {
    private Deque<String> words;
    private int repeatCount;
    private Direction direction;

    private WordPusher(Deque<String> words, int repeatCount, Direction direction) {
        this.words = words;
        this.repeatCount = repeatCount;
        this.direction = direction;
    }

    public static WordPusher create(WordPusherDTO wordPusherDTO) {
        return new WordPusher(
                wordPusherDTO.getWords(),
                wordPusherDTO.getRepeatCount(),
                wordPusherDTO.getDirection()
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