package com.codesquad.rubiks_cube.wordpusher;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class WordPusher {
    public String push(String input) {
        String[] inputs = input.split(" ");
        String str = inputs[0];
        int time = Integer.parseInt(inputs[1]);
        String direction = inputs[2];

        if (direction.toUpperCase().equals("L")) {
            time *= -1;
        }

        Deque<String> deque = new ArrayDeque<>(Arrays.asList(str.split("")));

        for (int i = 0; time < i; i--) {
            deque.offerLast(deque.pollFirst());
        }

        for (int i = 0; i < time; i++) {
            deque.offerFirst(deque.pollLast());
        }

        StringBuilder sb = new StringBuilder();

        for (String s : deque) {
            sb.append(s);
        }

        return sb.toString();
    }
}