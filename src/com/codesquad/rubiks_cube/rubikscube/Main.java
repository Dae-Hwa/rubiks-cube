package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.common.Utils;
import com.codesquad.rubiks_cube.flatcube.FlatCubePrinter;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    private static final int DEFAULT_REPEAT_NUMBER = 1;

    public static void main(String[] args) {

        RubiksCube rubiksCube = RubiksCube.create();

        RubiksCubePrinter.printRubiksCube(rubiksCube.toDTO().getRubiksCubeLayersDTO());

        LocalTime startTime = LocalTime.now();

        for (boolean isEnd = false; !isEnd; ) {
            FlatCubePrinter.printPrompt();

            isEnd = executeCommands(rubiksCube, Utils.getInput().split(""));
        }

        LocalTime endTime = LocalTime.now();

        RubiksCubePrinter.printEndMessage(rubiksCube.toDTO().getRotateCount(), Utils.measureElapsedTime(startTime, endTime));
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

            while (!commandsQueue.isEmpty() && Utils.isNumber(commandsQueue.peek())) {
                repeatNumber.append(commandsQueue.poll());
            }

            if (repeatNumber.length() == 0) {
                repeatNumber.append(DEFAULT_REPEAT_NUMBER);
            }

            for (int i = 0; i < Integer.parseInt(repeatNumber.toString()); i++) {
                rubiksCube.rotate(command);

                FlatCubePrinter.printCommand(command);
                RubiksCubePrinter.printRubiksCube(rubiksCube.toDTO().getRubiksCubeLayersDTO());
            }
        }

        return false;
    }
}
