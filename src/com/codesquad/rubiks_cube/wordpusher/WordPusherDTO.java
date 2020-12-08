package com.codesquad.rubiks_cube.wordpusher;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class WordPusherDTO {
    private Deque<String> words;
    private int repeatCount;
    private Direction direction;

    private WordPusherDTO(Deque<String> words, int repeatCount, Direction direction) {
        this.words = words;
        this.repeatCount = repeatCount;
        this.direction = direction;
    }

    public static WordPusherDTO create(String input) {
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

        return new WordPusherDTO(
                new ArrayDeque<>(words),
                Math.abs(repeatCount), // 부호에 따라 direction 결정되기 때문에 절댓값 사용
                direction
        );
    }

    public Deque<String> getWords() {
        return words;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public Direction getDirection() {
        return direction;
    }
}
