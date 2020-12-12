package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.BlockPosition;
import com.codesquad.rubiks_cube.flatcube.FlatCube;
import com.codesquad.rubiks_cube.flatcube.FlatCubeDTO;

import java.util.*;
import java.util.stream.Collectors;

public class RubiksCube {
    private static final int MIDDLES_LEFT_INDEX = 0;
    private static final int MIDDLES_FRONT_INDEX = 1;
    private static final int MIDDLES_RIGHT_INDEX = 2;
    private static final int MIDDLES_BACK_INDEX = 3;

    private FlatCube top;
    private FlatCube bottom;
    private List<FlatCube> middles;
    private int rotateCount;

    public RubiksCube(FlatCube top, FlatCube bottom, List<FlatCube> middles) {
        this.top = top;
        this.bottom = bottom;
        this.middles = middles;

        linkTop();
        linkBottom();
        linkMiddle();
    }

    public static RubiksCube create() {
        FlatCube top = FlatCube.create("B");

        FlatCube bottom = FlatCube.create("R");

        List<FlatCube> middles = new ArrayList<>();

        middles.add(FlatCube.create("W"));
        middles.add(FlatCube.create("O"));
        middles.add(FlatCube.create("G"));
        middles.add(FlatCube.create("Y"));

        return new RubiksCube(top, bottom, middles);
    }

    private void linkTop() {
        LinkedCubes linkedCubes = new LinkedCubes();

        linkedCubes.setTop(middles.get(MIDDLES_FRONT_INDEX), BlockPosition.BOTTOM_RIGHT.VALUE)
                .setLeft(middles.get(MIDDLES_RIGHT_INDEX), BlockPosition.BOTTOM_LEFT.VALUE)
                .setRight(middles.get(MIDDLES_LEFT_INDEX), BlockPosition.TOP_RIGHT.VALUE)
                .setBottom(middles.get(MIDDLES_BACK_INDEX), BlockPosition.TOP_LEFT.VALUE);

        top.setLinkedCubes(linkedCubes);
    }

    private void linkBottom() {
        LinkedCubes linkedCubes = new LinkedCubes();

        linkedCubes.setTop(middles.get(MIDDLES_LEFT_INDEX), BlockPosition.TOP_LEFT.VALUE)
                .setLeft(middles.get(MIDDLES_BACK_INDEX), BlockPosition.TOP_RIGHT.VALUE)
                .setRight(middles.get(MIDDLES_FRONT_INDEX), BlockPosition.BOTTOM_LEFT.VALUE)
                .setBottom(middles.get(MIDDLES_RIGHT_INDEX), BlockPosition.BOTTOM_RIGHT.VALUE);

        bottom.setLinkedCubes(linkedCubes);
    }

    private void linkMiddle() {
        int n = 4;

        for (int i = 0; i < n; i++) {
            middles.get(i)
                    .getLinkedCubes()
                    .setLeft(middles.get((i + n - 1) % n), BlockPosition.TOP_LEFT.VALUE)
                    .setRight(middles.get((i + 1) % n), BlockPosition.TOP_LEFT.VALUE)
                    .setTop(top, (BlockPosition.TOP_RIGHT.VALUE - i * 2) % FlatCube.BLOCKS_SIZE)
                    .setBottom(bottom, (BlockPosition.BOTTOM_LEFT.VALUE + i * 2) % FlatCube.BLOCKS_SIZE);
        }
    }

    public RubiksCube rotate(String command) {
        rotateCount++;

        switch (command) {
            case "F":
                middles.get(MIDDLES_FRONT_INDEX).rotateClockWise();
                break;

            case "F'":
                middles.get(MIDDLES_FRONT_INDEX).rotateCounterClockWise();
                break;

            case "R":
                middles.get(MIDDLES_RIGHT_INDEX).rotateClockWise();
                break;

            case "R'":
                middles.get(MIDDLES_RIGHT_INDEX).rotateCounterClockWise();
                break;

            case "U":
                top.rotateClockWise();
                break;

            case "U'":
                top.rotateCounterClockWise();
                break;

            case "B":
                middles.get(MIDDLES_BACK_INDEX).rotateClockWise();
                break;

            case "B'":
                middles.get(MIDDLES_BACK_INDEX).rotateCounterClockWise();
                break;

            case "L":
                middles.get(MIDDLES_LEFT_INDEX).rotateClockWise();
                break;

            case "L'":
                middles.get(MIDDLES_LEFT_INDEX).rotateCounterClockWise();
                break;

            case "D":
                bottom.rotateClockWise();
                break;

            case "D'":
                bottom.rotateCounterClockWise();
                break;

            default:
                break;
        }

        return this;
    }

    public RubiksCubeDTO toDTO() {
        List<FlatCubeDTO> middles = this.middles.stream()
                .map(FlatCube::toDTO)
                .collect(Collectors.toList());

        return new RubiksCubeDTO(top.toDTO(), bottom.toDTO(), middles, rotateCount);
    }

    @Override
    public String toString() {
        return "RubiksCube{" +
                "top=" + top +
                ", bottom=" + bottom +
                ", middles=" + middles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RubiksCube that = (RubiksCube) o;
        return Objects.equals(top, that.top) &&
                Objects.equals(bottom, that.bottom) &&
                Objects.equals(middles, that.middles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, bottom, middles);
    }
}
