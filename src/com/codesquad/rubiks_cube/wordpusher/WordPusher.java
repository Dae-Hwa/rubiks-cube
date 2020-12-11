package com.codesquad.rubiks_cube.wordpusher;

import java.util.ArrayDeque;
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
        for (int i = 0; i < repeatCount; i++) {
            words = new ArrayDeque<>(direction.push(words));
        }

        return this;
    }

    public WordPusherDTO toDTO() {
        return new WordPusherDTO(new ArrayDeque<>(words), repeatCount, direction);
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