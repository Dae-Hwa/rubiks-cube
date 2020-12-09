package com.codesquad.rubiks_cube.flatcube;

public class FlatCubePrinter {
    public static void printFlatCube(FlatCube flatCube) {
        System.out.print(flatCube.toString());
    }

    public static void printPrompt() {
        System.out.println();
        System.out.print("CUBE> ");
    }

    public static void printCommand(String command) {
        System.out.println();
        System.out.println(command);
    }

    public static void printEndMessage() {
        System.out.println("Bye~");
    }
}
