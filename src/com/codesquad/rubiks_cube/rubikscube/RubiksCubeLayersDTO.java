package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCubeDTO;

import java.util.List;

public class RubiksCubeLayersDTO {
    private FlatCubeDTO top;
    private FlatCubeDTO bottom;
    private List<FlatCubeDTO> middles;

    public RubiksCubeLayersDTO(FlatCubeDTO top, FlatCubeDTO bottom, List<FlatCubeDTO> middles) {
        this.top = top;
        this.bottom = bottom;
        this.middles = middles;
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
}
