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

        if (inputs.length != 3) {
            throw new IllegalArgumentException("올바르지 않은 입력형식입니다. 입력 문자열 : " + input);
        }

        List<String> words = Arrays.asList(inputs[0].split(""));
        int repeatCount = Integer.parseInt(inputs[1]);

        if (100 <= repeatCount || repeatCount < -100) {
            throw new IllegalArgumentException("숫자는 -100이상 100미만의 값이어야 합니다. 입력 숫자 : " + repeatCount);
        }
        
        Direction direction = Direction.getInstanceBy(inputs[2], repeatCount);

        return new WordPusher(
                new ArrayDeque<>(words),
                Math.abs(repeatCount), // 부호에 따라 direction 결정되기 때문에 절댓값 사용
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