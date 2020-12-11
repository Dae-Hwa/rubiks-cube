package com.codesquad.rubiks_cube.flatcube;

public class FlatCubePrinter {
    public static void printFlatCube(FlatCubeDTO flatCubeDTO) {
        StringBuilder sb = new StringBuilder();

        String[][] cube = flatCubeDTO.toArray();

        for (String[] row : cube) {
            for (String column : row) {
                sb.append(column)
                        .append(" ");
            }
            sb.append(System.lineSeparator());
        }

        System.out.print(sb.toString());
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
