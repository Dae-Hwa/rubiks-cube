package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCubePrinter;

public class RubiksCubePrinter {
    private static final String SPACE_TOP_AND_BOTTOM = "                ";

    public static void printRubiksCube(RubiksCubeDTO rubiksCubeDTO ) {
        FlatCubePrinter.printFlatCube(rubiksCubeDTO.getTop(), SPACE_TOP_AND_BOTTOM, "");
        System.out.println();
        FlatCubePrinter.printFlatCubes(rubiksCubeDTO.getMiddles());
        FlatCubePrinter.printFlatCube(rubiksCubeDTO.getBottom(), SPACE_TOP_AND_BOTTOM, "");
        System.out.println();
    }
}
