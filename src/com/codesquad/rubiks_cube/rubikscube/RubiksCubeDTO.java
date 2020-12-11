package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCubeDTO;

import java.util.List;

public class RubiksCubeDTO {
    private FlatCubeDTO top;
    private FlatCubeDTO bottom;
    private List<FlatCubeDTO> middles;
    private int rotateCount;

    public RubiksCubeDTO(FlatCubeDTO top, FlatCubeDTO bottom, List<FlatCubeDTO> middles, int rotateCount) {
        this.top = top;
        this.bottom = bottom;
        this.middles = middles;
        this.rotateCount = rotateCount;
    }

    public FlatCubeDTO getTop() {
        return top;
    }

    public FlatCubeDTO getBottom() {
        return bottom;
    }

    public List<FlatCubeDTO> getMiddles() {
        return middles;
    }

    public int getRotateCount() {
        return rotateCount;
    }
}
