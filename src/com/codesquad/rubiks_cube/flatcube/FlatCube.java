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
        push(CubeCommand.getInstanceBy(command));

        return this;
    }

    private void push(CubeCommand cubeCommand) {
        Deque<String> deque = new ArrayDeque<>();

        for (int i = 0; i < CUBE_SIZE; i++) {
            deque.offerLast(blocks[(i + cubeCommand.getStartPosition()) % blocks.length]);
        }

        Direction direction = cubeCommand.getDirection();
        deque = new ArrayDeque<>(direction.push(deque));

        for (int i = 0; i < CUBE_SIZE; i++) {
            blocks[(i + cubeCommand.getStartPosition()) % blocks.length] = deque.pollFirst();
        }
    }

    /**
     * 일차원으로 늘어트려져있는 큐브를 2차원으로 변경시킨다.
     *
     * @return 아래와 같은 형태가 된다. <br/>
     * [blocks[0]][blocks[1]][blocks[2]]<br/>
     * [blocks[7]][mainBlock][blocks[3]]<br/>
     * [blocks[6]][blocks[5]][blocks[4]]
     */
    public String[][] toArray() {
        String[][] blocks = new String[CUBE_SIZE][CUBE_SIZE];

        // 부호에 따라 음의 방향, 양의 방향
        int direction = 1;
        int row = 0;
        int column = -1;

        int range = CUBE_SIZE;
        for (int i = -1; i < this.blocks.length - 1; direction = -direction) {
            // 가로줄 채우기
            for (int j = 0; j < range; j++) {
                i++;
                column += direction;
                blocks[row][column] = this.blocks[i];
            }
            --range;
            // 세로줄 채우기
            for (int j = 0; j < range; j++) {
                i++;
                row += direction;
                blocks[row][column] = this.blocks[i];
            }
        }

        blocks[CUBE_SIZE / 2][CUBE_SIZE / 2] = mainBlock;

        return blocks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String[][] cube = toArray();

        for (String[] row : cube) {
            for (String column : row) {
                sb.append(column)
                        .append(" ");
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
