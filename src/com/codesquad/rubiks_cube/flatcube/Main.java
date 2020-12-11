package com.codesquad.rubiks_cube.flatcube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        String[] blocks = new String[]{"R", "R", "W", "W", "B", "B", "G", "G"};
        String mainBlock = "C";
        FlatCube flatCube = new FlatCube(blocks, mainBlock);

        FlatCubePrinter.printFlatCube(flatCube);

        for (boolean isEnd = false; !isEnd; ) {
            FlatCubePrinter.printPrompt();

            isEnd = executeCommands(flatCube, getInput().split(""));
        }

        FlatCubePrinter.printEndMessage();
    }

    public static boolean executeCommands(FlatCube flatCube, String[] commands) {
        Queue<String> commandsQueue = new ArrayDeque<>(Arrays.asList(commands));

        while (!commandsQueue.isEmpty()) {
            String command = commandsQueue.poll();

            if(command.equals("Q")) {
                return true;
            }

            command += !commandsQueue.isEmpty() && commandsQueue.peek().equals("'") ?
                    commandsQueue.poll() :
                    "";

            FlatCubePrinter.printCommand(command);
            FlatCubePrinter.printFlatCube(flatCube.push(command));
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
