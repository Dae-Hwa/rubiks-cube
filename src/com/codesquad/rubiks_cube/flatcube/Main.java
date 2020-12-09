package com.codesquad.rubiks_cube.flatcube;

public class Main {
    public static void main(String[] args) {
        String[] blocks = new String[]{"R", "R", "W", "W", "B", "B", "G", "G"};
        String mainBlock = "C";
        FlatCube flatCube = new FlatCube(blocks, mainBlock);

        System.out.println(flatCube.toString());
        System.out.print("CUBE> ");
    }
}
