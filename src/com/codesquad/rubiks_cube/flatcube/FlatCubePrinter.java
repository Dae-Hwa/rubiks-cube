package com.codesquad.rubiks_cube.flatcube;

import java.util.List;

public class FlatCubePrinter {
    private static String getPrintableFlatCube(FlatCubeDTO flatCubeDTO, String prefix, String postfix) {
        StringBuilder sb = new StringBuilder();

        String[][] cube = flatCubeDTO.toArray();

        for (String[] row : cube) {
            sb.append(prefix);
            for (String column : row) {
                sb.append(column)
                        .append(" ");
            }
            sb.append(postfix)
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }

    public static void printFlatCube(FlatCubeDTO flatCubeDTO) {
        printFlatCube(flatCubeDTO, "", "");
    }

    public static void printFlatCube(FlatCubeDTO flatCubeDTO, String prefix, String postfix) {
        System.out.print(getPrintableFlatCube(flatCubeDTO, prefix, postfix));
    }

    public static void printFlatCubes(List<FlatCubeDTO> flatCubeDTOs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3 * 4; j++) {
                String current = flatCubeDTOs.get(j / 3).toArray()[i][j % 3];
                sb.append(current)
                        .append(" ");
                if (j % 3 == 2) {
                    sb.append("     ");
                }
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb.toString());
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
