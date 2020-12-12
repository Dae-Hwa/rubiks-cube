package com.codesquad.rubiks_cube.flatcube;

import java.util.Arrays;

public class FlatCubeDTO {
    private final String[] blocks;
    private final String mainBlock;

    public FlatCubeDTO(String[] blocks, String mainBlock) {
        this.blocks = blocks;
        this.mainBlock = mainBlock;
    }

    public String[] getBlocks() {
        return blocks.clone();
    }

    public String getMainBlock() {
        return mainBlock;
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
        String[][] blocks = new String[FlatCube.CUBE_SIZE][FlatCube.CUBE_SIZE];

        // 부호에 따라 음의 방향, 양의 방향
        int direction = 1;
        int row = 0;
        int column = -1;

        int range = FlatCube.CUBE_SIZE;
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

        blocks[FlatCube.CUBE_SIZE / 2][FlatCube.CUBE_SIZE / 2] = mainBlock;

        return blocks;
    }

    @Override
    public String toString() {
        return "FlatCubeDTO{" +
                "blocks=" + Arrays.toString(blocks) +
                ", mainBlock='" + mainBlock + '\'' +
                '}';
    }
}
