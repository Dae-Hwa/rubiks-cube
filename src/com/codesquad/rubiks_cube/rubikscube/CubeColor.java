package com.codesquad.rubiks_cube.rubikscube;

public enum CubeColor {
    Black("B"),
    Red("R"),
    White("W"),
    Orange("O"),
    Green("G"),
    Yellow("Y"),
    Cyan("C");

    public final String VALUE;

    CubeColor(String VALUE) {
        this.VALUE = VALUE;
    }
}
