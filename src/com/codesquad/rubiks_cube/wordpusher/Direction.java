package com.codesquad.rubiks_cube.wordpusher;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public enum Direction {
    LEFT {
        @Override
        public <T> Collection<T> push(Collection<T> params) {
            Deque<T> deque = new ArrayDeque<>(params);

            deque.offerLast(deque.pollFirst());

            return deque;
        }
    }, RIGHT {
        @Override
        public <T> Collection<T> push(Collection<T> params) {
            Deque<T> deque = new ArrayDeque<>(params);

            deque.offerFirst(deque.pollLast());

            return deque;
        }
    };

    public static Direction getInstanceBy(String direction, int repeatCount) {
        if (repeatCount < 0) {
            return getInstanceBy(direction).getReversed();
        }

        return getInstanceBy(direction);
    }

    private static Direction getInstanceBy(String direction) {
        if (direction.equalsIgnoreCase("L")) {
            return LEFT;
        }

        return RIGHT;
    }

    public abstract <T> Collection<T> push(Collection<T> params);

    private Direction getReversed() {
        if (this.equals(LEFT)) {
            return RIGHT;
        }

        return LEFT;
    }
}
