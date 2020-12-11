package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCubePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    private static final String SPACE_TOP_AND_BOTTOM = "                ";

    public static void main(String[] args) {

        RubiksCube rubiksCube = RubiksCube.create();

        for (boolean isEnd = false; !isEnd; ) {
            FlatCubePrinter.printPrompt();

            isEnd = executeCommands(rubiksCube, getInput().split(""));
        }

        FlatCubePrinter.printEndMessage();
    }

    public static boolean executeCommands(RubiksCube rubiksCube, String[] commands) {
        Queue<String> commandsQueue = new ArrayDeque<>(Arrays.asList(commands));

        while (!commandsQueue.isEmpty()) {
            String command = commandsQueue.poll();

            if (command.equals("Q")) {
                return true;
            }

            command += !commandsQueue.isEmpty() && commandsQueue.peek().equals("'") ?
                    commandsQueue.poll() :
                    "";

            StringBuilder repeatNumber = new StringBuilder();

            while (!commandsQueue.isEmpty() && isNumber(commandsQueue.peek())) {
                repeatNumber.append(commandsQueue.poll());
            }

            if (repeatNumber.length() == 0) {
                repeatNumber.append("1");
            }

            for (int i = 0; i < Integer.parseInt(repeatNumber.toString()); i++) {
                rubiksCube.rotate(command);

                FlatCubePrinter.printCommand(command);
                FlatCubePrinter.printFlatCube(rubiksCube.toDTO().getTop(), SPACE_TOP_AND_BOTTOM, "");
                System.out.println();
                FlatCubePrinter.printFlatCubes(rubiksCube.toDTO().getMiddles());
                FlatCubePrinter.printFlatCube(rubiksCube.toDTO().getBottom(), SPACE_TOP_AND_BOTTOM, "");
                System.out.println();
            }
        }

        return false;
    }

    private static boolean isNumber(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
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
