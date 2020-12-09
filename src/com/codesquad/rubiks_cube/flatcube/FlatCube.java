package com.codesquad.rubiks_cube.flatcube;

import com.codesquad.rubiks_cube.wordpusher.Direction;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlatCube {
    private static final int CUBE_SIZE = 3;
    /**
     * 큐브를 달팽이집 형식으로 표현
     * [blocks[0]][blocks[1]][blocks[2]]
     * [blocks[7]][mainBlock][blocks[3]]
     * [blocks[6]][blocks[5]][blocks[4]]
     */
    private final String[] blocks;
    private final String mainBlock;

    public FlatCube(String[] blocks, String mainBlock) {
        this.blocks = blocks;
        this.mainBlock = mainBlock;
    }

    public FlatCube push(String command) {
        int startPoint;

        switch (command) {
            case "U":
                startPoint = 0;

                push(startPoint, Direction.LEFT);

                break;

            case "U'":
                startPoint = 0;

                push(startPoint, Direction.RIGHT);

                break;

            case "R":
                startPoint = 2;

                push(startPoint, Direction.LEFT);

                break;

            case "R'":
                startPoint = 2;

                push(startPoint, Direction.RIGHT);

                break;

            case "B":
                startPoint = 4;

                push(startPoint, Direction.LEFT);

                break;

            case "B'":
                startPoint = 4;

                push(startPoint, Direction.RIGHT);

                break;

            case "L":
                startPoint = 6;

                push(startPoint, Direction.LEFT);

                break;

            case "L'":
                startPoint = 6;

                push(startPoint, Direction.RIGHT);

                break;
            default:
                break;
        }


        return this;
    }

    private void push(int startPoint, Direction direction) {
        Deque<String> deque = new ArrayDeque<>();

        for (int i = 0; i < CUBE_SIZE; i++) {
            deque.offerLast(blocks[(i + startPoint) % blocks.length]);
        }

        if (direction == Direction.LEFT) {
            deque.offerLast(deque.pollFirst());
        } else {
            deque.offerFirst(deque.pollLast());
        }


        for (int i = 0; i < CUBE_SIZE; i++) {
            blocks[(i + startPoint) % blocks.length] = deque.pollFirst();
        }
    }

    public String[][] toArray() {
        String[][] blocks = new String[CUBE_SIZE][CUBE_SIZE];

        // 부호에 따라 음의 방향, 양의 방향
        int direction = 1;
        int row = 0;
        int column = -1;

        int range = CUBE_SIZE;
        for (int i = -1; i < this.blocks.length - 1; direction = -direction) {
            for (int j = 0; j < range; j++) {
                i++;
                column += direction;
                blocks[row][column] = this.blocks[i];
            }
            --range;
            for (int j = 0; j < range; j++) {
                i++;
                row += direction;
                blocks[row][column] = this.blocks[i];
            }
        }

        blocks[CUBE_SIZE / 2][CUBE_SIZE / 2] = mainBlock;

        return blocks;
    }
}
