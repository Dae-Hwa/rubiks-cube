package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCube;

import java.util.*;

public class RubiksCube {
    private FlatCube top;
    private FlatCube bottom;
    private List<FlatCube> middles;

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

        linkedCubes.setTop(middles.get(1), 4);
        linkedCubes.setLeft(middles.get(2), 6);
        linkedCubes.setRight(middles.get(0), 2);
        linkedCubes.setBottom(middles.get(3), 0);

        top.setLinkedCubes(linkedCubes);
    }

    private void linkBottom() {
        LinkedCubes linkedCubes = new LinkedCubes();

        linkedCubes.setTop(middles.get(0), 0);
        linkedCubes.setLeft(middles.get(3), 2);
        linkedCubes.setRight(middles.get(1), 6);
        linkedCubes.setBottom(middles.get(2), 4);

        bottom.setLinkedCubes(linkedCubes);
    }

    private void linkMiddle() {
        int n = 4;

        for (int i = 0; i < n; i++) {
            middles.get(i)
                    .getLinkedCubes()
                    .setLeft(middles.get((i + n - 1) % n), 0)
                    .setRight(middles.get((i + 1) % n), 0)
                    .setTop(top, 6 - i * 2)
                    .setBottom(bottom, i * 2);
        }
    }

    public RubiksCube rotate(String command) {
        switch (command) {
            case "F":
                middles.get(1).rotateClockWise();
                break;

            case "F'":
                middles.get(1).rotateCounterClockWise();
                break;

            case "R":
                middles.get(2).rotateClockWise();
                break;

            case "R'":
                middles.get(2).rotateCounterClockWise();
                break;

            case "U":
                top.rotateClockWise();
                break;

            case "U'":
                top.rotateCounterClockWise();
                break;

            case "B":
                middles.get(3).rotateClockWise();
                break;

            case "B'":
                middles.get(3).rotateCounterClockWise();
                break;

            case "L":
                middles.get(0).rotateClockWise();
                break;

            case "L'":
                middles.get(0).rotateCounterClockWise();
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

        return new RubiksCubeDTO(top.toDTO(), bottom.toDTO(), middles);
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
