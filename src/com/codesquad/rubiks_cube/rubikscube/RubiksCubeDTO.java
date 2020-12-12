package com.codesquad.rubiks_cube.rubikscube;

public class RubiksCubeDTO {
    private RubiksCubeLayersDTO rubiksCubeLayersDTO;
    private int rotateCount;

    public RubiksCubeDTO(RubiksCubeLayersDTO rubiksCubeLayersDTO, int rotateCount) {
        this.rubiksCubeLayersDTO = rubiksCubeLayersDTO;
        this.rotateCount = rotateCount;
    }

    public RubiksCubeLayersDTO getRubiksCubeLayersDTO() {
        return rubiksCubeLayersDTO;
    }

    public int getRotateCount() {
        return rotateCount;
    }
}
