package com.codesquad.rubiks_cube.flatcube;

import com.codesquad.rubiks_cube.rubikscube.LinkedCubes;
import com.codesquad.rubiks_cube.wordpusher.Direction;
import com.codesquad.rubiks_cube.wordpusher.WordPusher;
import com.codesquad.rubiks_cube.wordpusher.WordPusherDTO;

import java.util.*;

public class FlatCube {
    public static final int CUBE_SIZE = 3;
    public static final int BLOCKS_SIZE = 8;

    /**
     * 큐브를 달팽이집 형식으로 표현
     * [blocks[0]][blocks[1]][blocks[2]]
     * [blocks[7]][mainBlock][blocks[3]]
     * [blocks[6]][blocks[5]][blocks[4]]
     */
    private final String[] blocks;
    private final String mainBlock;
    private LinkedCubes linkedCubes = new LinkedCubes();

    public FlatCube(String[] blocks, String mainBlock) {
        this.blocks = blocks;
        this.mainBlock = mainBlock;
    }

    public static FlatCube create(String color) {
        String[] blocks = new String[Double.valueOf(Math.pow(CUBE_SIZE, 2)).intValue() - 1];
        Arrays.fill(blocks, color);
        return new FlatCube(blocks, color);
    }

    public LinkedCubes getLinkedCubes() {
        return linkedCubes;
    }

    public void setLinkedCubes(LinkedCubes linkedCubes) {
        this.linkedCubes = linkedCubes;
    }

    public FlatCube rotateClockWise() {
        linkedCubes.rotateClockWise();

        Deque<String> pushedBlocks = WordPusher.create(new WordPusherDTO(
                new ArrayDeque<>(Arrays.asList(blocks)),
                CUBE_SIZE - 1,
                Direction.RIGHT
        )).push()
                .toDTO()
                .getWords();

        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = pushedBlocks.pollFirst();
        }

        return this;
    }

    public FlatCube rotateCounterClockWise() {
        linkedCubes.rotateCounterClockWise();

        Deque<String> pushedBlocks = WordPusher.create(new WordPusherDTO(
                new ArrayDeque<>(Arrays.asList(blocks)),
                CUBE_SIZE - 1,
                Direction.LEFT
        )).push()
                .toDTO()
                .getWords();

        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = pushedBlocks.pollFirst();
        }

        return this;
    }

    public String[] getBlocksStartAt(int index) {
        String[] blocks = new String[CUBE_SIZE];

        for (int i = 0; i < CUBE_SIZE; i++) {
            blocks[i] = this.blocks[(i + index) % this.blocks.length];
        }

        return blocks;
    }

    public FlatCube setBlocksStartAt(int index, String[] blocks) {
        for (int i = 0; i < CUBE_SIZE; i++) {
            this.blocks[(i + index) % this.blocks.length] = blocks[i];
        }

        return this;
    }

    public FlatCube push(String command) {
        push(CubeCommand.getInstanceBy(command));

        linkedCubes.getLeft();

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

    public FlatCubeDTO toDTO() {
        return new FlatCubeDTO(blocks.clone(), mainBlock);
    }

    @Override
    public String toString() {
        return "FlatCube{" +
                "blocks=" + Arrays.toString(blocks) +
                ", mainBlock='" + mainBlock + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatCube flatCube = (FlatCube) o;
        return Arrays.equals(blocks, flatCube.blocks) &&
                Objects.equals(mainBlock, flatCube.mainBlock);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mainBlock);
        result = 31 * result + Arrays.hashCode(blocks);
        return result;
    }
}
