package com.codesquad.rubiks_cube.flatcube;

import com.codesquad.rubiks_cube.common.Utils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        String[] blocks = new String[]{"R", "R", "W", "W", "B", "B", "G", "G"};
        String mainBlock = "C";
        FlatCube flatCube = new FlatCube(blocks, mainBlock);

        FlatCubePrinter.printFlatCube(flatCube.toDTO());

        for (boolean isEnd = false; !isEnd; ) {
            FlatCubePrinter.printPrompt();

            isEnd = executeCommands(flatCube, Utils.getInput().split(""));
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
            FlatCubePrinter.printFlatCube(flatCube.push(command).toDTO());
        }

        return false;
    }
}
