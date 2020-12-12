package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCubePrinter;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class RubiksCubePrinter {
    private static final String SPACE_TOP_AND_BOTTOM = "                ";

    public static void printRubiksCube(RubiksCubeLayersDTO rubiksCubeLayersDTO) {
        FlatCubePrinter.printFlatCube(rubiksCubeLayersDTO.getTop(), SPACE_TOP_AND_BOTTOM, "");
        System.out.println();
        FlatCubePrinter.printFlatCubes(rubiksCubeLayersDTO.getMiddles());
        FlatCubePrinter.printFlatCube(rubiksCubeLayersDTO.getBottom(), SPACE_TOP_AND_BOTTOM, "");
        System.out.println();
    }

    public static void printEndMessage(int rotateCount, String elapsedTime) {


        System.out.println("경과시간: " + elapsedTime);
        System.out.println("조작갯수: " + rotateCount);
        System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
    }
}
