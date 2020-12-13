package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.BlockPosition;
import com.codesquad.rubiks_cube.flatcube.FlatCube;
import com.codesquad.rubiks_cube.flatcube.FlatCubeDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RubiksCubeLayers {
    private static final int MIDDLES_LEFT_INDEX = 0;
    private static final int MIDDLES_FRONT_INDEX = 1;
    private static final int MIDDLES_RIGHT_INDEX = 2;
    private static final int MIDDLES_BACK_INDEX = 3;

    private FlatCube top;
    private FlatCube bottom;
    private List<FlatCube> middles;
    private boolean canRotate = true;

    public RubiksCubeLayers(FlatCube top, FlatCube bottom, List<FlatCube> middles) {
        this.top = top;
        this.bottom = bottom;
        this.middles = middles;

        linkTop();
        linkBottom();
        linkMiddle();
    }

    public static RubiksCubeLayers create(RubiksCubeLayersDTO rubiksCubeLayersDTO) {
        return new RubiksCubeLayers(
                FlatCube.create(rubiksCubeLayersDTO.getTop()),
                FlatCube.create(rubiksCubeLayersDTO.getBottom()),
                rubiksCubeLayersDTO.getMiddles().stream()
                        .map(FlatCube::create)
                        .collect(Collectors.toList())
        );
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

    public boolean isCanRotate() {
        return canRotate;
    }

    public void setCanRotate(boolean canRotate) {
        this.canRotate = canRotate;
    }

    public FlatCube getTop() {
        return top;
    }

    public FlatCube getBottom() {
        return bottom;
    }

    public FlatCube getLeft() {
        return middles.get(MIDDLES_LEFT_INDEX);
    }

    public FlatCube getFront() {
        return middles.get(MIDDLES_FRONT_INDEX);
    }

    public FlatCube getRight() {
        return middles.get(MIDDLES_RIGHT_INDEX);
    }

    public FlatCube getBack() {
        return middles.get(MIDDLES_BACK_INDEX);
    }

    public RubiksCubeLayersDTO toDTO() {
        List<FlatCubeDTO> middles = this.middles.stream()
                .map(FlatCube::toDTO)
                .collect(Collectors.toList());

        return new RubiksCubeLayersDTO(top.toDTO(), bottom.toDTO(), middles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RubiksCubeLayers that = (RubiksCubeLayers) o;
        return Objects.equals(top, that.top) && Objects.equals(bottom, that.bottom) && Objects.equals(middles, that.middles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, bottom, middles);
    }
}
