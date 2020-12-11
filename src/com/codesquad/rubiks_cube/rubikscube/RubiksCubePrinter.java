package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCubePrinter;

public class RubiksCubePrinter {
    private static final String SPACE_TOP_AND_BOTTOM = "                ";

    public static void printRubiksCube(RubiksCubeDTO rubiksCubeDTO) {
        FlatCubePrinter.printFlatCube(rubiksCubeDTO.getTop(), SPACE_TOP_AND_BOTTOM, "");
        System.out.println();
        FlatCubePrinter.printFlatCubes(rubiksCubeDTO.getMiddles());
        FlatCubePrinter.printFlatCube(rubiksCubeDTO.getBottom(), SPACE_TOP_AND_BOTTOM, "");
        System.out.println();
    }

    public static void printEndMessage(int rotateCount) {
        System.out.println("조작갯수: " + rotateCount);
        System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
    }
}
