package com.codesquad.rubiks_cube.flatcube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        String[] blocks = new String[]{"R", "R", "W", "W", "B", "B", "G", "G"};
        String mainBlock = "C";
        FlatCube flatCube = new FlatCube(blocks, mainBlock);

        FlatCubePrinter.printFlatCube(flatCube);

        boolean isEnd = false;

        while (!isEnd) {
            FlatCubePrinter.printPrompt();

            isEnd = executeCommands(flatCube, getInput());
        }

        FlatCubePrinter.printEndMessage();
    }

    public static boolean executeCommands(FlatCube flatCube, String commands) {
        for (String command : commands.split("")) {
            if (command.equals("Q")) {
                return true;
            }

            FlatCubePrinter.printCommand(command);
            FlatCubePrinter.printFlatCube(flatCube);
        }

        return false;
    }

    public static String getInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
